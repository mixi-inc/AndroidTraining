
package jp.mixi.assignment.controller.adv;

import android.os.*;
import android.support.v4.app.*;
import android.view.*;

public class MainFragment extends Fragment {

    public MainFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_main, container);
        return v;
    }
}
