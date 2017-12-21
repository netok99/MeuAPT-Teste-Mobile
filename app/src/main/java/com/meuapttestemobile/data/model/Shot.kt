package com.meuapttestemobile.data.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class Shot(val title: String, val images: Image, val views_count: Int, val created_at: Date,
                val description: String, val comments_count: Int) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readParcelable<Image>(Image::class.java.classLoader),
            source.readInt(),
            source.readSerializable() as Date,
            source.readString(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeParcelable(images, 0)
        writeInt(views_count)
        writeSerializable(created_at)
        writeString(description)
        writeInt(comments_count)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Shot> = object : Parcelable.Creator<Shot> {
            override fun createFromParcel(source: Parcel): Shot = Shot(source)
            override fun newArray(size: Int): Array<Shot?> = arrayOfNulls(size)
        }
    }
}
