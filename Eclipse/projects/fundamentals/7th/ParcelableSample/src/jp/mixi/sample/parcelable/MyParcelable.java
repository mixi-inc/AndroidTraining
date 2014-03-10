package jp.mixi.sample.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class MyParcelable implements Parcelable {
    // 必ず、public で static かつ final な Creator<T> 型の CREATOR 定数オブジェクトを定義する
    // 修飾子を間違ったり、名前を間違ったりすると上手く機能しなくなる
    public static final Creator<MyParcelable> CREATOR = new Creator<MyParcelable>() {
        @Override
        public MyParcelable[] newArray(int size) {
            return new MyParcelable[size];
        }

        @Override
        public MyParcelable createFromParcel(Parcel source) {
            return new MyParcelable(source);
        }
    };

    private int mMyInt;
    private String mMyString;
    private List<String> mMyStringList;
    // Parcelable なオブジェクトの中で、Parcelable なオブジェクトも管理可能
    private List<MyAnotherParcelable> mMyAnotherParcelableList;

    public MyParcelable() {
        mMyInt = 4;
        mMyString = "hoge";
        mMyStringList = new ArrayList<String>();
        mMyAnotherParcelableList = new ArrayList<MyAnotherParcelable>();
    }

    // Parcel オブジェクトから復帰する時のインスタンス生成手段
    public MyParcelable(Parcel source) {
        // Parcel に書きだした時と同じ順番で読み出す必要がある
        // 順番を間違うと ClassCastException になり得る
        mMyInt = source.readInt();
        mMyString = source.readString();
        mMyStringList = source.createStringArrayList();
        mMyAnotherParcelableList = source.createTypedArrayList(MyAnotherParcelable.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0; // 通常はこのままで良い
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mMyInt);
        dest.writeString(mMyString);
        dest.writeStringList(mMyStringList);
        dest.<MyAnotherParcelable>writeTypedList(mMyAnotherParcelableList);
    }

    public int getMyInt() {
        return mMyInt;
    }

    public String getMyString() {
        return mMyString;
    }

    public List<String> getMyStringList() {
        return mMyStringList;
    }

    public List<MyAnotherParcelable> getMyAnotherParcelableList() {
        return mMyAnotherParcelableList;
    }
}