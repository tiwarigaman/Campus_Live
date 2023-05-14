package fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.mobile.campuslive.R
import com.google.firebase.database.*
import com.mobile.campuslive.hod.Faculty_recycler_view_hod
import com.mobile.campuslive.hod.facultyDataClass_hod


class home : Fragment() {
    private var mRef : DatabaseReference? = null
    private var reference : DatabaseReference? = null
    private var directorRef : DatabaseReference? = null
    private var directorRefPharm : DatabaseReference? = null
    private var mRef_hods : DatabaseReference? = null
    private lateinit var Images_hods : ArrayList<String>
    private lateinit var name_hods : ArrayList<String>
    private lateinit var pos_hods : ArrayList<String>
    private lateinit var newArrayList_hods : ArrayList<facultyDataClass_hod>
    private var i : Int = 1

    private lateinit var recyclerView_hod: RecyclerView


    private var imageslide = arrayListOf<SlideModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRef = FirebaseDatabase.getInstance().getReference("Events")
        reference = FirebaseDatabase.getInstance().getReference("image_slider")
        directorRef = FirebaseDatabase.getInstance().getReference("director")
        directorRefPharm = FirebaseDatabase.getInstance().getReference("directorPharm")
        mRef_hods = FirebaseDatabase.getInstance().getReference("hods")


//        val about2 : CardView = view.findViewById(R.id.card_view_about)
        val about2 : Button = view.findViewById(R.id.know_more)
        val directorName : TextView = view.findViewById(R.id.director_name)
        val directorNamePharm : TextView = view.findViewById(R.id.director_pharm_name)
        val directorMessage : TextView = view.findViewById(R.id.director_message)
        val directorMessagePharm : TextView = view.findViewById(R.id.director_pharm_message)
        val directorImage : ImageView = view.findViewById(R.id.director_image)
        val directorImagePharm : ImageView = view.findViewById(R.id.director_pharm_image)


        Images_hods = arrayListOf()
        name_hods = arrayListOf()
        pos_hods = arrayListOf()
        newArrayList_hods = arrayListOf()


        directorRef?.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.hasChild("image")){
                    val image = snapshot.child("image").value.toString()
                    Glide.with(context!!)
                        .load(image)
                        .centerCrop()
                        .into(directorImage)
                }
                if (snapshot.hasChild("name")){
                    val name = snapshot.child("name").value.toString()
                    directorName.text = name
                }
                if (snapshot.hasChild("message")){
                    val message = snapshot.child("message").value.toString()
                    directorMessage.text = message
                }
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })

        directorRefPharm?.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.hasChild("image")){
                    val image = snapshot.child("image").value.toString()
                    Glide.with(context!!)
                        .load(image)
                        .centerCrop()
                        .into(directorImagePharm)
                }
                if (snapshot.hasChild("name")){
                    val name = snapshot.child("name").value.toString()
                    directorNamePharm.text = name
                }
                if (snapshot.hasChild("message")){
                    val message = snapshot.child("message").value.toString()
                    directorMessagePharm.text = message
                }
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })


//        val events2 :CardView = view.findViewById(R.id.card_view_events)
        reference?.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children){
                    reference?.keepSynced(true)
                    if (ds.hasChild("title")) {
                        val sliderItem = SlideModel(
                            ds.child("imagelink").value.toString(), ds.child("title").value.toString()
                            , ScaleTypes.CENTER_CROP
                        )
                        imageslide.add(sliderItem)
                    }else{
                        val sliderItem = SlideModel(
                            ds.child("imagelink").value.toString(), ScaleTypes.CENTER_CROP
                        )
                        imageslide.add(sliderItem)
                    }
                }
                if(imageslide.isNotEmpty()){
                    val sliderLay = view.findViewById<ImageSlider>(R.id.sliderLayout)
                    sliderLay.setImageList(imageslide)
                }else{
                    Toast.makeText(context,"No! featured images.", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })

        about2.setOnClickListener {
            startActivity(Intent(context, com.mobile.campuslive.AboutActivityOfCampus::class.java))
        }
        mRef_hods?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                while (snapshot.hasChild("$i")){
                    Images_hods.add(snapshot.child("$i").child("image").value.toString())
                    name_hods.add(snapshot.child("$i").child("name").value.toString())
                    pos_hods.add(snapshot.child("$i").child("pos").value.toString())
                    i++
                }
                getUserData_hods()
            }


            override fun onCancelled(error: DatabaseError) {

            }
        })
        recyclerView_hod = view.findViewById(R.id.recyclerView_of_hods)
        recyclerView_hod.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL,false)
        recyclerView_hod.setHasFixedSize(true)

    }

    private fun getUserData_hods() {
        for(i in Images_hods.indices){
            val details = facultyDataClass_hod(Images_hods[i],name_hods[i],pos_hods[i])
            newArrayList_hods.add(details)
        }
        recyclerView_hod.adapter = Faculty_recycler_view_hod(newArrayList_hods)
    }
}