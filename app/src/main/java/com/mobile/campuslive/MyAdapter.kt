package com.mobile.campuslive

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter(private val newsList : ArrayList<NewsDataClass>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder1>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder1 {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return MyViewHolder1(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder1, position: Int) {

        val currentItem = newsList[position]
        Glide.with(holder.itemView.context)
            .load(currentItem.image)
            .into(holder.titleImage)
        holder.titleOfNews.text = currentItem.title
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class MyViewHolder1(itemView : View) : RecyclerView.ViewHolder(itemView){

        val titleImage : ImageView = itemView.findViewById(R.id.image_view_recycler)
        val titleOfNews : TextView = itemView.findViewById(R.id.text_recycler)

    }
}