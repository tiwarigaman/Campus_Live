package fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import com.mobile.campuslive.R
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.mobile.campuslive.Adapter.Faculty_recycler_view
import com.mobile.campuslive.civil.Faculty_recycler_view_ce
import com.mobile.campuslive.civil.facultyDataClass_ce
import com.mobile.campuslive.electrical.Faculty_recycler_view_ee
import com.mobile.campuslive.electrical.facultyDataClass_ee
import com.mobile.campuslive.facultyDataClass
import com.mobile.campuslive.mba.Faculty_recycler_mba
import com.mobile.campuslive.mba.facultyDataClass_mba
import com.mobile.campuslive.me.Faculty_recycler_view_me
import com.mobile.campuslive.me.facultyDataClass_me

class department : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerView_me: RecyclerView
    private lateinit var recyclerView_ee: RecyclerView
    private lateinit var recyclerView_ce: RecyclerView
    private lateinit var recyclerView_mba: RecyclerView
    private lateinit var Images : ArrayList<String>
    private lateinit var name : ArrayList<String>
    private lateinit var pos : ArrayList<String>
    private lateinit var newArrayList : ArrayList<facultyDataClass>
    private lateinit var Images_me : ArrayList<String>
    private lateinit var name_me : ArrayList<String>
    private lateinit var pos_me : ArrayList<String>
    private lateinit var newArrayList_me : ArrayList<facultyDataClass_me>
    private lateinit var Images_ee : ArrayList<String>
    private lateinit var name_ee : ArrayList<String>
    private lateinit var pos_ee : ArrayList<String>
    private lateinit var newArrayList_ee : ArrayList<facultyDataClass_ee>
    private lateinit var Images_ce : ArrayList<String>
    private lateinit var name_ce : ArrayList<String>
    private lateinit var pos_ce : ArrayList<String>
    private lateinit var newArrayList_ce : ArrayList<facultyDataClass_ce>
    private lateinit var Images_mba : ArrayList<String>
    private lateinit var name_mba : ArrayList<String>
    private lateinit var pos_mba : ArrayList<String>
    private lateinit var newArrayList_mba : ArrayList<facultyDataClass_mba>
    private var i : Int = 1
    private var j : Int = 1
    private var k : Int = 1
    private var l : Int = 1
    private var m : Int = 1
    private var mRef : DatabaseReference? = null
    private var mRef_me : DatabaseReference? = null
    private var mRef_ee : DatabaseReference? = null
    private var mRef_ce : DatabaseReference? = null
    private var mRef_mba : DatabaseReference? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_department, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Images = arrayListOf()
        name = arrayListOf()
        pos = arrayListOf()
        newArrayList = arrayListOf()
        Images_me = arrayListOf()
        name_me = arrayListOf()
        pos_me = arrayListOf()
        newArrayList_me = arrayListOf()
        Images_ee = arrayListOf()
        name_ee = arrayListOf()
        pos_ee = arrayListOf()
        newArrayList_ee = arrayListOf()
        Images_ce = arrayListOf()
        name_ce = arrayListOf()
        pos_ce = arrayListOf()
        newArrayList_ce = arrayListOf()
        Images_mba = arrayListOf()
        name_mba = arrayListOf()
        pos_mba = arrayListOf()
        newArrayList_mba = arrayListOf()
        mRef = FirebaseDatabase.getInstance().getReference("cse")
        mRef_me = FirebaseDatabase.getInstance().getReference("me")
        mRef_ee = FirebaseDatabase.getInstance().getReference("ee")
        mRef_ce = FirebaseDatabase.getInstance().getReference("ce")
        mRef_mba = FirebaseDatabase.getInstance().getReference("mba")


        ///goto fun
        val cseView : TextView = view.findViewById(R.id.cse_it_view)
        val cseView2 : LinearLayout = view.findViewById(R.id.cse_view2)
        val meView : TextView = view.findViewById(R.id.me_view)
        val meView2 : LinearLayout = view.findViewById(R.id.me_view2)
        val eeView : TextView = view.findViewById(R.id.ee_view)
        val eeView2 : LinearLayout = view.findViewById(R.id.ee_View2)
        val ceView : TextView = view.findViewById(R.id.ce_view)
        val ceView2 : LinearLayout = view.findViewById(R.id.ce_view2)
        val mbaView : TextView = view.findViewById(R.id.mba_view)
        val mbaView2 : LinearLayout = view.findViewById(R.id.mba_view2)
        val scrollView : ScrollView = view.findViewById(R.id.scroll_View)

        cseView.setOnClickListener {
            scrollView.smoothScrollTo(0, cseView2.top)
        }
        meView.setOnClickListener {
            scrollView.smoothScrollTo(0, meView2.top)
        }
        eeView.setOnClickListener {
            scrollView.smoothScrollTo(0, eeView2.top)
        }
        ceView.setOnClickListener {
            scrollView.smoothScrollTo(0, ceView2.top)
        }
        mbaView.setOnClickListener {
            scrollView.smoothScrollTo(0, mbaView2.top)
        }


        mRef?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                while (snapshot.hasChild("$i")){
                    Images.add(snapshot.child("$i").child("image").value.toString())
                    name.add(snapshot.child("$i").child("name").value.toString())
                    pos.add(snapshot.child("$i").child("pos").value.toString())
                    i++
                }
                getUserData()
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
        recyclerView = view.findViewById(R.id.recyclerView_of_faculty)
        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerView.setHasFixedSize(true)
        mRef_me?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                while (snapshot.hasChild("$j")){
                    Images_me.add(snapshot.child("$j").child("image").value.toString())
                    name_me.add(snapshot.child("$j").child("name").value.toString())
                    pos_me.add(snapshot.child("$j").child("pos").value.toString())
                    j++
                }
                getUserData_me()
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
        recyclerView_me = view.findViewById(R.id.recyclerView_of_faculty_me)
        recyclerView_me.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerView_me.setHasFixedSize(true)
        mRef_ee?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                while (snapshot.hasChild("$k")){
                    Images_ee.add(snapshot.child("$k").child("image").value.toString())
                    name_ee.add(snapshot.child("$k").child("name").value.toString())
                    pos_ee.add(snapshot.child("$k").child("pos").value.toString())
                    k++
                }
                getUserData_ee()
            }


            override fun onCancelled(error: DatabaseError) {

            }
        })
        recyclerView_ee = view.findViewById(R.id.recyclerView_of_faculty_ee)
        recyclerView_ee.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerView_ee.setHasFixedSize(true)
        mRef_ce?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                while (snapshot.hasChild("$l")){
                    Images_ce.add(snapshot.child("$l").child("image").value.toString())
                    name_ce.add(snapshot.child("$l").child("name").value.toString())
                    pos_ce.add(snapshot.child("$l").child("pos").value.toString())
                    l++
                }
                getUserData_ce()
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
        recyclerView_ce = view.findViewById(R.id.recyclerView_of_faculty_ce)
        recyclerView_ce.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerView_ce.setHasFixedSize(true)
        mRef_mba?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                while (snapshot.hasChild("$m")){
                    Images_mba.add(snapshot.child("$m").child("image").value.toString())
                    name_mba.add(snapshot.child("$m").child("name").value.toString())
                    pos_mba.add(snapshot.child("$m").child("pos").value.toString())
                    m++
                }
                getUserData_mba()
            }


            override fun onCancelled(error: DatabaseError) {

            }
        })
        recyclerView_mba = view.findViewById(R.id.recyclerView_of_faculty_mba)
        recyclerView_mba.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerView_mba.setHasFixedSize(true)
    }

    private fun getUserData_mba() {
        for(i in Images_mba.indices){
            val details = facultyDataClass_mba(Images_mba[i],name_mba[i],pos_mba[i])
            newArrayList_mba.add(details)
        }
        recyclerView_mba.adapter = Faculty_recycler_mba(newArrayList_mba)
    }

    private fun getUserData_ce() {
        for(i in Images_ce.indices){
            val details = facultyDataClass_ce(Images_ce[i],name_ce[i],pos_ce[i])
            newArrayList_ce.add(details)
        }
        recyclerView_ce.adapter = Faculty_recycler_view_ce(newArrayList_ce)
    }

    private fun getUserData_ee() {
        for(i in Images_ee.indices){
            val details = facultyDataClass_ee(Images_ee[i],name_ee[i],pos_ee[i])
            newArrayList_ee.add(details)
        }
        recyclerView_ee.adapter = Faculty_recycler_view_ee(newArrayList_ee)
    }

    private fun getUserData_me() {
        for(i in Images_me.indices){
            val details = facultyDataClass_me(Images_me[i],name_me[i],pos_me[i])
            newArrayList_me.add(details)
        }
        recyclerView_me.adapter = Faculty_recycler_view_me(newArrayList_me)
    }

    private fun getUserData() {
        for(i in Images.indices){
            val details = facultyDataClass(Images[i],name[i],pos[i])
            newArrayList.add(details)
        }
        recyclerView.adapter = Faculty_recycler_view(newArrayList)
    }
}