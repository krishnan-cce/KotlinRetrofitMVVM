package com.example.foody.model

import com.google.gson.annotations.SerializedName



class AlbumList : ArrayList<Album>()

data class Album(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)