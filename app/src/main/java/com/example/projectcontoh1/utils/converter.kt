package com.vinz.projectcontoh1.utils

import com.vinz.projectcontoh1.data.retrofit.KategoriResponse
import com.vinz.projectcontoh1.data.room.KategoriEntity

fun List<KategoriResponse>.toListKategoriEntity(): List<KategoriEntity> =
    map{
        KategoriEntity(
            id = it.id.toInt(),
            categoryName = it.name
        )
    }

fun List<KategoriEntity>.toListKategoriResponse() : List<KategoriResponse> =
    map{
        KategoriResponse(
            id = it.id.toString(),
            name = it.categoryName
        )
    }

fun KategoriEntity.toKategoriResponse() : KategoriResponse =
    KategoriResponse(
        id = id.toString(),
        name = categoryName
    )