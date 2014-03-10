package proton.inject;

import javax.inject.Inject;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.Application;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.PowerManager;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.test.AndroidTestCase;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public class SystemServiceInjectionTest extends AndroidTestCase {
	private Injector mInjector;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Proton.initialize((Application) getContext().getApplicationContext());
		mInjector = Proton.getInjector(getContext());
	}

	@Override
	protected void tearDown() throws Exception {
		Proton.destroy();
		super.tearDown();
	}

	public void testInject() {
		Client c = mInjector.inject(new Client());
		assertNotNull(c.activityManager);
		assertNotNull(c.alarmManager);
		assertNotNull(c.audioManager);
		assertNotNull(c.connectivityManager);
		assertNotNull(c.inputMethodManager);
		assertNotNull(c.keyguardManager);
		assertNotNull(c.locationManager);
		assertNotNull(c.notificationManager);
		assertNotNull(c.powerManager);
		assertNotNull(c.sensorManager);
		assertNotNull(c.telephonyManager);
		assertNotNull(c.vibrator);
		assertNotNull(c.wifiManager);
		assertNotNull(c.windowManager);
	}

	public static class Client {
		@Inject
		private ActivityManager activityManager;
		@Inject
		private AlarmManager alarmManager;
		@Inject
		private AudioManager audioManager;
		@Inject
		private ConnectivityManager connectivityManager;
		@Inject
		private InputMethodManager inputMethodManager;
		@Inject
		private KeyguardManager keyguardManager;
		@Inject
		private LocationManager locationManager;
		@Inject
		private NotificationManager notificationManager;
		@Inject
		private PowerManager powerManager;
		@Inject
		private SensorManager sensorManager;
		@Inject
		private TelephonyManager telephonyManager;
		@Inject
		private Vibrator vibrator;
		@Inject
		private WifiManager wifiManager;
		@Inject
		private WindowManager windowManager;
	}
}
