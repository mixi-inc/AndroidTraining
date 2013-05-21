[![Build Status](https://travis-ci.org/hnakagawa/proton.png)](https://travis-ci.org/hnakagawa/proton)

## What is Proton?
A lightweight dependency injection framework for Android.

Inspired by MiniGuice and [RoboGuice](https://github.com/roboguice/roboguice)

## Getting Started
Initialize App
```
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Proton.initialize(this, new MyModule());
    }
}
```

Define Module
```
public class MyModule extends DefaultModule {
    @Override
    protected void configure() {
        super.configure();
        bind(Greeting.class).to(GreetingImpl.class);
    }
}
```

Field Injection
```
public class MainActivity extends ProtonActivity {
    @Inject private Greeting mGreeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.greeting_text);
        textView.setText(mGreeting.greet());
    }
}
```

### Context-scoped Injection

Proton supports context-scoped injection.

```
@ContextScoped
public class GreetingImpl extends Greeting {
    ...
}
```

or

```
public class MyModule extends DefaultModule {
    @Override
    protected void configure() {
        super.configure();
        bind(Greeting.class).to(GreetingImpl.class).in(ContextScoped.class);
    }
}
```

- @ApplicationScoped => is injected as application singleton.
- @ContextScoped     => is injected as context singleton.
- @Dependent         => is injected as new object every time.

### Tips
#### On-demand Injection
```
Injector injector = Proton.getInjector(...);
Greeting greeting = new GreetingImpl();
injector.inject(greeting);
```

#### Provider Injection
```
public class BigCoffeeMaker {
    @Inject Provider<Filter> mFilterProvider;

    public void brew(int numberOfPots) {
        ...
        for (int p = 0; p < numberOfPots; p++) {
            maker.addFileter(filterProvider.get()); // new filter every time.
            maker.addCoffee(...);
            maker.percolate();
            ...
        }
    }
}
```

#### RetainState
@RetainState saves value automatically when the android system call onSaveInstanceState.

## Todo
- Clarity error messages
- Test utility

## License
```
    Copyright 2013 Hirofumi Nakagawa.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
```
