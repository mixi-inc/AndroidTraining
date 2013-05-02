
package jp.mixi.sample.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

class SamplePagerAdapter extends PagerAdapter {

    @SuppressWarnings("unused")
    private static final String TAG = SamplePagerAdapter.class.getSimpleName();

    private static final int PAGE_COUNT = 5;

    private Context mContext;

    public SamplePagerAdapter(Context context) {
        super();
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // TextViewを生成
        TextView textView = new TextView(mContext);
        textView.setText("Position:" + position);
        // コンテナに追加
        container.addView(textView);

        // ページに当たるObjectを返却します。この例のようにViewのみを返す必要はありません。
        return textView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // viewの削除
        // objectはinstantiateItemで返却したオブジェクトです
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        // ページ数を返します。今回は固定値としています。
        return PAGE_COUNT;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // hoge
        return view == (TextView) object;
    }

}
