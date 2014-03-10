package proton.inject;

import proton.inject.binding.Bindings;
import proton.inject.listener.FieldListeners;
import proton.inject.listener.ProviderListeners;

public interface Module {
	public void configure(Bindings bindings, ProviderListeners providerListeners, FieldListeners fieldListeners);
}
