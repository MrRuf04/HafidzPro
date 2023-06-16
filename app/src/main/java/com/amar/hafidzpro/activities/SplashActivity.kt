package com.amar.hafidzpro.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.amar.hafidzpro.R

class SplashActivity : AppCompatActivity() {
    //Deklarasi variabel timer Splash Screen muncul
    private val SPLASH_TIME_OUT:Long = 3000 // delay 3 detik
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        //kode untuk menjalankan main screen
        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}