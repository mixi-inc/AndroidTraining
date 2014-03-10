package proton.inject.observer;

import java.lang.annotation.Annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import proton.inject.InvocationException;
import proton.inject.util.SparseClassArray;

public class ObserverManager {
	private SparseClassArray<List<Observer>> mObservers = new SparseClassArray<List<Observer>>();

	public void registerIfObserver(Object obj) {
		for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
			for (Method method : clazz.getDeclaredMethods()) {
				Class<?> eventClass = findObserveEvent(method);
				if (eventClass != null)
					register(eventClass, new Observer(obj, method));
			}
		}
	}

	private void register(Class<?> eventClass, Observer observer) {
		synchronized (this) {
			List<Observer> list = mObservers.get(eventClass);
			if (list == null) {
				list = new ArrayList<Observer>();
				mObservers.put(eventClass, list);
			}
			list.add(observer);
		}
	}

	public void fire(Object event) {
		synchronized (this) {
			List<Observer> list = mObservers.get(event.getClass());
			if (list == null)
				return;

			for (Observer observer : list) {
				try {
					observer.method.invoke(observer.receiver, event);
				} catch (IllegalArgumentException exp) {
					throw new InvocationException(exp);
				} catch (IllegalAccessException exp) {
					throw new InvocationException(exp);
				} catch (InvocationTargetException exp) {
					throw new InvocationException(exp);
				}
			}
		}
	}

	public void destroy() {
		synchronized (this) {
			mObservers.clear();
		}
	}

	private Class<?> findObserveEvent(Method method) {
		Annotation[][] parameterAnnotations = method.getParameterAnnotations();
		for (int i = 0; i < parameterAnnotations.length; ++i) {
			Annotation[] annotations = parameterAnnotations[i];
			Class<?> type = method.getParameterTypes()[i];
			for (Annotation ann : annotations) {
				if (ann.annotationType() == Observes.class)
					return type;
			}
		}
		return null;
	}

	private static class Observer {
		private Object receiver;
		private Method method;

		private Observer(Object receiver, Method method) {
			this.receiver = receiver;
			this.method = method;
		}
	}
}
