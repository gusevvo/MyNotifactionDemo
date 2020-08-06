package com.example.mynotifactiondemo.ui.cargoes.own.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotifactiondemo.R
import kotlinx.android.synthetic.main.fragment_my_transportations_load_state_footer.view.*

class MyTransportationsLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<MyTransportationsLoadStateAdapter.LoadStateViewHolder>() {

    class LoadStateViewHolder(
        v: View,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(v) {

        private var view: View = v

        init {
            view.retry_button.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                view.error_msg.text = loadState.error.localizedMessage
            }
            view.progress_bar.isVisible = loadState is LoadState.Loading
            view.retry_button.isVisible = loadState !is LoadState.Loading
            view.error_msg.isVisible = loadState !is LoadState.Loading
        }
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_my_transportations_load_state_footer, parent, false)
        return LoadStateViewHolder(
            view,
            retry
        )
    }
}