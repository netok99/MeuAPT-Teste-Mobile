package com.meuapttestemobile.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Shot implements Parcelable {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("images")
    @Expose
    private Image images;
    @SerializedName("views_count")
    @Expose
    private int viewsCount;
    @SerializedName("created_at")
    @Expose
    private Date createdAt;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("comments_count")
    @Expose
    private int commentsCount;

    public Shot(String title, Image images, int viewsCount, Date createdAt, String description,
                int commentsCount) {
        this.title = title;
        this.images = images;
        this.viewsCount = viewsCount;
        this.createdAt = createdAt;
        this.description = description;
        this.commentsCount = commentsCount;
    }

    public String getTitle() {
        return title;
    }

    public Image getImages() {
        return images;
    }

    public int getViewsCount() {
        return viewsCount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getDescription() {
        return description;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeParcelable(this.images, flags);
        dest.writeInt(this.viewsCount);
        dest.writeLong(this.createdAt != null ? this.createdAt.getTime() : -1);
        dest.writeString(this.description);
        dest.writeInt(this.commentsCount);
    }

    protected Shot(Parcel in) {
        this.title = in.readString();
        this.images = in.readParcelable(Image.class.getClassLoader());
        this.viewsCount = in.readInt();
        long tmpCreatedAt = in.readLong();
        this.createdAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
        this.description = in.readString();
        this.commentsCount = in.readInt();
    }

    public static final Parcelable.Creator<Shot> CREATOR = new Parcelable.Creator<Shot>() {
        @Override
        public Shot createFromParcel(Parcel source) {
            return new Shot(source);
        }

        @Override
        public Shot[] newArray(int size) {
            return new Shot[size];
        }
    };
}
