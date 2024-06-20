package com.vinz.projectcontoh1.data.room

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AppEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val name: String,

    val kategori: String,
    val alamat:String,
    val harga:String,
    val kamarmandi:String,
    val kamartidur:String,
    val fasilitas:String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(kategori)
        parcel.writeString(alamat)
        parcel.writeString(harga)
        parcel.writeString(kamarmandi)
        parcel.writeString(kamartidur)
        parcel.writeString(fasilitas)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AppEntity> {
        override fun createFromParcel(parcel: Parcel): AppEntity {
            return AppEntity(parcel)
        }

        override fun newArray(size: Int): Array<AppEntity?> {
            return arrayOfNulls(size)
        }
    }
}