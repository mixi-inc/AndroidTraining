
package jp.mixi.practice.database;

import android.provider.BaseColumns;

public class Book implements BaseColumns {

    @SuppressWarnings("unused")
    private static final String TAG = Book.class.getSimpleName();

    public static final String BOOK_TABLE_NAME = "book";

    public static final String COLUMN_NAME_BOOK_TITLE = "title";
    public static final String COLUMN_NAME_BOOK_PUBLISHER = "publisher";
    public static final String COLUMN_NAME_BOOK_PRICE = "price";

    private String title;
    private String publisher;
    private int price;

    public Book(String title, String publisher, int price) {
        super();
        this.title = title;
        this.publisher = publisher;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
