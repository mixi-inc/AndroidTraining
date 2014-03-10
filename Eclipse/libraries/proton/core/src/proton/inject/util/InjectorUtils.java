package proton.inject.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.inject.Provider;

import proton.inject.ProvisionException;
import proton.inject.scope.ApplicationScoped;
import proton.inject.scope.ContextScoped;
import proton.inject.scope.Dependent;

public final class InjectorUtils {
	private InjectorUtils() {
	}

	public static Class<?> toActualClass(Type type) {
		if (type instanceof ParameterizedType
				&& (((ParameterizedType) type).getRawType() == Provider.class || ((ParameterizedType) type)
						.getRawType() == javax.inject.Provider.class))
			return (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
		return (Class<?>) type;
	}

	public static boolean isAbstract(Class<?> clazz) {
		return clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers());
	}

	public static Class<? extends Annotation> getScopeAnnotation(Class<?> clazz) {
		Annotation[] anns = clazz.getAnnotations();
		for (Annotation a : anns) {
			Class<? extends Annotation> annClass = a.annotationType();
			if (ApplicationScoped.class == annClass || Dependent.class == annClass || ContextScoped.class == annClass)
				return annClass;

		}
		return ContextScoped.class;
	}

	public static void setField(Object receiver, Field field, Object value) {
		try {
			field.set(receiver, value);
		} catch (IllegalAccessException exp) {
			throw new ProvisionException(exp);
		}
	}

	public static Object getField(Object receiver, Field field) {
		try {
			return field.get(receiver);
		} catch (IllegalAccessException exp) {
			throw new ProvisionException(exp);
		}
	}

	public static Object newInstance(Constructor<?> constructor, Object[] args) {
		try {
			return constructor.newInstance(args);
		} catch (IllegalAccessException exp) {
			throw new ProvisionException(exp);
		} catch (InvocationTargetException exp) {
			throw new ProvisionException(exp);
		} catch (InstantiationException exp) {
			throw new ProvisionException(exp);
		}
	}
}
