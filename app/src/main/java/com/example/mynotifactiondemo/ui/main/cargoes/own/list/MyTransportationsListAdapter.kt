package com.example.mynotifactiondemo.ui.main.cargoes.own.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotifactiondemo.R
import kotlinx.android.synthetic.main.fragment_my_transportations_list_item.view.*

class MyTransportationsListAdapter(
    private val onListItemClickListener: OnListItemClickListener
) : PagingDataAdapter<MyTransportationsListItemModel, MyTransportationsListAdapter.TransportationHolder>(
    COMPARATOR
) {

    interface OnListItemClickListener {
        fun onListItemClick(id: String)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<MyTransportationsListItemModel>() {
            override fun areItemsTheSame(
                oldItem: MyTransportationsListItemModel,
                newItem: MyTransportationsListItemModel
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: MyTransportationsListItemModel,
                newItem: MyTransportationsListItemModel
            ): Boolean =
                oldItem == newItem
        }
    }

    class TransportationHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v

        fun bind(
            itemModel: MyTransportationsListItemModel,
            onListItemClickListener: OnListItemClickListener
        ) {
            view.number_and_status_change_date.text = itemModel.numberAndStatusChangeDate
            view.cost.text = itemModel.cost
            view.cost_without_vat.text = itemModel.costWithoutVat
            view.status.text = itemModel.statusText
            view.status.setTextColor(itemModel.statusTextColor)
            view.city_loading.text = itemModel.cityLoading
            view.city_unloading.text = itemModel.cityUnloading
            view.date_loading.text = itemModel.dateLoading
            view.date_unloading.text = itemModel.dateUnloading
            view.loading_operation_number.text = "1"
            view.unloading_operation_number.text = itemModel.routeNodesCount.toString()
            view.setOnClickListener{
                onListItemClickListener.onListItemClick(itemModel.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransportationHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_my_transportations_list_item, parent, false)
        return TransportationHolder(view)
    }

    override fun onBindViewHolder(holder: TransportationHolder, position: Int) {
        val transportation = getItem(position);
        if (transportation != null) {
            holder.bind(transportation, onListItemClickListener)
        }
    }
}