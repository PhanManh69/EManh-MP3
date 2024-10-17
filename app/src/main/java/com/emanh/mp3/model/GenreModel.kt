package com.emanh.mp3.model

import android.os.Parcel
import android.os.Parcelable

data class GenreModel(
    var id: Int = 0,
    var musicGenre: String = "",
    var song: ArrayList<SongModel> = ArrayList()
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.createTypedArrayList(SongModel.CREATOR) as ArrayList<SongModel>
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(musicGenre)
        parcel.writeTypedList(song)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GenreModel> {
        override fun createFromParcel(parcel: Parcel): GenreModel {
            return GenreModel(parcel)
        }

        override fun newArray(size: Int): Array<GenreModel?> {
            return arrayOfNulls(size)
        }
    }
}
