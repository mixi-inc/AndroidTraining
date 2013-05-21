package proton.inject.binding;

import proton.inject.util.SparseClassArray;

public class Bindings {
	private SparseClassArray<Binding<?>> mBindings = new SparseClassArray<Binding<?>>();

	public <T> void register(Binding<T> binding) {
		mBindings.put(binding.getBindClass(), binding);
	}

	public <T> void unregister(Binding<T> binding) {
		mBindings.remove(binding.getBindClass());
	}

	@SuppressWarnings("unchecked")
	public <T> Binding<T> get(Class<T> key) {
		return (Binding<T>) mBindings.get(key);
	}
}
