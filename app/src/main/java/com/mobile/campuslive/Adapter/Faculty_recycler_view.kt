package com.mobile.campuslive.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobile.campuslive.R
import com.mobile.campuslive.facultyDataClass

class Faculty_recycler_view(private val facultyList: ArrayList<facultyDataClass>): RecyclerView.Adapter<com.mobile.campuslive.Adapter.Faculty_recycler_view.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.faculty_details,parent,false)
        return MyViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = facultyList[position]
        Glide.with(holder.itemView.context)
            .load(currentItem.image)
            .into(holder.facultyImage)
        holder.facultyName.text = currentItem.name
        holder.facultyPos.text = currentItem.pos
    }

    override fun getItemCount(): Int {
        return facultyList.size
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val facultyImage : ImageView = itemView.findViewById(R.id.faculty_image)
        val facultyName : TextView = itemView.findViewById(R.id.faculty_name)
        val facultyPos : TextView = itemView.findViewById(R.id.faculty_pos)
    }
}