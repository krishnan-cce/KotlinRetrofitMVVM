package com.example.foody.repository

import androidx.lifecycle.MutableLiveData
import com.example.foody.model.AlbumList
import com.example.foody.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MainActivityRepository {

    val albumList = MutableLiveData<AlbumList>()


    fun getAlbumsApiCall(): MutableLiveData<AlbumList> {
        val call = RetrofitClient.apiInterface.getAlbumList()
        call.enqueue(object : Callback<AlbumList> {
            override fun onFailure(call: Call<AlbumList>, t: Throwable) {
                albumList.postValue(null)
            }

            override fun onResponse(call: Call<AlbumList>, response: Response<AlbumList>) {
                if(response.isSuccessful) {
                    albumList.postValue(response.body())
                } else {
                    albumList.postValue(null)
                }
            }
        })
        return albumList
    }

    fun searchUser(searchText: String) {

        val retroInstance = RetrofitClient.apiInterface
        val call = retroInstance.searchAlbums(searchText)
        call.enqueue(object : Callback<AlbumList> {
            override fun onFailure(call: Call<AlbumList>, t: Throwable) {
                albumList.postValue(null)
            }

            override fun onResponse(call: Call<AlbumList>, response: Response<AlbumList>) {
                if(response.isSuccessful) {
                    albumList.postValue(response.body())
                } else {
                    albumList.postValue(null)
                }
            }
        })
    }
}