package com.mobile.campuslive

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()



        val updateHandler = Handler()
        val runnable = Runnable {
            startActivity(Intent(this,newsDetailsActivity::class.java))
            finish()
             // some action(s)
        }
        updateHandler.postDelayed(runnable, 2500)



    }
}