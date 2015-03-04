package jp.mixi.assignment.messagingandnotification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * ViewHolderパターンで実装
 * Created by Hideyuki.Kikuma on 2015/02/18.
 */
public class ClickableAdapter extends BindableAdapter<ClickableAdapter.ClickableItem> {
    private static final int LAYOUT_ID = android.R.layout.simple_expandable_list_item_1;
    public ClickableAdapter(Context context, List<ClickableItem> items) {
        super(context, items);
    }

    @Override
    public View newView(LayoutInflater inflater, int position, ViewGroup container) {
        View view = inflater.inflate(LAYOUT_ID, container, false);
        ViewHolder holder = new ViewHolder(view);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(ClickableItem item, int position, View view) {
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.mTitle.setText(item.mTitle);
        view.setOnClickListener(item.mClickListener);
    }

    private static class ViewHolder {
        private TextView mTitle;

        ViewHolder(View view) {
            mTitle = (TextView) view.findViewById(android.R.id.text1);
        }
    }

    public static class ClickableItem {
        private String mTitle;
        private View.OnClickListener mClickListener;

        public ClickableItem(String title, View.OnClickListener onClickListener) {
            mTitle = title;
            mClickListener = onClickListener;
        }
    }

}
