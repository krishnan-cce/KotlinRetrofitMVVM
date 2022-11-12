package com.example.foody.network

import com.example.foody.model.AlbumList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("albums")
    fun getAlbumList(): Call<AlbumList>

    @GET("albums")
    fun searchAlbums(@Query("id") searchText: String): Call<AlbumList>


}