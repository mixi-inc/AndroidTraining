package proton.inject.fragment;

import proton.inject.Proton;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class ProtonDialogFragment extends DialogFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Proton.getInjector(getActivity()).inject(this);
	}
}
