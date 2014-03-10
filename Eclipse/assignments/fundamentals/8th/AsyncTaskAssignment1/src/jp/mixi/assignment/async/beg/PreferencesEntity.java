package jp.mixi.assignment.async.beg;

import android.os.Parcel;
import android.os.Parcelable;

public class PreferencesEntity implements Parcelable {
    public static final Creator<PreferencesEntity> CREATOR = new Creator<PreferencesEntity>() {
        @Override
        public PreferencesEntity[] newArray(int size) {
            return new PreferencesEntity[size];
        }

        @Override
        public PreferencesEntity createFromParcel(Parcel source) {
            return new PreferencesEntity(source);
        }
    };
    private String mHogeData;
    private int mFugaData;
    private boolean mIsPiyo;

    public PreferencesEntity() {
        mHogeData = "hoge";
        mFugaData = 1111;
        mIsPiyo = true;
    }

    public PreferencesEntity(String hoge, int fuga, boolean isPiyo) {
        mHogeData = hoge;
        mFugaData = fuga;
        mIsPiyo = isPiyo;
    }

    public PreferencesEntity(Parcel source) {
        mHogeData = source.readString();
        mFugaData = source.readInt();
        mIsPiyo = source.readInt() == 1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mHogeData);
        dest.writeInt(mFugaData);
        dest.writeInt(mIsPiyo ? 1 : 0);
    }

    public String getHoge() {
        return mHogeData;
    }

    public int getFuga() {
        return mFugaData;
    }

    public boolean isPiyo() {
        return mIsPiyo;
    }
}