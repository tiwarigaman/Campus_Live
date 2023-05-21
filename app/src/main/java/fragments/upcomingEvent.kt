package fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.messaging.FirebaseMessaging
import com.mobile.campuslive.*
import com.mobile.campuslive.R

class upcomingEvent : Fragment() {
    private var mRef : DatabaseReference? = null
    private lateinit var Images : ArrayList<String>
    private lateinit var heading : ArrayList<String>
    private lateinit var newArrayList : ArrayList<NewsDataClass>
    private lateinit var recyclerView : RecyclerView

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
        heading = arrayListOf()
        newArrayList = arrayListOf()


        recyclerView = view.findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(context)

        mRef = FirebaseDatabase.getInstance().getReference("upcoming").child("event")
        mRef?.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                Images.clear()
                heading.clear()


                for (child in snapshot.children) {
                    if (child.hasChild("image")) {
                        Images.add(child.child("image").value.toString())
                        heading.add(child.child("title").value.toString())
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
        if (Images.isNotEmpty()) {
            for (i in Images.indices) {
                val news = NewsDataClass(Images[i], heading[i])
                newArrayList.add(news)
            }
        }
        recyclerView.adapter = MyAdapter(newArrayList)
    }
}

