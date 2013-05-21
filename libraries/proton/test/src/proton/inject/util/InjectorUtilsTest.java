package proton.inject.util;

import java.lang.reflect.Field;

import javax.inject.Provider;

import proton.inject.util.InjectorUtils;

import android.test.AndroidTestCase;

public class InjectorUtilsTest extends AndroidTestCase {

    public void testToActualClass() throws Exception {
        Client c = new Client();
        Field field = c.getClass().getDeclaredField("provider");
        Class<?> clazz = InjectorUtils.toActualClass(field.getGenericType());
        assertEquals(String.class, clazz);
    }

    public void testIsAbstract() {
        assertTrue(InjectorUtils.isAbstract(Aaa.class));
        assertTrue(InjectorUtils.isAbstract(Bbb.class));
        assertFalse(InjectorUtils.isAbstract(Ccc.class));
    }
    
    public interface Aaa {        
    }
    public abstract class Bbb {
    }
    public static class Ccc {        
    }
    
    public static class Client {
        @SuppressWarnings("unused")
        private Provider<String> provider;
    }
}
