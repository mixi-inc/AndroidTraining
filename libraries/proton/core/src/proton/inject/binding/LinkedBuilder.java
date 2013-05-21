package proton.inject.binding;

import javax.inject.Provider;

public interface LinkedBuilder<T> {
	public ScopedBuilder to(Class<? extends T> clazz);

	public ScopedBuilder toProvider(Class<? extends Provider<T>> provider);

	public void toProvider(Provider<T> provider);
}
