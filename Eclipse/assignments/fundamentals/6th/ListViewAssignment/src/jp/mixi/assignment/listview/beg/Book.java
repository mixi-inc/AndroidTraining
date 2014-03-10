
package jp.mixi.assignment.listview.beg;

public class Book {

    @SuppressWarnings("unused")
    private static final String TAG = Book.class.getSimpleName();

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
