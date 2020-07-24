package com.example.mynotifactiondemo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.mynotifactiondemo.R
import kotlinx.android.synthetic.main.fragment_launch.*

class LaunchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_launch, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_launch_button_next.setOnClickListener {  view: View ->
            view.findNavController().navigate(R.id.action_launchFragment_to_loginFragment)
        }
    }
}