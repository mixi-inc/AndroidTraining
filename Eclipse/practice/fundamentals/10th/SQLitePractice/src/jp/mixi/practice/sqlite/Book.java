
package jp.mixi.practice.sqlite;

import android.provider.BaseColumns;

public class Book implements BaseColumns {

    @SuppressWarnings("unused")
    private static final String TAG = Book.class.getSimpleName();

    public static final String BOOK_TABLE_NAME = "book";

    public static final String COLUMN_NAME_BOOK_TITLE = "title";
    public static final String COLUMN_NAME_BOOK_PUBLISHER = "publisher";
    public static final String COLUMN_NAME_BOOK_PRICE = "price";

    private String mTitle;
    private String mPublisher;
    private int mPrice;

    public Book(String title, String publisher, int price) {
        super();
        mTitle = title;
        mPublisher = publisher;
        mPrice = price;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getPublisher() {
        return mPublisher;
    }

    public void setPublisher(String publisher) {
        mPublisher = publisher;
    }

    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int price) {
        mPrice = price;
    }

}
