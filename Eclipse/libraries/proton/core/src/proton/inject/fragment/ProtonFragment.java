package proton.inject.fragment;

import proton.inject.Proton;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class ProtonFragment extends Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Proton.getInjector(getActivity()).inject(this);
	}
}
