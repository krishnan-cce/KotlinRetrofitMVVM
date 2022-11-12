package com.example.foody

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foody.databinding.ActivityMainBinding
import com.example.foody.model.Album
import com.example.foody.model.AlbumList
import kotlinx.android.synthetic.main.activity_main.*

class  MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnItemClickListener {

    private lateinit var context: Context
    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    //private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        context = this@MainActivity


        initRecyclerView()
        initViewModel()
        searchUser()

    }
    private fun searchUser() {
        search_button.setOnClickListener {
            if(!TextUtils.isEmpty(searchEditText.text.toString())) {
                mainActivityViewModel.searchUsers(searchEditText.text.toString())
            } else {
                mainActivityViewModel.getAlbum()
            }
        }
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity)
            adapter = recyclerViewAdapter

        }
    }
    private fun initViewModel() {
        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        mainActivityViewModel.getAlbum().observe(this, Observer<AlbumList> {
            if (it == null) {
                Toast.makeText(this@MainActivity, "no result found...", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@MainActivity, "album found...", Toast.LENGTH_LONG).show()
                recyclerViewAdapter.userList = it.toMutableList()//.toMutableList()
                recyclerViewAdapter.notifyDataSetChanged()
            }
        })
        mainActivityViewModel.getAlbum()
    }

    override fun onItemEditCLick(user: Album) {
        //val intent = Intent(this@MainActivity, CreateNewUserActivity::class.java)
        //intent.putExtra("user_id", user.id)
        //startActivityForResult(intent, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == 1000) {
            mainActivityViewModel.getAlbum()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}