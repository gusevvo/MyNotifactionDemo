package com.example.mynotifactiondemo.ui.main.cargoes.own.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mynotifactiondemo.R
import com.example.mynotifactiondemo.viewmodel.MyTransportationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_verification_code.*

@AndroidEntryPoint
class VerificationCodeFragment : DialogFragment() {

    private val args: VerificationCodeFragmentArgs by navArgs()
    private val myTransportationViewModel: MyTransportationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_verification_code, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cancelButton.setOnClickListener { findNavController().popBackStack() }

        signButton.setOnClickListener {
             val verificationCode = verificationCodeInput.text.toString()
             myTransportationViewModel.acceptMyTransportation(args.id, verificationCode)
            findNavController().popBackStack()
        }
    }
}