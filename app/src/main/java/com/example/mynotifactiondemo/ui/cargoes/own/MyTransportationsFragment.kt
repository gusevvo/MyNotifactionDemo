package com.example.mynotifactiondemo.ui.cargoes.own

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynotifactiondemo.R
import com.example.mynotifactiondemo.data.api.dto.MyTransportationsResponseDto
import com.example.mynotifactiondemo.data.api.dto.MyTransportationsResponseItemDto
import com.example.mynotifactiondemo.viewmodel.MyTransportationsViewModel
import com.example.mynotifactiondemo.viewmodel.model.ViewModelResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_my_transportations.*
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyTransportationsFragment : Fragment() {

    private val myTransportationsViewModel: MyTransportationsViewModel by viewModels()
    private val adapter = MyTransportationsListAdapter()
    private var fetchMyTrasnportationsJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_transportations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        getMyTransportations()
    }

    private fun initAdapter() {
        transportations_recycler_view.adapter = adapter.withLoadStateHeaderAndFooter(
            header = MyTransportationsLoadStateAdapter { adapter.retry() },
            footer = MyTransportationsLoadStateAdapter { adapter.retry() }
        )
        transportations_recycler_view.layoutManager = LinearLayoutManager(activity)
    }

    private fun getMyTransportations() {
        fetchMyTrasnportationsJob?.cancel()
        fetchMyTrasnportationsJob = lifecycleScope.launch {
            myTransportationsViewModel.myTransportations.map { pagingData ->
                pagingData.map { map(it) }
            }.collectLatest {
                adapter.submitData(it)
            }
        }

        // Scroll to top when the list is refreshed from network.
        lifecycleScope.launch {
            adapter.loadStateFlow
                // Only emit when REFRESH LoadState for RemoteMediator changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where Remote REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { transportations_recycler_view.scrollToPosition(0) }
        }
    }

    private fun map(dto: MyTransportationsResponseItemDto) = MyTransportationListItemModel(
        id = dto.id,
        status = dto.status,
        numberAndStatusChangeDate = "${dto.number} от ${dto.statusChangeTime}",
        cost = dto.tariffWithVat.toString(),
        costWithoutVat = dto.tariff.toString(),
        statusText = "Статус",
        cityLoading = dto.cityLoading,
        cityUnloading = dto.cityUnloading,
        dateLoading = dto.dateLoading,
        dateUnloading = dto.dateUnloading,
        routeNodesCount = dto.routeNodesCount
    )
}