package proton.example;

import proton.inject.DefaultModule;
import proton.inject.Proton;
import android.app.Application;

public class ExampleApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		Proton.initialize(this, new DefaultModule() {
			@Override
			protected void configure() {
				super.configure();
				bind(Greeting.class).to(GreetingImpl.class);
			}
		});
	}
}
