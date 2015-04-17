package com.example.gmomedia.listviewsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by usr0200475 on 15/04/17.
 */

//public class CustomListItemAdapter extends ArrayAdapter<DummyContent.DummyItem> {


    public class CustomListItemAdapter extends ArrayAdapter<Weather.Detail> {


    private LayoutInflater mLayoutInflater;
    private RequestQueue mQueue;

//    public CustomListItemAdapter (Context context, List<DummyContent.DummyItem> objects){

    public CustomListItemAdapter (Context context, List<Weather.Detail> objects, RequestQueue queue){

        super (context,0,objects);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mQueue = queue;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

//        View view = null;
//        ViewHolder viewHolder;
        ItemLayout itemLayout;
        // ListViewに表示する分のレイアウトが生成されていない場合レイアウトを作成する
        if (convertView == null) {
            // レイアウトファイルからViewを生成する
             itemLayout = (ItemLayout)mLayoutInflater.inflate(R.layout.listitem_2, parent, false);
             itemLayout.setQueue(mQueue);

//            viewHolder = new ViewHolder();
//            viewHolder.text1 = (TextView) view.findViewById(R.id.id);
//            viewHolder.text2 = (TextView) view.findViewById(R.id.content);
//            view.setTag(viewHolder);

        } else {

// レイアウトが存在する場合は再利用する
//            view = convertView;
//            viewHolder = (ViewHolder) view.getTag();

              itemLayout = (ItemLayout) convertView;

        }

        // リストアイテムに対応するデータを取得する
        // DummyContent.DummyItem dummyItem = getItem(position);

        Weather.Detail detail = getItem(position);


        //itemLayout = (ItemLayout) convertView;
        //itemLayout.bindView(dummyItem);


        itemLayout.bindView(detail);



//        // 各Viewに表示する情報を設定
//        TextView text1 = (TextView) view.findViewById(R.id.id);
//        text1.setText("Title:" + item.getId());
//        TextView text2 = (TextView) view.findViewById(R.id.content);
//        text2.setText("SubTitle:" + item.getContent());

        return itemLayout;

    }

    static class ViewHolder{
        TextView text1;
        TextView text2;
    }
}
