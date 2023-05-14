package fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.campuslive.R
import com.google.firebase.database.*
import com.mobile.campuslive.recent_placer.RecentPlacerDataClass
import com.mobile.campuslive.recent_placer.recent_placer
import com.mobile.campuslive.top_placer.TopPlacerDataClass
import com.mobile.campuslive.top_placer.Top_Placer


class PlacementFragment : Fragment() {

    private lateinit var Images_top : ArrayList<String>
    private lateinit var Images_top_com : ArrayList<String>
    private lateinit var name_top : ArrayList<String>
    private lateinit var pos_top : ArrayList<String>
    private lateinit var newArrayList_top : ArrayList<TopPlacerDataClass>
    private lateinit var recyclerView_top : RecyclerView
    private var i : Int = 1
    private var mRef_top : DatabaseReference? = null
    private lateinit var Images_rec : ArrayList<String>
    private lateinit var Images_rec_com : ArrayList<String>
    private lateinit var name_rec : ArrayList<String>
    private lateinit var pos_rec : ArrayList<String>
    private lateinit var newArrayList_rec : ArrayList<RecentPlacerDataClass>
    private lateinit var recyclerView_rec : RecyclerView
    private var j : Int = 1
    private var mRef_rec : DatabaseReference? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_placement, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Images_top = arrayListOf()
        Images_top_com = arrayListOf()
        name_top = arrayListOf()
        pos_top = arrayListOf()
        newArrayList_top = arrayListOf()
        mRef_top = FirebaseDatabase.getInstance().getReference("placement").child("top")
        Images_rec = arrayListOf()
        Images_rec_com = arrayListOf()
        name_rec = arrayListOf()
        pos_rec = arrayListOf()
        newArrayList_rec = arrayListOf()
        mRef_rec = FirebaseDatabase.getInstance().getReference("placement").child("recent")
        mRef_top?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                while (snapshot.hasChild("$i")){
                    Images_top.add(snapshot.child("$i").child("image").value.toString())
                    Images_top_com.add(snapshot.child("$i").child("com").value.toString())
                    name_top.add(snapshot.child("$i").child("name").value.toString())
                    pos_top.add(snapshot.child("$i").child("pos").value.toString())
                    i++
                }
                getUserData()
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
        recyclerView_top = view.findViewById(R.id.recyclerView_placement_top)
        recyclerView_top.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL,false)
        recyclerView_top.setHasFixedSize(true)

        //recent
        mRef_rec?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                while (snapshot.hasChild("$j")){
                    Images_rec.add(snapshot.child("$j").child("image").value.toString())
                    Images_rec_com.add(snapshot.child("$j").child("com").value.toString())
                    name_rec.add(snapshot.child("$j").child("name").value.toString())
                    pos_rec.add(snapshot.child("$j").child("pos").value.toString())
                    j++
                }
                getUserData_rec()
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
        recyclerView_rec = view.findViewById(R.id.recyclerView_placement_recently)
        recyclerView_rec.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL,false)
        recyclerView_rec.setHasFixedSize(true)

    }

    private fun getUserData_rec() {
        for(i in Images_rec.indices){
            val details = RecentPlacerDataClass(Images_rec[i],name_rec[i],pos_rec[i],Images_rec_com[i])
            newArrayList_rec.add(details)
        }
        recyclerView_rec.adapter = recent_placer(newArrayList_rec)
    }

    private fun getUserData() {
        for(i in Images_top.indices){
            val details = TopPlacerDataClass(Images_top[i],name_top[i],pos_top[i],Images_top_com[i])
            newArrayList_top.add(details)
        }
        recyclerView_top.adapter = Top_Placer(newArrayList_top)
    }
}