package jp.mixi.training.inapppurchase;

import jp.mixi.training.inapppurchase.helper.DummySku;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	private void disableActionViews () {
		findViewById(R.id.action_start_accepted_purchase).setEnabled(false);
		findViewById(R.id.action_start_canceled_purchase).setEnabled(false);
	}
	private void enableActionViews () {
		findViewById(R.id.action_start_accepted_purchase).setEnabled(true);
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
	}

	private void startPurchase (final DummySku skuToPurchase) {
		//IMP here
		Log.w(TAG, "purchase not implemented");
	}

	public void onStartAcceptedPurchaseClick (final View view) {
		startPurchase(DummySku.PURCHASED);
	}

	public void onStartCanceledPurchaseClick (final View view) {
		startPurchase(DummySku.CANCELED);
	}
}
