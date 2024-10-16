package com.emanh.mp3.model

import android.os.Parcel
import android.os.Parcelable

data class ChannelModel(
    var id: Int = 0,
    var avatarChannel: String = "",
    var nameChannel: String = "",
    var countFollow: Long = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readLong()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(avatarChannel)
        parcel.writeString(nameChannel)
        parcel.writeLong(countFollow)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ChannelModel> {
        override fun createFromParcel(parcel: Parcel): ChannelModel {
            return ChannelModel(parcel)
        }

        override fun newArray(size: Int): Array<ChannelModel?> {
            return arrayOfNulls(size)
        }
    }
}
