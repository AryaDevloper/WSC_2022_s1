package com.example.wsc_2022_s1

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.wsc_2022_s1.databinding.ActivityMain2Binding
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity2 : AppCompatActivity() {

    companion object {
        const val TICKETS = "tickets"
        const val EVENTS = "events"
        const val RECORDS = "records"
    }

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)



        val navController = findNavController(R.id.nav_host_fragment_content_main)

        NavigationUI.setupWithNavController(binding.bottomNav, navController)

    }


}