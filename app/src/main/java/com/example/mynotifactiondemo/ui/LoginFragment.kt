package com.example.mynotifactiondemo.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.mynotifactiondemo.R
import com.example.mynotifactiondemo.data.api.ApiClientInterface
import com.example.mynotifactiondemo.data.api.LoginRequestDto
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_launch.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import java.lang.Exception
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    @Inject
    lateinit var apiClient: ApiClientInterface

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_login_button_login.setOnClickListener { this.onLoginClick(it) }
    }

    private fun onLoginClick(view: View) {

        val requestDto = LoginRequestDto(userEmail.text.toString(), userPassword.text.toString(), "")

        runBlocking(Dispatchers.IO) {
            try {
                val v = apiClient.login(requestDto)
//                if (v.isSuccessful) {
//                    Log.i("login", v.body())
//                } else {
//                    Log.e("login", v.errorBody().toString())
//                }
            } catch (t: Throwable) {
                Log.e("login", "exception", t)
            }
        }


        view.findNavController().navigate(R.id.action_loginFragment_to_limitsListFragment)
    }
}