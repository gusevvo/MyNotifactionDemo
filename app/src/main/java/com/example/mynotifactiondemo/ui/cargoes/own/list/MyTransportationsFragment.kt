package com.example.mynotifactiondemo.ui.cargoes.own.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynotifactiondemo.R
import com.example.mynotifactiondemo.common.mapping.Mapper
import com.example.mynotifactiondemo.viewmodel.MyTransportationsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_my_transportations.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MyTransportationsFragment : Fragment() {

    @Inject lateinit var mapper: Mapper
    private val myTransportationsViewModel: MyTransportationsViewModel by viewModels()

    private val adapter = MyTransportationsListAdapter( object: MyTransportationsListAdapter.OnListItemClickListener {
        override fun onListItemClick(id: String) {
            val action = MyTransportationsFragmentDirections.actionMyTransportationsFragmentToMyTransportationDetailsFragment(id)
            findNavController().navigate(action)
        }
    })

    private var fetchMyTrasnportationsJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_transportations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        getMyTransportations()
    }

    private fun initUI() {
        adapter.addLoadStateListener { loadState ->

            // Only show the list if refresh succeeds.
            transportations_recycler_view.isVisible = loadState.source.refresh is LoadState.NotLoading

            // Show loading spinner during initial load or refresh.
            progress_bar.isVisible = loadState.source.refresh is LoadState.Loading

            // Show the retry state if initial load or refresh fails.
            retry_button.isVisible = loadState.source.refresh is LoadState.Error

            // Toast on any error, regardless of whether it came from RemoteMediator or PagingSource
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                Toast.makeText(
                    this.requireContext(),
                    "\uD83D\uDE28 Wooops ${it.error}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        transportations_recycler_view.adapter = adapter.withLoadStateHeaderAndFooter(
            header = MyTransportationsLoadStateAdapter { adapter.retry() },
            footer = MyTransportationsLoadStateAdapter { adapter.retry() }
        )
        transportations_recycler_view.layoutManager = LinearLayoutManager(activity)

        retry_button.setOnClickListener { adapter.retry() }
    }

    private fun getMyTransportations() {
        fetchMyTrasnportationsJob?.cancel()
        fetchMyTrasnportationsJob = lifecycleScope.launch {
            myTransportationsViewModel.myTransportations.map { pagingData ->
                pagingData.map { mapper.map<MyTransportationsListItemModel>(it) }
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
}