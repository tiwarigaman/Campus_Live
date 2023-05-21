package com.mobile.campuslive

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.*
import fragments.*

class newsDetailsActivity : AppCompatActivity() {
    private var mRef : DatabaseReference? = null
    private var mRefEvents : DatabaseReference? = null
    private var reference : DatabaseReference? = null
    private lateinit var tryAgain : Button

//    private var sliderItems = ArrayList<SliderItems>()
//    private var titleImage = ArrayList<String>()
//    private var desc = ArrayList<String>()
//    private var images = ArrayList<String>()
//    private var newsLink = ArrayList<String>()
//    private var head = ArrayList<String>()

    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var navigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private var yes : Boolean = false

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("MissingInflatedId", "CutPasteId", "RestrictedApi", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        replaceFragment(feed())
        window.setFlags(
            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED)
        mRef = FirebaseDatabase.getInstance().getReference("Events")
        mRefEvents = FirebaseDatabase.getInstance().getReference("upcoming")
        reference = FirebaseDatabase.getInstance().getReference("image_slider")
//        val about2 :CardView = findViewById(R.id.card_view_about)
//        val events2 :CardView = findViewById(R.id.card_view_events)
//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)


        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        drawerToggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
        mRefEvents?.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                yes = snapshot.hasChild("event")
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_ -> {
                    replaceFragment(home())
                }
                R.id.feed_ -> {
                    replaceFragment(feed())
                    Toast.makeText(this@newsDetailsActivity, "Feed is loading...", Toast.LENGTH_SHORT)
                        .show()
                }
                R.id.feed_video -> {
//                    replaceFragment(feed())
                    Toast.makeText(this@newsDetailsActivity, "Feed is loading...", Toast.LENGTH_SHORT)
                        .show()
                }
                R.id.events_->{
                    replaceFragment(event_fragment())
                    Toast.makeText(this@newsDetailsActivity, "Loading events...", Toast.LENGTH_SHORT)
                        .show()
                }
                R.id.about_->{
                    replaceFragment(aboutFragment())
                }
                R.id.upcoming_events->{
                    if (yes){
                        replaceFragment(upcomingEvent())
                    }else{
                        Toast.makeText(this@newsDetailsActivity,"No Upcoming events...",Toast.LENGTH_LONG).show()
                    }
                }
                R.id.department->{
                    replaceFragment(department())
                }
                R.id.placement->{
                    replaceFragment(PlacementFragment())
                }
                R.id.feedfack_->{
                    val emailIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_EMAIL, arrayOf("imentor.corp@gmail.com"))
                        putExtra(Intent.EXTRA_SUBJECT, "Feedback/Request")
                        putExtra(Intent.EXTRA_TEXT, "Please let us know your feedback/request here...Please note that your name will not be included in the feedback email, in order to ensure anonymity ")
                        `package` = "com.google.android.gm" // use the package name of a specific email app
                    }
                    startActivity(emailIntent)
                }
            }
            drawerLayout.closeDrawers() // close the drawer when an item is clicked
            true
        }

//        checkNetConnection()
    }
    private fun checkNetConnection() {
        val connectivityManager : ConnectivityManager = applicationContext?.getSystemService(
            AppCompatActivity.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val networkInfo : NetworkInfo? = connectivityManager.activeNetworkInfo
        val dialog = Dialog(this)
        if((networkInfo == null) || !networkInfo.isConnected || !networkInfo.isAvailable){
            Toast.makeText(this,"No Internet Connection!", Toast.LENGTH_SHORT).show()
            dialog.setContentView(R.layout.dialog)
            dialog.setCanceledOnTouchOutside(false)
            dialog.window?.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            tryAgain  = dialog.findViewById(R.id.try_again)
            tryAgain.setOnClickListener {
                recreate()
            }
            dialog.show()
        }else{
//                Toast.makeText(this,"Connection is Back.",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
    }


    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.frame_layout,fragment)
        fragmentTransition.commit()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else {
            super.onBackPressed()
            overridePendingTransition(R.anim.slider_in_right, R.anim.slide_out_left)
        }
    }

}