package com.vinz.projectcontoh1.data.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    @GET("Fasilitas")
    fun getAllKategori() : Call<List<KategoriResponse>>

}