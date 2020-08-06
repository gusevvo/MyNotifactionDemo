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
import com.example.mynotifactiondemo.common.Mapper
import com.example.mynotifactiondemo.data.api.dto.MyTransportationResponseDto
import com.example.mynotifactiondemo.viewmodel.MyTransportationViewModel
import com.example.mynotifactiondemo.viewmodel.model.ViewModelResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.component_label_value_pair.view.*
import kotlinx.android.synthetic.main.fragment_my_transportation_details.*
import javax.inject.Inject

@AndroidEntryPoint
class MyTransportationDetailsFragment : Fragment() {
    @Inject
    lateinit var mapper: Mapper
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

        val model = mapper.map<MyTransportationDetailsModel>(myTransportation)

        order_number.text = model.orderNumber
        order_date.text = model.orderDate
        contract_number_and_date.text = model.contractNumberAndDate
        order_status.text = model.orderStatusText
        order_status.setTextColor(model.orderStatusTextColor)
        customer_bank_details.valueText.text = model.customerBankDetails
        carrier_bank_details.valueText.text = model.carrierBankDetails
        cargo_type.valueText.text = model.carrierBankDetails
        loading_type.valueText.text = model.carrierBankDetails
        trailer_volume.valueText.text = model.trailerVolume
        trailer_type.valueText.text = model.trailerType
        carrying_capacity.valueText.text = model.carryingCapacity
        cost_without_vat.valueText.text = model.costWithoutVat
        payment_due_date.valueText.text = model.paymentDueDate
        additional_requirements.valueText.text = model.additionalRequirements
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