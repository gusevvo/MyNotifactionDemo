package com.example.mynotifactiondemo.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.mynotifactiondemo.R
import com.example.mynotifactiondemo.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewModel.authenticationState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                LoginViewModel.AuthenticationStates.AUTHENTICATED -> navigateToLimitsListFragment()
                LoginViewModel.AuthenticationStates.UNAUTHENTICATED -> Log.e("login", "не аутентифицирован")
                LoginViewModel.AuthenticationStates.INVALID_AUTHENTICATION -> Log.e("login", "ошибка аутентификации")
            }
        })

        fragment_login_button_login.setOnClickListener { this.onLoginClick(it) }
    }

    private fun navigateToLimitsListFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_limitsListFragment)
    }

    private fun onLoginClick(view: View) {
        loginViewModel.login(userEmail.text.toString(), userPassword.text.toString())
    }
}