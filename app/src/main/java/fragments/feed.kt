package fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.database.*
import com.google.firebase.messaging.FirebaseMessaging
import com.mobile.campuslive.*
import com.mobile.campuslive.R


class feed : Fragment() {

    private var mRef : DatabaseReference? = null
    private var sliderItems = ArrayList<SliderItems>()
    private var title = ArrayList<String>()
    private var desc = ArrayList<String>()
    private var images = ArrayList<String>()
    private var newsLink = ArrayList<String>()
    private var head = ArrayList<String>()
    private var videoView :ArrayList<String>? = null
    private var titleVideo : ArrayList<String>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val verticalViewPage: VerticalViewPage = view.findViewById(R.id.verticalViewPager1)
//        val verticalViewPage2: VerticalViewPage2 = findViewById<VerticalViewPage2>(R.id.verticalViewPager)

        mRef = FirebaseDatabase.getInstance().getReference("News")
        mRef!!.keepSynced(true)

        FirebaseMessaging.getInstance().subscribeToTopic("notification")
        mRef?.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children){
                    mRef?.keepSynced(true)
                    //title video if data is given....
                    titleVideo?.add(ds.child("titleVideo").value.toString())
                    videoView?.add(ds.child("VideoLink").value.toString())
                    //for the other news....
                    title.add(ds.child("title").value.toString())
                    desc.add(ds.child("desc").value.toString())
                    images.add(ds.child("imagelink").value.toString())
                    newsLink.add(ds.child("news link").value.toString())
                    head.add(ds.child("head").value.toString())
                    // Create a new SliderItems object for this image URL
                    val sliderItem = SliderItems(ds.child("imagelink").value.toString())
                    sliderItems.add(sliderItem)
                }
                if(images.isNotEmpty()) {
                        verticalViewPage.adapter = ViewPageAdapter(
                            context!!,
                            sliderItems,
                            title,
                            desc,
                            images,
                            newsLink,
                            head,
                            verticalViewPage
                        )
                }

            }
            override fun onCancelled(error: DatabaseError) {

            }
        })

    }
}