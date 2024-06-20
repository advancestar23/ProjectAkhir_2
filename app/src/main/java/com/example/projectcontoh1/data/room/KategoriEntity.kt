package com.vinz.projectcontoh1.data.room

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("kategori")
data class KategoriEntity(
    @PrimaryKey
    val id: Int,

    val categoryName: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(categoryName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<KategoriEntity> {
        override fun createFromParcel(parcel: Parcel): KategoriEntity {
            return KategoriEntity(parcel)
        }

        override fun newArray(size: Int): Array<KategoriEntity?> {
            return arrayOfNulls(size)
        }
    }
}
