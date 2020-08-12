package com.example.mynotifactiondemo.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mynotifactiondemo.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
}