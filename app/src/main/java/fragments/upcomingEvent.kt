package fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.messaging.FirebaseMessaging
import com.mobile.campuslive.*
import com.mobile.campuslive.R

class upcomingEvent : Fragment() {
    private var mRef : DatabaseReference? = null
    private lateinit var Images : ArrayList<String>
    private lateinit var youtube : ArrayList<String>
    private lateinit var heading : ArrayList<String>
    private lateinit var heading2 : ArrayList<String>
    private lateinit var newArrayList : ArrayList<NewsDataClass>
    private lateinit var newArrayList2 : ArrayList<NewsDataClass2>
    private var i : Int = 0
    private lateinit var recyclerView : RecyclerView
    private lateinit var recyclerView2 : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FirebaseMessaging.getInstance().token
        FirebaseMessaging.getInstance().subscribeToTopic("upcoming")

        Images = arrayListOf()
        youtube = arrayListOf()
        heading = arrayListOf()
        heading2 = arrayListOf()
        newArrayList = arrayListOf()
        newArrayList2 = arrayListOf()

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView2 = view.findViewById(R.id.recyclerView2)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView2.layoutManager = LinearLayoutManager(context)
//        recyclerView.setHasFixedSize(true)
//        recyclerView2.setHasFixedSize(true)

        mRef = FirebaseDatabase.getInstance().getReference("upcoming").child("event")
        mRef?.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                Images.clear()
                youtube.clear()
                heading.clear()
                heading2.clear()

                for (child in snapshot.children) {
                    if (child.hasChild("image")) {
                        Images.add(child.child("image").value.toString())
                        heading.add(child.child("title").value.toString())
                    }

                }
                for (child in snapshot.children){
                    if (child.hasChild("youtube")){
                        youtube.add(child.child("youtube").value.toString())
                        heading2.add(child.child("title").value.toString())
                    }
                }

                getUserData()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun getUserData() {

        if (youtube.isNotEmpty()) {
            for (i in youtube.indices) {
                val news = NewsDataClass2(youtube[i], heading2[i])
                newArrayList2.add(news)
            }
            recyclerView2.adapter = MyAdapter2(newArrayList2)
        }

        if (Images.isNotEmpty()) {
            for (i in Images.indices) {
                val news = NewsDataClass(Images[i], heading[i])
                newArrayList.add(news)
            }
        }
        recyclerView.adapter = MyAdapter(newArrayList)
    }
}

