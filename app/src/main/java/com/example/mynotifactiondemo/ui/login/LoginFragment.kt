package com.example.mynotifactiondemo.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        loginButton.setOnClickListener { this.onLoginClick() }
    }

    private fun onLoginClick() {
        loginViewModel.login(userEmailInput.text.toString(), userPasswordInput.text.toString())
    }

    private fun handleSuccess(state: LoginViewModel.AuthenticationState) {
        hideProgressBar()
        when (state) {
            LoginViewModel.AuthenticationState.AUTHENTICATED -> {
                val toMainFlow = LoginFragmentDirections.actionGlobalMyTransportationsFragment()
                findNavController().navigate(toMainFlow)
            }
            LoginViewModel.AuthenticationState.UNAUTHENTICATED -> {
                Log.w("login", "Пользователь не аутентифицирован")
            }
        }
    }

    private fun handleFailure(failure: ViewModelResult.Failure) {
        hideProgressBar()
        Log.e("login", "Ошибка аутентификации", failure.throwable)
        Toast.makeText(context, failure.throwable.message, Toast.LENGTH_LONG).show()
    }

    private fun handleLoading() {
        showProgressBar()
    }

    private fun showProgressBar() {
        loginGroup.visibility = View.GONE
        loginProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        loginGroup.visibility = View.VISIBLE
        loginProgressBar.visibility = View.GONE
    }
}