package com.example.mynotifactiondemo.ui

import android.app.NotificationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.mynotifactiondemo.R
import com.example.mynotifactiondemo.common.services.createChannel
import com.example.mynotifactiondemo.viewmodel.LoginViewModel
import com.example.mynotifactiondemo.viewmodel.model.ViewModelResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.navigation_view_header.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigationView()
        createNotificationChanel()

        observeUser()
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

    private fun observeUser() {

        loginViewModel.user.observe(this, Observer { result ->
            when (result.status) {
                ViewModelResult.Status.SUCCESS -> {
                    val user = result.getValueOrNull()!!
                    user_full_name.text = "${user.lastName} ${user.firstName} ${user.middleName}"
                    user_email.text = user.email
                }
                ViewModelResult.Status.FAILURE -> {
                    Toast.makeText(this, "\uD83D\uDE28 Wooops ${result.getFailureOrNull()!!.throwable}", Toast.LENGTH_LONG).show()
                }
                ViewModelResult.Status.LOADING -> Toast.makeText(this, "User data loading", Toast.LENGTH_LONG).show()
            }
        })

    }
}