package jp.mixi.training.inapppurchase.helper;

import java.lang.reflect.Type;
import java.util.Date;

import javax.annotation.Nullable;

import jp.mixi.android.commons.json.JsonConvertible;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.os.RemoteException;

import com.android.vending.billing.IInAppBillingService;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

public class StartPurchaseHelper {	
	private static final int BILLING_RESPONSE_RESULT_OK = 0;
	private static final int BILLING_RESPONSE_RESULT_USER_CANCELED = 1;
	private static final int BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE = 3;
	private static final int BILLING_RESPONSE_RESULT_ITEM_UNAVAILABLE = 4;
	@SuppressWarnings("unused")
	private static final int BILLING_RESPONSE_RESULT_DEVELOPER_ERROR = 5;
	@SuppressWarnings("unused")
	private static final int BILLING_RESPONSE_RESULT_ERROR = 6;
	private static final int BILLING_RESPONSE_RESULT_ITEM_ALREADY_OWNED = 7;
	private static final int BILLING_RESPONSE_RESULT_ITEM_NOT_OWNED = 8;

	private static final String RESPONSE_KEY_RESPONSE_CODE = "RESPONSE_CODE";
	private static final String RESPONSE_KEY_BUY_INTENT = "BUY_INTENT";
	private static final String RESPONSE_KEY_PURCHASE_DATA = "INAPP_PURCHASE_DATA";

	private final GsonBuilder mGsonBuilder;
	{
		mGsonBuilder = new GsonBuilder();
		mGsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
			@Override
			public Date deserialize (final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
				if (json != null && json.isJsonPrimitive()) {
					final JsonPrimitive jsonPrimitive = json.getAsJsonPrimitive();
					if (jsonPrimitive != null && jsonPrimitive.isNumber()) {
						final long dateLong = jsonPrimitive.getAsLong();
						return new Date(dateLong);
					}
				}
				//not a number, default deserialization:
				return context.deserialize(json, typeOfT);
			}
		});
	}
	public static enum BillingActionResponse {
		/**
		 * 決済アクションが成功した
		 */
		DONE,
		/**
		 * ユーザが決済を中止した
		 */
		CANCELED,
		/**
		 * 課金サービスのバージョンがアクションに対応しない
		 */
		SERVICE_VERSION_ERROR,
		/**
		 * 購入不可能なアイテム（sku）
		 */
		ITEM_UNAVAILABLE,
		/**
		 * 保有していないアイテムの消費（consume）や保有しているアイテムの購入等
		 */
		ITEM_OWNERSHIP_ERROR,
		/**
		 * 内部実装によるエラーが発生した
		 */
		INTERNAL_ERROR
	}

	public static class PurchaseData implements JsonConvertible {
		private String orderId;
		private String packageName;
		private String productId;
		private Date purchaseTime;
		private String purchaseToken;
		private String developerPayload;

		public String getOrderId() { return orderId; }
		public String getPackageName() { return packageName; }
		public String getProductId() { return productId; }
		public Date getPurchaseTime() { return purchaseTime; }
		public String getPurchaseToken() { return purchaseToken; }
		public String getDeveloperPayload() { return developerPayload; }

		@Override
		public String toString() {
			return Objects.toStringHelper(this)
					.add("orderId", orderId)
					.add("packageName", packageName)
					.add("productId", productId)
					.add("purchaseTime", purchaseTime)
					.add("purchaseToken", purchaseToken)
					.add("developerPayload", developerPayload)
					.toString();
		}
	};

	private BillingActionResponse billingActionResponseCodeToEnum (final int billingActionResponseCode, final BillingActionResponse defaultEnum) {
		switch (billingActionResponseCode) {
		case BILLING_RESPONSE_RESULT_USER_CANCELED:
			return BillingActionResponse.CANCELED;
		case BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE:
			return BillingActionResponse.SERVICE_VERSION_ERROR;
		case BILLING_RESPONSE_RESULT_ITEM_UNAVAILABLE:
			return BillingActionResponse.ITEM_UNAVAILABLE;
		case BILLING_RESPONSE_RESULT_ITEM_ALREADY_OWNED:
		case BILLING_RESPONSE_RESULT_ITEM_NOT_OWNED:
			return BillingActionResponse.ITEM_OWNERSHIP_ERROR;
		default:
			return defaultEnum;
		}
	}

	public BillingActionResponse startPurchaseAction (final Activity callingActivity, final int activityResultCode, final IInAppBillingService billingService, final DummySku sku) throws RemoteException {
		if (callingActivity == null || billingService == null) throw new IllegalArgumentException("activity and service argument must be non-null");

		final Bundle purchaseIntentBundle = billingService.getBuyIntent(3, callingActivity.getPackageName(), sku.getId(), "inapp", null);
		final int responseCodeGetBuyIntent = purchaseIntentBundle.getInt(RESPONSE_KEY_RESPONSE_CODE);
		switch (responseCodeGetBuyIntent) {
		case BILLING_RESPONSE_RESULT_OK:
			final PendingIntent pendingIntent = purchaseIntentBundle.getParcelable(RESPONSE_KEY_BUY_INTENT);
			if (pendingIntent != null) {
				try {
					callingActivity.startIntentSenderForResult(pendingIntent.getIntentSender(), activityResultCode, null, 0, 0, 0);
					return BillingActionResponse.DONE;
				} catch (final SendIntentException sendIntentException) {
					return BillingActionResponse.INTERNAL_ERROR;
				}
			} else {
				return BillingActionResponse.INTERNAL_ERROR;
			}
		default:
			return billingActionResponseCodeToEnum(responseCodeGetBuyIntent, BillingActionResponse.INTERNAL_ERROR);
		}
	}

	public Optional<PurchaseData> extractPurchaseActionData (@Nullable final Bundle purchaseActionResult) {
		if (purchaseActionResult != null) {
			final String purchaseActionResultJson = purchaseActionResult.getString(RESPONSE_KEY_PURCHASE_DATA);
			if (purchaseActionResultJson != null) {
				final Gson purchaseActionBuilder = mGsonBuilder.create();
				final PurchaseData purchaseActionData = purchaseActionBuilder.fromJson(purchaseActionResultJson, PurchaseData.class);
				return Optional.of(purchaseActionData);
			}
		}
		//no data:
		return Optional.absent();
	}

	public BillingActionResponse consumePurchase (final Activity callingActivity, final IInAppBillingService billingService, final String purchaseToken) throws RemoteException {
		if (callingActivity == null || billingService == null) throw new IllegalArgumentException("activity and service argument must be non-null");

		final int responseCode = billingService.consumePurchase(3, callingActivity.getPackageName(), purchaseToken);
		switch (responseCode) {
		case BILLING_RESPONSE_RESULT_OK:
			return BillingActionResponse.DONE;
		default:
			return billingActionResponseCodeToEnum(responseCode, BillingActionResponse.INTERNAL_ERROR);
		}
	}
}
