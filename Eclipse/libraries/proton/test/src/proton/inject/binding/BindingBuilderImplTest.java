package proton.inject.binding;

import javax.inject.Provider;

import proton.inject.ConfigurationException;
import proton.inject.binding.AndroidVersion;
import proton.inject.binding.BindingBuilderImpl;
import proton.inject.binding.Bindings;
import proton.inject.binding.DeviceModel;
import proton.inject.scope.ApplicationScoped;
import proton.inject.scope.Dependent;

import android.test.AndroidTestCase;

public class BindingBuilderImplTest extends AndroidTestCase {
	private Bindings mBindings;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mBindings = new Bindings();
	}

	public void testTo() {
		new BindingBuilderImpl<Aaa>(Aaa.class, mBindings).to(AaaImpl.class);
		assertEquals(AaaImpl.class, mBindings.get(Aaa.class).getToClass());
	}

	public void testToWithVersionAnnotation() {
		new BindingBuilderImpl<Bbb>(Bbb.class, mBindings).to(BbbImpl.class);
		assertNull(mBindings.get(Bbb.class));
	}

	public void testToWithModelAnnotation() {
		new BindingBuilderImpl<Ccc>(Ccc.class, mBindings).to(CccImpl.class);
		assertNull(mBindings.get(Ccc.class));
	}

	public void testToWithNull() {
		try {
			new BindingBuilderImpl<Aaa>(Aaa.class, mBindings).to(null);
			fail();
		} catch (ConfigurationException exp) {
		}
	}

	public void testToWithScopeAnnotation() {
		new BindingBuilderImpl<Aaa>(Aaa.class, mBindings).to(AaaImpl.class);
		assertEquals(ApplicationScoped.class, mBindings.get(Aaa.class).getScope());
	}

	public void testToProviderClassOfQextendsProviderOfT() {
		new BindingBuilderImpl<Aaa>(Aaa.class, mBindings).toProvider(AaaProvider.class);
		assertEquals(Dependent.class, mBindings.get(Aaa.class).getScope());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testToProviderClassWithNull() {
		try {
			new BindingBuilderImpl<Aaa>(Aaa.class, mBindings).toProvider((Class) null);
			fail();
		} catch (ConfigurationException exp) {
		}
	}

	public void testToProviderProviderOfT() {
		Provider<Aaa> p = new AaaProvider();
		new BindingBuilderImpl<Aaa>(Aaa.class, mBindings).toProvider(p);
		assertEquals(p, mBindings.get(Aaa.class).getProvider());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testToProviderWithNull() {
		try {
			new BindingBuilderImpl<Aaa>(Aaa.class, mBindings).toProvider((Provider) null);
			fail();
		} catch (ConfigurationException exp) {
		}
	}

	public void testIn() {
		new BindingBuilderImpl<Aaa>(Aaa.class, mBindings).in(ApplicationScoped.class);
		assertEquals(ApplicationScoped.class, mBindings.get(Aaa.class).getScope());
	}

	public void testInWithNull() {
		try {
			new BindingBuilderImpl<Aaa>(Aaa.class, mBindings).in(null);
			fail();
		} catch (ConfigurationException exp) {
		}
	}

	public interface Aaa {
	}

	@ApplicationScoped
	@AndroidVersion(0)
	public static class AaaImpl implements Aaa {
	}

	@Dependent
	@AndroidVersion(0)
	public static class AaaProvider implements Provider<Aaa> {
		@Override
		public Aaa get() {
			return new AaaImpl();
		}
	}

	public interface Bbb {
	}

	@AndroidVersion(100)
	public static class BbbImpl implements Bbb {
	}

	public interface Ccc {
	}

	@DeviceModel("aaa")
	public static class CccImpl implements Ccc {
	}
}
