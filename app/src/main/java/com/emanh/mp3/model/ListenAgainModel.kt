package com.emanh.mp3.model

import android.os.Parcel
import android.os.Parcelable

data class ListenAgainModel(
    val id: Int = 0,
    val logo: String = "",
    val name: String = "",
    val singer: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(logo)
        parcel.writeString(name)
        parcel.writeString(singer)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ListenAgainModel> {
        override fun createFromParcel(parcel: Parcel): ListenAgainModel {
            return ListenAgainModel(parcel)
        }

        override fun newArray(size: Int): Array<ListenAgainModel?> {
            return arrayOfNulls(size)
        }
    }
}
