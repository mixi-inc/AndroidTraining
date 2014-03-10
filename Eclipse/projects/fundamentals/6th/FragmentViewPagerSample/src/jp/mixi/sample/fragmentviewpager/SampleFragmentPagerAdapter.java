
package jp.mixi.sample.fragmentviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final int PAGE_COUNT = 5;

    @SuppressWarnings("unused")
    private static final String TAG = SampleFragmentPagerAdapter.class.getSimpleName();

    public SampleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return SampleFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

}
