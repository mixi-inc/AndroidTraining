package jp.mixi.training.inapppurchase;

import jp.mixi.training.inapppurchase.helper.DummySku;
import jp.mixi.training.inapppurchase.helper.StartPurchaseHelper;
import jp.mixi.training.inapppurchase.helper.StartPurchaseHelper.BillingActionResponse;
import jp.mixi.training.inapppurchase.helper.StartPurchaseHelper.PurchaseData;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.vending.billing.IInAppBillingService;
import com.google.common.base.Optional;

// DISCLAIMER: this activity is a mere way to test calls to the Google in-app purchase service ;
// it doesn't provide a proper "buy something" user experience
// nor should it be referred to as a model for an activity providing such a UX
public class MainActivity extends FragmentActivity {
	private static final String TAG = MainActivity.class.getSimpleName();

	private static final int REQUEST_CODE_PURCHASE = 100;

	private volatile IInAppBillingService mBillingService = null;
	private ServiceConnection mBillingServiceConnection = null;

	private final StartPurchaseHelper mPurchaseHelper = new StartPurchaseHelper();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		disableActionViews();

		//アプリ内課金サービスに接続する
		mBillingServiceConnection = new ServiceConnection() {
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				mBillingService = IInAppBillingService.Stub.asInterface(service);
				enableActionViews();
			}

			@Override
			public void onServiceDisconnected(ComponentName name) {
				mBillingService = null;
			}
		};
		bindService(new Intent("com.android.vending.billing.InAppBillingService.BIND"), mBillingServiceConnection, Context.BIND_AUTO_CREATE);
	}

	private void disableActionViews () {
		findViewById(R.id.action_start_accepted_purchase).setEnabled(false);
		findViewById(R.id.action_consume_purchase).setEnabled(false);
		findViewById(R.id.action_start_canceled_purchase).setEnabled(false);
	}
	private void enableActionViews () {
		findViewById(R.id.action_start_accepted_purchase).setEnabled(true);
		findViewById(R.id.action_consume_purchase).setEnabled(true);
		findViewById(R.id.action_start_canceled_purchase).setEnabled(true);
	}
	private void showResult (final boolean resultOk, final String text) {
		final TextView resultView = (TextView)findViewById(R.id.print_purchase_result);
		if (resultView != null) {
			resultView.setBackgroundColor(getResources().getColor(resultOk ? R.color.result_ok_bg : R.color.result_ko_bg));
			resultView.setText(text != null ? text : "");
		} else {
			Log.w(TAG, "Can't find result view ; nothing done");
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mBillingServiceConnection != null) unbindService(mBillingServiceConnection);
	}

	protected String getDummyPurchaseToken () {
		return DummySku.PURCHASED.getPurchaseToken(true, getPackageName());
	}

	private void startPurchase (final DummySku skuToPurchase) {
		if (mBillingService == null) {
			Log.w(TAG, "Purchase attempted while billing service not yet initiated");
			return;
		}
		try {
			final BillingActionResponse startPurchaseResult = mPurchaseHelper.startPurchaseAction(this, REQUEST_CODE_PURCHASE, mBillingService, skuToPurchase);

			switch (startPurchaseResult) {  //エラーが起きた場合はここでフィードバックし、成功した場合はonActivityResultが呼ばれるまで待つ
			case CANCELED:
				showResult(false, getResources().getString(R.string.error_user_canceled));
				break;
			case ITEM_UNAVAILABLE:
				showResult(false, getResources().getString(R.string.error_item_unavailable));
				break;
			case ITEM_OWNERSHIP_ERROR:
				showResult(false, getResources().getString(R.string.error_item_already_owned));
				break;
			case INTERNAL_ERROR:
			case SERVICE_VERSION_ERROR:
				showResult(false, getResources().getString(R.string.error_internal));
				break;
			default:
				//no error, nothing to do until we are called back
				break;
			}
		} catch (final RemoteException remoteException) {
			Log.w(TAG, "remote exception while purchase attempt", remoteException);
			showResult(false, getResources().getString(R.string.error_internal));
		}
	}

	@Override
	protected void onActivityResult (final int requestCode, final int resultCode, final Intent data) {
		switch (requestCode) {
		case REQUEST_CODE_PURCHASE:
			switch (resultCode) {
			case Activity.RESULT_OK :
				final Optional<PurchaseData> purchaseResultData = mPurchaseHelper.extractPurchaseActionData(data.getExtras());
				final int messageId = purchaseResultData.isPresent() ? R.string.result_purchase_accepted : R.string.error_internal;
				showResult(true, getResources().getString(messageId));
				if (purchaseResultData.isPresent()) Log.v(TAG, purchaseResultData.get().toString());
				break;
			case Activity.RESULT_CANCELED:
				showResult(false, getResources().getString(R.string.error_user_canceled));
				break;
			default:
				showResult(false, getResources().getString(R.string.error_internal));
				break;
			}
		}
	}

	public void onStartAcceptedPurchaseClick (final View view) {
		startPurchase(DummySku.PURCHASED);
	}

	public void onStartCanceledPurchaseClick (final View view) {
		startPurchase(DummySku.CANCELED);
	}
	
	public void onConsumeLastPurchaseClick (final View view) {
		final String lastPurchaseToken = getDummyPurchaseToken();
		if (lastPurchaseToken != null) {
			try {
				final BillingActionResponse consumePurchaseResult = mPurchaseHelper.consumePurchase(this, mBillingService, lastPurchaseToken);
				
				final boolean purchaseConsumed = (consumePurchaseResult == BillingActionResponse.DONE);
				final int messageId;
				if (purchaseConsumed) messageId = R.string.result_purchase_consumed;
				else if (consumePurchaseResult == BillingActionResponse.ITEM_OWNERSHIP_ERROR) messageId = R.string.error_item_already_owned;
				else messageId = R.string.result_purchase_not_consumed;
				showResult(purchaseConsumed, getResources().getString(messageId));
			} catch (final RemoteException remoteException) {
				Log.w(TAG, "remote exception while consumption attempt", remoteException);
				showResult(false, getResources().getString(R.string.error_internal));
			}
		} else {
			showResult(false, getResources().getString(R.string.error_no_purchase_to_consume));
		}
	}
}
