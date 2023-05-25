package fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mobile.campuslive.Adapter.SliderItemsForVideo
import com.mobile.campuslive.Adapter.ViewPageAdapterForVideo
import com.mobile.campuslive.R
import com.mobile.campuslive.SliderItems
import com.mobile.campuslive.VerticalViewPage
import com.mobile.campuslive.ViewPageAdapter

class VideoFeed : Fragment() {
    private var mRef : DatabaseReference? = null
    private var video : ArrayList<String>? = null
    private var sliderItems = ArrayList<SliderItemsForVideo>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vedio_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val verticalViewPage: VerticalViewPage = view.findViewById(R.id.verticalViewPageInVideo)
        sliderItems.toArray()

        mRef = FirebaseDatabase.getInstance().getReference("feedVideo")

        mRef?.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children){
                    mRef?.keepSynced(true)
                    //title video if data is given....
                    val sliderItem = SliderItemsForVideo(ds.child("link").value.toString())
                    sliderItems.add(sliderItem)
                }
//                if(video!!.isNotEmpty()) {

                    verticalViewPage.adapter = ViewPageAdapterForVideo(
                        context!!,
                        sliderItems,
                        verticalViewPage
                    )
//                }

            }
            override fun onCancelled(error: DatabaseError) {

            }
        })




    }



}