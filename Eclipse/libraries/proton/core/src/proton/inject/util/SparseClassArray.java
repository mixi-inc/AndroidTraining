package proton.inject.util;

import android.util.SparseArray;

public class SparseClassArray<T> {
	private SparseArray<T> mArray = new SparseArray<T>();

	public void put(Class<?> key, T value) {
		mArray.put(key.hashCode(), value);
	}

	public T get(Class<?> key) {
		return mArray.get(key.hashCode());
	}

	public void remove(Class<?> key) {
		mArray.remove(key.hashCode());
	}

	public void clear() {
		mArray.clear();
	}
}
