package fragments

import android.app.Dialog
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.firebase.messaging.FirebaseMessaging
import com.mobile.campuslive.SliderItems
import com.mobile.campuslive.VerticalViewPage
import com.mobile.campuslive.R
import com.mobile.campuslive.ViewPageAdapter

class event_fragment : Fragment() {
    private var mRef : DatabaseReference? = null
    private var sliderItems = ArrayList<SliderItems>()

    private var title = ArrayList<String>()
    private var desc = ArrayList<String>()
    private var images = ArrayList<String>()
    private var newsLink = ArrayList<String>()
    private var head = ArrayList<String>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FirebaseMessaging.getInstance().subscribeToTopic("event")


        mRef = FirebaseDatabase.getInstance().getReference("Events")
        val verticalViewPage: VerticalViewPage = view.findViewById(R.id.verticalViewPageInEvents)
        mRef?.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children){
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