package com.example.mynotifactiondemo.ui.splash

import android.graphics.drawable.Animatable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.mynotifactiondemo.R
import com.example.mynotifactiondemo.viewmodel.LoginViewModel
import com.example.mynotifactiondemo.viewmodel.model.ViewModelResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_splash.*

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private val loginViewModel: LoginViewModel by activityViewModels()

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
//            todo: fix explicit delay
            Handler().postDelayed({ navigateToLoginFragment() }, 6000)
        }

//        loginViewModel.user.observe(viewLifecycleOwner, Observer { result ->
//            when (result.status) {
//                ViewModelResult.Status.SUCCESS -> navigateToLoginFragment()
////                ViewModelResult.Status.FAILURE -> navigateToLoginFragment()
////                    Toast.makeText(
////                    context,
////                    "\uD83D\uDE28 Wooops ${result.getFailureOrNull()!!.throwable}\nTry later.",
////                    Toast.LENGTH_LONG
////                ).show()
//                ViewModelResult.Status.LOADING -> Log.d("splash", "User loading")
//            }
//        })


    }

    private fun navigateToLoginFragment() {
        val toLoginFlow = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
        findNavController().navigate(toLoginFlow)
    }
}