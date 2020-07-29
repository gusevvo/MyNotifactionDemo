package com.example.mynotifactiondemo.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.mynotifactiondemo.R
import com.example.mynotifactiondemo.viewmodel.LoginViewModel
import com.example.mynotifactiondemo.viewmodel.model.ViewModelResult
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

        loginViewModel.authenticationState.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                ViewModelResult.Status.SUCCESS -> handleSuccess(result.getValueOrNull()!!)
                ViewModelResult.Status.FAILURE -> handleFailure(result.getFailureOrNull()!!)
                ViewModelResult.Status.LOADING -> handleLoading()
            }
        })

        fragment_login_button_login.setOnClickListener { this.onLoginClick(it) }
    }

    private fun onLoginClick(view: View) {
        loginViewModel.login(userEmail.text.toString(), userPassword.text.toString())
    }

    private fun handleSuccess(state: LoginViewModel.AuthenticationState) {
        hideProgressBar()
        when (state) {
            LoginViewModel.AuthenticationState.AUTHENTICATED -> navigateToLimitsListFragment()
            LoginViewModel.AuthenticationState.UNAUTHENTICATED -> Log.w("login", "Пользователь не аутентифицирован")
        }
    }

    private fun handleFailure(failure: ViewModelResult.Failure) {
        hideProgressBar()
        Log.e("login", "Ошибка аутентификации", failure.throwable)
    }

    private fun handleLoading() {
        showProgressBar()
    }

    private fun navigateToLimitsListFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_limitsListFragment)
    }

    private fun showProgressBar() {
        loginnGroup.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        loginnGroup.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }
}