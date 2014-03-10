package proton.inject.content;

import proton.inject.Proton;

import android.content.ContentProvider;

public abstract class ProtonContentProvider extends ContentProvider {
	@Override
	public boolean onCreate() {
		Proton.getInjector(getContext()).inject(this);
		return true;
	}
}
