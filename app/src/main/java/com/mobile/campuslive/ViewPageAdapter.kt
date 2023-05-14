package com.mobile.campuslive

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.HashMap

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

    var newPosition : Int = 0
    var x1 : Float = 0f
    var x2 : Float = 0f


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
        val itemView = mLayoutInflater.inflate(R.layout.item_container, container, false)
//        var count = 0
//        var mRef : DatabaseReference = FirebaseDatabase.getInstance().getReference("bookmark")
        val imageView = itemView.findViewById<ImageView>(R.id.image_view)
        val imageView2 = itemView.findViewById<ImageView>(R.id.imageView2)
        val title = itemView.findViewById<TextView>(R.id.headline)
        val descTv = itemView.findViewById<TextView>(R.id.desc)
        val head = itemView.findViewById<TextView>(R.id.head)
        // Create a new reference to the bookmarks node in the database
//        val database = FirebaseDatabase.getInstance()
//        val bookmarksRef = database.getReference("bookmarks")
//        // Generate a unique ID for the new bookmark
//        val bookmarkId = bookmarksRef.push().key!!


            title.text = titles[position]
            descTv.text = desc[position]
            head.text = head1[position]
//        title.setOnClickListener {
//            count++
//            if (count > 0 && count % 2 != 0) {
//                // Create a new bookmark object with the required fields
//                val userMap : HashMap<String, String> = HashMap()
//                userMap["id"] = bookmarkId
//                userMap["title"] = titles[position]
//                userMap["desc"] = desc[position]
//                userMap["imageUrl"] = sliderItems[position].imageUrl
//                userMap["newsLink"] = newsLink[position]
//                userMap["head"] = head1[position]
//
////                val bookmark = hashMapOf(
////                    "id" to bookmarkId,
////                    "title" to titles[position],
////                    "desc" to desc[position],
////                    "imageUrl" to sliderItems[position].imageUrl,
////                    "newsLink" to newsLink[position],
////                    "head" to head1[position]
////                )
//                // Add the bookmark to the database
//                bookmarksRef.child(bookmarkId).updateChildren(userMap as Map<String, Any>).addOnSuccessListener {
//                    Toast.makeText(context,"Bookmarked.",Toast.LENGTH_SHORT).show()
//                }
////                bookmarksRef.child(bookmarkId).setValue(bookmark).addOnSuccessListener {
////
////                }
//                title.setTextColor(ContextCompat.getColor(context, R.color.myCol))
//            } else {
//                // Create a reference to the bookmark node to be deleted
//                val bookmarkRef1 = database.getReference("bookmarks").child(bookmarkId)
//                // Remove the bookmark from the database
//                bookmarkRef1.removeValue().addOnSuccessListener {
//                    Toast.makeText(context,"Bookmark removed.",Toast.LENGTH_SHORT).show()
//                }
//                title.setTextColor(ContextCompat.getColor(context, R.color.myCol2))
////                title.setTextColor(title.textColors.defaultColor)
//            }
//        }

        Glide.with(context)
                .load(sliderItems[position].imageUrl)
                .into(imageView)

            Glide.with(context).load(sliderItems[position].imageUrl).centerCrop().
            override(3,3).
            into(imageView2)

        container.addView(itemView)
        return itemView

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

}