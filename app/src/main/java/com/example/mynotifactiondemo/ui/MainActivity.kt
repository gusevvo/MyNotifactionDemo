package com.example.mynotifactiondemo.ui

import android.app.NotificationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.mynotifactiondemo.R
import com.example.mynotifactiondemo.common.services.createChannel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigationView()
        createNotificationChanel()
    }

    private fun createNotificationChanel() {
        val notificationManager = ContextCompat.getSystemService(applicationContext, NotificationManager::class.java) as NotificationManager
        notificationManager.createChannel("my_chanel_id", "my_chanel")
    }

    private fun setupNavigationView() {
        val navController = findNavController(R.id.main_nav_host_fragment)
        NavigationUI.setupWithNavController(navigation_view, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, mainActivity)
    }

    override fun onSupportNavigateUp() =
        NavigationUI.navigateUp(findNavController(R.id.main_nav_host_fragment), mainActivity)

//    override fun onBackPressed() {
//        if (mainActivity.isDrawerOpen(GravityCompat.START)) {
//            mainActivity.closeDrawer(GravityCompat.START)
//        } else {
//            super.onBackPressed()
//        }
//    }
}