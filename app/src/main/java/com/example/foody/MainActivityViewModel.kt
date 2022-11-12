package com.example.foody

import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foody.model.AlbumList
import com.example.foody.network.RetrofitClient
import com.example.foody.repository.MainActivityRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel: ViewModel()  {


    var recyclerListData: MutableLiveData<AlbumList> = MutableLiveData()


    fun getAlbum() : LiveData<AlbumList> {
        recyclerListData = MainActivityRepository.getAlbumsApiCall()
        return recyclerListData
    }

    fun searchUsers(searchText: String){
        MainActivityRepository.searchUser(searchText)
    }



}