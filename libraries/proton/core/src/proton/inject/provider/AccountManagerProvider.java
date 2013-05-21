package proton.inject.provider;

import javax.inject.Inject;
import javax.inject.Provider;

import proton.inject.binding.AndroidVersion;

import android.accounts.AccountManager;
import android.content.Context;

@AndroidVersion(5)
public class AccountManagerProvider implements Provider<AccountManager> {
	@Inject
	private Context mContext;

	@Override
	public AccountManager get() {
		return AccountManager.get(mContext);
	}
}
