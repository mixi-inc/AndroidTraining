package jp.mixi.sample.test.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class SampleDBEntity implements Parcelable {
    public static final Creator<SampleDBEntity> CREATOR = new Creator<SampleDBEntity>() {
        @Override
        public SampleDBEntity[] newArray(int size) {
            return new SampleDBEntity[size];
        }

        @Override
        public SampleDBEntity createFromParcel(Parcel source) {
            return new SampleDBEntity(source);
        }
    };

    private int mId;
    private String mName;

    public SampleDBEntity(int id, String name) {
        mId = id;
        mName = name;
    }

    public SampleDBEntity(Parcel source) {
        mId = source.readInt();
        mName = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }
}