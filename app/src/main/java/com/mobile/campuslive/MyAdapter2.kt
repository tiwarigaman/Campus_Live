package com.mobile.campuslive

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class MyAdapter2(private val newsList : ArrayList<NewsDataClass2>) :
    RecyclerView.Adapter<MyAdapter2.MyViewHolder1>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder1 {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item2,parent,false)
        return MyViewHolder1(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder1, position: Int) {

        val currentItem = newsList[position]

        holder.youtube.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
//                val videoId = "S0Q4gqBUs7c"
                youTubePlayer.loadVideo(currentItem.videoId, 0f)
            }
        })
        holder.titleOfNews.text = currentItem.title
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class MyViewHolder1(itemView : View) : RecyclerView.ViewHolder(itemView){

        val youtube : YouTubePlayerView = itemView.findViewById(R.id.youtube)
        val titleOfNews : TextView = itemView.findViewById(R.id.text_recycler2)

    }
}