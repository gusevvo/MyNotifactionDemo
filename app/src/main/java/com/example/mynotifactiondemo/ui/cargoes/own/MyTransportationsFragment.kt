package com.example.mynotifactiondemo.ui.cargoes.own

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynotifactiondemo.R
import com.example.mynotifactiondemo.data.api.dto.MyTransportationsResponseDto
import com.example.mynotifactiondemo.viewmodel.MyTransportationsViewModel
import com.example.mynotifactiondemo.viewmodel.model.ViewModelResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_my_transportations.*

@AndroidEntryPoint
class MyTransportationsFragment : Fragment() {

    private val myTransportationsViewModel: MyTransportationsViewModel by viewModels()
    private lateinit var adapter: MyTransportationsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_transportations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupObserver()


    }

    private fun setupUI() {
        adapter = MyTransportationsListAdapter(arrayListOf())
        transportations_recycler_view.adapter = adapter
        transportations_recycler_view.layoutManager = LinearLayoutManager(activity)
//        transportations_recycler_view.apply {
//            layoutManager = LinearLayoutManager(activity)
//            adapter = adapter
//        }
    }

    private fun setupObserver() {
        myTransportationsViewModel.myTransportations.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                ViewModelResult.Status.SUCCESS -> handleSuccess(result.getValueOrNull()!!)
                ViewModelResult.Status.FAILURE -> handleFailure(result.getFailureOrNull()!!)
                ViewModelResult.Status.LOADING -> handleLoading()
            }
        })
    }

    private fun handleSuccess(myTransportations: MyTransportationsResponseDto) {
        val models = myTransportations.items.map { MyTransportationListItemModel(
            id = it.id,
            status = it.status,
            numberAndStatusChangeDate = "${it.number} от ${it.statusChangeTime}",
            cost = it.tariffWithVat.toString(),
            costWithoutVat = it.tariff.toString(),
            statusText = "Статус",
            cityLoading = it.cityLoading,
            cityUnloading = it.cityUnloading,
            dateLoading = it.dateLoading,
            dateUnloading = it.dateUnloading,
            routeNodesCount = it.routeNodesCount
        ) }
        adapter.addData(models)
        adapter.notifyDataSetChanged()
    }

    private fun handleFailure(failure: ViewModelResult.Failure) {
        //hideProgressBar()
        Log.e("my-transport", "Ошибка аутентификации", failure.throwable)
    }

    private fun handleLoading() {
        //showProgressBar()
        Log.i("my-transport", "Загрузка моего транспорта")

    }
}