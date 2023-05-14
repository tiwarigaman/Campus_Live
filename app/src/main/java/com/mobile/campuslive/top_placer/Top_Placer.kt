package com.mobile.campuslive.top_placer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobile.campuslive.R

class Top_Placer(private val facultyList: ArrayList<TopPlacerDataClass>): RecyclerView.Adapter<Top_Placer.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.top_placer_details,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = facultyList[position]
        Glide.with(holder.itemView.context)
            .load(currentItem.image)
            .into(holder.facultyImage)
        Glide.with(holder.itemView.context)
            .load(currentItem.com)
            .into(holder.facultyImage_com)
        holder.facultyName.text = currentItem.name
        holder.facultyPos.text = currentItem.pos
    }

    override fun getItemCount(): Int {
        return facultyList.size
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val facultyImage : ImageView = itemView.findViewById(R.id.top_rec_image)
        val facultyImage_com : ImageView = itemView.findViewById(R.id.top_rec_image_com)
        val facultyName : TextView = itemView.findViewById(R.id.top_rec_name)
        val facultyPos : TextView = itemView.findViewById(R.id.top_rec_pos)

    }
}