package com.amar.hafidzpro.activities


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.amar.hafidzpro.R

class GetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get)
        supportActionBar?.hide()

        val btn: Button = findViewById(R.id.btn_get)
        btn.setOnClickListener { btn() }

    }
    fun btn() {
        startActivity(Intent(this, SplashActivity::class.java))
    }
}