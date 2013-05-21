package proton.inject.util;

import proton.inject.util.Validator;
import android.test.AndroidTestCase;

public class ValidatorTest extends AndroidTestCase {

	public void testCheckState() {
		Validator.checkState(true, "");
	}

	public void testCheckStateWithFalse() {
		try {
			Validator.checkState(false, "");
		} catch (IllegalStateException exp) {
		}
	}

	public void testCheckNotNull() {
		String a = Validator.checkNotNull("a", "");
		assertEquals("a", a);
	}

	public void testCheckNotNullWithNull() {
		try {
			Validator.checkNotNull(null, "");
			fail();
		} catch (IllegalStateException exp) {
		}
	}
}
