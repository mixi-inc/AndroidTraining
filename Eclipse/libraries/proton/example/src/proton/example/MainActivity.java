package proton.example;

import javax.inject.Inject;

import proton.inject.activity.ProtonActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends ProtonActivity {
	@Inject
	private Greeting mGreeting;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView textView = (TextView)findViewById(R.id.text);
		textView.setText(mGreeting.greet());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
