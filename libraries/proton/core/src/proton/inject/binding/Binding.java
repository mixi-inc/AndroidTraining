package proton.inject.binding;

import java.lang.annotation.Annotation;

import javax.inject.Provider;

public class Binding<T> {
	private final Class<T> mBindClass;
	private Class<?> mToClass;
	private Class<? extends Provider<T>> mProviderClass;
	private Provider<T> mProvider;
	private Class<? extends Annotation> mScope;

	public Binding(Class<T> key) {
		mBindClass = key;
		mToClass = key;
	}

	public Class<T> getBindClass() {
		return mBindClass;
	}

	public Class<?> getToClass() {
		return mProviderClass != null ? mProviderClass : mToClass;
	}

	public void setToClass(Class<?> toClass) {
		mToClass = toClass;
	}

	public void setProviderClass(Class<? extends Provider<T>> providerClass) {
		mProviderClass = providerClass;
	}

	public Class<? extends Provider<T>> getProviderClass() {
		return mProviderClass;
	}

	public void setProvider(Provider<T> provider) {
		mProvider = provider;
	}

	public Provider<T> getProvider() {
		return (Provider<T>) mProvider;
	}

	public void setScope(Class<? extends Annotation> scope) {
		mScope = scope;
	}

	public Class<? extends Annotation> getScope() {
		return mScope;
	}
}
