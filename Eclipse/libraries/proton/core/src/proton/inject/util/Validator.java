package proton.inject.util;

public class Validator {
	public static void checkState(boolean state, String msg) {
		if (!state)
			throw new IllegalStateException(msg);
	}

	public static <T> T checkNotNull(T obj, String msg) {
		if (obj == null)
			throw new IllegalStateException(msg);
		return obj;
	}
}
