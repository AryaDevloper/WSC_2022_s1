package com.example.wsc_2022_s1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity2::class.java))
        finish()
    }
}