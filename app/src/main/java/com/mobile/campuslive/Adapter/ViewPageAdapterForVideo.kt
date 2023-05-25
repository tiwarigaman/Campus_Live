package com.mobile.campuslive.Adapter

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.MediaController
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mobile.campuslive.R
import com.mobile.campuslive.VerticalViewPage
import java.util.HashMap

class ViewPageAdapterForVideo(


    var context: Context,
    var sliderItems: ArrayList<SliderItemsForVideo>,

    var verticalViewPage: VerticalViewPage


) : PagerAdapter() {




    var mLayoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return sliderItems.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    @SuppressLint("ClickableViewAccessibility", "MissingInflatedId", "SetTextI18n", "NewApi",
        "ResourceAsColor"
    )
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = mLayoutInflater.inflate(R.layout.item_container_video, container, false)
//        var count = 0
//        var mRef : DatabaseReference = FirebaseDatabase.getInstance().getReference("bookmark")
        val video : VideoView = itemView.findViewById(R.id.Video_button)
        try {
            val mediaController = MediaController(context)
            mediaController.setAnchorView(video)
            video.setMediaController(mediaController)
            video.setVideoURI(Uri.parse(sliderItems[position].videoUrl))
            video.start()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Video playback failed", Toast.LENGTH_SHORT).show()
        }

        container.addView(itemView)
        return itemView

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

}