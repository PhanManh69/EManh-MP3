package com.emanh.mp3.model

import android.os.Parcel
import android.os.Parcelable

data class LibraryModel(
    var id: Int = 0,
    var nameUser: String = "",
    var avatarUser: String = "",
    var nameLibrary: String = "",
    var song: ArrayList<SongModel> = ArrayList()
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.createTypedArrayList(SongModel.CREATOR) as ArrayList<SongModel>,
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nameUser)
        parcel.writeString(avatarUser)
        parcel.writeString(nameLibrary)
        parcel.writeTypedList(song)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LibraryModel> {
        override fun createFromParcel(parcel: Parcel): LibraryModel {
            return LibraryModel(parcel)
        }

        override fun newArray(size: Int): Array<LibraryModel?> {
            return arrayOfNulls(size)
        }
    }
}
