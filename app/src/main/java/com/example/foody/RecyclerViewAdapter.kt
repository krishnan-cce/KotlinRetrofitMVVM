package com.example.foody


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foody.model.Album
import kotlinx.android.synthetic.main.recycler_row.view.*

class RecyclerViewAdapter(private val clickListener: OnItemClickListener): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var userList = mutableListOf<Album>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row, parent, false)
        return MyViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //val user = userList[position]
        //holder.bind(user)
        holder.bind(userList[position])
        holder.itemView.setOnClickListener {
            clickListener.onItemEditCLick(userList[position])
        }
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textViewName = view.textViewName
        val textViewEmail = view.textViewEmail
        val textViewStats = view.textViewStats

        fun bind(data : Album) {
            textViewName.text = data.id.toString()
            textViewEmail.text = data.title
            textViewStats.text = data.userId.toString()
        }
    }

    interface OnItemClickListener {
        fun onItemEditCLick(user : Album)
    }
}