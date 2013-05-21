package proton.inject.util;

import proton.inject.util.SparseClassArray;
import android.test.AndroidTestCase;

public class SparseClassArrayTest extends AndroidTestCase {
    public void testPutAndGet() {
        SparseClassArray<String> arr = new SparseClassArray<String>();
        arr.put(String.class, "aa");
        assertEquals("aa", arr.get(String.class));
        assertNotSame("aa", arr.get(Object.class));
    }
}
