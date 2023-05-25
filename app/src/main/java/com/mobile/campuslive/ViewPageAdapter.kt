package com.mobile.campuslive

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView
import kotlin.math.abs


class ViewPageAdapter(


    var context: Context,
    var sliderItems: ArrayList<SliderItems>,
    var titles: ArrayList<String>,
    var desc: ArrayList<String>,
    var images: ArrayList<String>,
    var newsLink: ArrayList<String>,
    var head1: ArrayList<String>,
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
        "ResourceAsColor", "InflateParams"
    )
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = mLayoutInflater.inflate(R.layout.item_container, container, false)
//        var count = 0
//        var mRef : DatabaseReference = FirebaseDatabase.getInstance().getReference("bookmark")
        val imageView = itemView.findViewById<PhotoView>(R.id.image_view)
        val imageView2 = itemView.findViewById<ImageView>(R.id.imageView2)
        val title = itemView.findViewById<TextView>(R.id.headline)
        val descTv = itemView.findViewById<TextView>(R.id.desc)
        val head = itemView.findViewById<TextView>(R.id.head)


        val popupWindow = PopupWindow(context)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.popup_image, null)
        val popupImageView: PhotoView = popupView.findViewById(R.id.popup_image_view)
        Glide.with(context)
            .load(sliderItems[position].imageUrl)
            .into(popupImageView)

        imageView.setOnClickListener {
            popupWindow.apply {
                contentView = popupView
                width = LinearLayout.LayoutParams.MATCH_PARENT
                height = LinearLayout.LayoutParams.MATCH_PARENT
                isFocusable = true
                showAtLocation(imageView, Gravity.CENTER, 0, 0)
            }
        }



        title.text = titles[position]
        descTv.text = desc[position]
        head.text = head1[position]

        Glide.with(context)
                .load(sliderItems[position].imageUrl)
                .into(imageView)

        Glide.with(context).load(sliderItems[position].imageUrl).centerCrop().override(3, 3)
                .into(imageView2)

        container.addView(itemView)
        return itemView

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

}