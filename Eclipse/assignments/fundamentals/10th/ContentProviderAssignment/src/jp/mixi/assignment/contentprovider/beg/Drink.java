
package jp.mixi.assignment.contentprovider.beg;

import android.net.Uri;
import android.provider.BaseColumns;

public class Drink implements BaseColumns {

    @SuppressWarnings("unused")
    private static final String TAG = Drink.class.getSimpleName();

    // TODO:AUTHORITYを定義してください
    // 一意となる識別子にする
    public static final String AUTHORITY = "";

    // TODO:CONTENT_URIを定義してください
    public static final Uri CONTENT_URI = Uri.parse("");

    public static final String DRINK_TABLE_NAME = "drink";

    public static final String COLUMN_NAME_DRINK_NAME = "name";
    public static final String COLUMN_NAME_DRINK_PRICE = "price";

    private String mName;
    private int mPrice;

    public Drink(String name, int price) {
        super();
        mName = name;
        mPrice = price;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int price) {
        mPrice = price;
    }

}
