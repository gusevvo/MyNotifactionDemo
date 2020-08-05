package com.example.mynotifactiondemo.ui.cargoes.own.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.mynotifactiondemo.R
import com.example.mynotifactiondemo.common.mappingprofiles.toStatusColor
import com.example.mynotifactiondemo.common.mappingprofiles.toStatusText
import com.example.mynotifactiondemo.data.api.dto.MyTransportationResponseDto
import com.example.mynotifactiondemo.viewmodel.MyTransportationViewModel
import com.example.mynotifactiondemo.viewmodel.model.ViewModelResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_my_transportation_details.*

@AndroidEntryPoint
class MyTransportationDetailsFragment : Fragment() {

    private val args: MyTransportationDetailsFragmentArgs by navArgs()
    private val myTransportationViewModel: MyTransportationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_transportation_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myTransportationViewModel.getMyTransportation(args.id)

        myTransportationViewModel.myTransportation.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                ViewModelResult.Status.SUCCESS -> handleSuccess(result.getValueOrNull()!!)
                ViewModelResult.Status.FAILURE -> handleFailure(result.getFailureOrNull()!!)
                ViewModelResult.Status.LOADING -> handleLoading()
            }
        })

    }

    private fun handleSuccess(myTransportation: MyTransportationResponseDto) {
        hideProgressBar()
        order_number.text = "Заявка №${myTransportation.number}"
        order_date.text = "от ${myTransportation.forceDate}"
        contract_number_and_date.text = "к договору №${myTransportation.contract.number} от ${myTransportation.contract.number}"
        order_status.text = myTransportation.status.toStatusText()
        order_status.setTextColor(myTransportation.status.toStatusColor())
        customer_name.text = myTransportation.customer.name
        customer_tin.text = "ИНН ${myTransportation.customer.tin}"
        customer_trrc.text = "КПП ${myTransportation.customer.trrc}"
        carrier_name.text = myTransportation.carrier.name
        carrier_tin.text = "ИНН ${myTransportation.carrier.tin}"
        carrier_trrc.text = "КПП ${myTransportation.carrier.trrc}"
    }

    private fun handleFailure(failure: ViewModelResult.Failure) {
        hideProgressBar()
        Log.e("login", "Ошибка аутентификации", failure.throwable)
    }

    private fun handleLoading() {
        showProgressBar()
    }

    private fun showProgressBar() {
        scrollView.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        scrollView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }
}