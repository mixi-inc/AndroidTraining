package proton.inject.binding;

import java.lang.annotation.Annotation;

import javax.inject.Provider;

import android.os.Build;

import proton.inject.ConfigurationException;
import proton.inject.scope.ApplicationScoped;
import proton.inject.scope.ContextScoped;
import proton.inject.scope.Dependent;
import proton.inject.util.InjectorUtils;

public class BindingBuilderImpl<T> implements BindingBuilder<T> {
	private final Binding<T> mBinding;
	private final Bindings mBindings;

	public BindingBuilderImpl(Class<T> clazz, Bindings bindings) {
		mBinding = new Binding<T>(clazz);
		mBindings = bindings;

		mBindings.register(mBinding);

		if (!InjectorUtils.isAbstract(clazz)) {
			setScope(clazz);
			validateAnnotation(clazz);
		}
	}

	@Override
	public ScopedBuilder to(Class<? extends T> to) {
		mBinding.setToClass(checkNotNull(to, "to"));
		setScope(to);
		validateAnnotation(to);
		return this;
	}

	@Override
	public ScopedBuilder toProvider(Class<? extends Provider<T>> provider) {
		mBinding.setProviderClass(checkNotNull(provider, "provider"));
		setScope(InjectorUtils.toActualClass(provider));
		validateAnnotation(provider);
		return this;
	}

	@Override
	public void toProvider(Provider<T> provider) {
		mBinding.setProvider(checkNotNull(provider, "provider"));
		mBinding.setScope(ApplicationScoped.class);
		validateAnnotation(provider.getClass());
	}

	@Override
	public void in(Class<? extends Annotation> scope) {
		mBinding.setScope(checkNotNull(scope, "scope"));
	}

	private void setScope(Class<?> clazz) {
		Annotation[] anns = clazz.getAnnotations();
		for (Annotation ann : anns) {
			if (ApplicationScoped.class.isInstance(ann) || Dependent.class.isInstance(ann)
					|| ContextScoped.class.isInstance(ann))
				mBinding.setScope(ann.annotationType());
		}
	}

	private void validateAnnotation(Class<?> clazz) {
		AndroidVersion version = clazz.getAnnotation(AndroidVersion.class);
		if (version != null && Build.VERSION.SDK_INT < version.value())
			mBindings.unregister(mBinding);

		DeviceModel model = clazz.getAnnotation(DeviceModel.class);
		if (model != null && !Build.MODEL.equals(model.value()))
			mBindings.unregister(mBinding);
	}

	private <I> I checkNotNull(I obj, String msg) {
		if (obj == null)
			throw new ConfigurationException(msg);
		return obj;
	}
}
