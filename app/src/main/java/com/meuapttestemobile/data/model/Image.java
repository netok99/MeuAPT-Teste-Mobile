package com.meuapttestemobile.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image implements Parcelable {

    @SerializedName("hidpi")
    @Expose
    private final String hidpi;
    @SerializedName("normal")
    @Expose
    private final String normal;

    public Image(String hidpi, String normal) {
        this.hidpi = hidpi;
        this.normal = normal;
    }

    public String getHidpi() {
        return hidpi;
    }

    public String getNormal() {
        return normal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.hidpi);
        dest.writeString(this.normal);
    }

    protected Image(Parcel in) {
        this.hidpi = in.readString();
        this.normal = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}
