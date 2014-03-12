package jp.mixi.sample.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class MyAnotherParcelable implements Parcelable {
    public static final Creator<MyAnotherParcelable> CREATOR = new Creator<MyAnotherParcelable>() {
        @Override
        public MyAnotherParcelable[] newArray(int size) {
            return new MyAnotherParcelable[size];
        }

        @Override
        public MyAnotherParcelable createFromParcel(Parcel source) {
            return new MyAnotherParcelable(source);
        }
    };

    private int[] mMyIntegers;

    public MyAnotherParcelable() {
        mMyIntegers = new int[5];
    }

    public MyAnotherParcelable(Parcel source) {
        mMyIntegers = source.createIntArray();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeIntArray(mMyIntegers);
    }

    public int[] getMyIntegersArray() {
        return mMyIntegers;
    }

    public void setValueAt(int index, int value) {
        mMyIntegers[index] = value;
    }
}