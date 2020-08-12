package com.example.mynotifactiondemo.ui.splash

import android.graphics.drawable.Animatable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mynotifactiondemo.R
import kotlinx.android.synthetic.main.fragment_splash.*

class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            (splash_image.drawable as Animatable).start()
            //todo: fix explicit delay
            Handler().postDelayed({ navigateToLoginFragment() }, 6000)
        }
    }

    private fun navigateToLoginFragment() {
        val toLoginFlow = SplashFragmentDirections.actionSplashFragmentToLoginActivity()
        findNavController().navigate(toLoginFlow)
    }
}