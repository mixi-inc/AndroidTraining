
package jp.mixi.practice.listview.beg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomListItemAdapter extends ArrayAdapter<String> {

    @SuppressWarnings("unused")
    private static final String TAG = CustomListItemAdapter.class.getSimpleName();

    private LayoutInflater mLayoutInflater;

    public CustomListItemAdapter(Context context, List<String> objects) {
        // 第2引数はtextViewResourceIdとされていますが、カスタムリストアイテムを使用する場合は特に意識する必要のない引数です
        super(context, 0, objects);
        // レイアウト生成に使用するインフレーター
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;

        // ListViewに表示する分のレイアウトが生成されていない場合レイアウトを作成する
        if (convertView == null) {
            // レイアウトファイルからViewを生成する
            view = mLayoutInflater.inflate(R.layout.custom_list_item, parent, false);

        } else {
            // レイアウトが存在する場合は使いまわす
            view = convertView;
        }

        // リストアイテムに対応するデータを取得する
        String item = getItem(position);

        // 各Viewに表示する情報を設定
        TextView text1 = (TextView) view.findViewById(R.id.TitleText);
        text1.setText("Title:" + item);
        TextView text2 = (TextView) view.findViewById(R.id.SubTitleText);
        text2.setText("SubTitle:" + item);

        return view;
    }

}
