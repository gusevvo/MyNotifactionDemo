package com.example.mynotifactiondemo.ui.cargoes.own

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotifactiondemo.R
import kotlinx.android.synthetic.main.fragment_my_transportations_list_item.view.*

class MyTransportationsListAdapter() :
    PagingDataAdapter<MyTransportationListItemModel, MyTransportationsListAdapter.TransportationHolder>(
        COMPARATOR
    ) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<MyTransportationListItemModel>() {
            override fun areItemsTheSame(
                oldItem: MyTransportationListItemModel,
                newItem: MyTransportationListItemModel
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: MyTransportationListItemModel,
                newItem: MyTransportationListItemModel
            ): Boolean =
                oldItem == newItem
        }
    }

    class TransportationHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v

        fun bind(myTransportation: MyTransportationListItemModel) {
            view.number_and_status_change_date.text = myTransportation.numberAndStatusChangeDate
            view.cost.text = myTransportation.cost
            view.cost_without_vat.text = myTransportation.costWithoutVat
            view.status.text = myTransportation.statusText
            view.status.setTextColor(myTransportation.statusTextColor)
            view.city_loading.text = myTransportation.cityLoading
            view.city_unloading.text = myTransportation.cityUnloading
            view.date_loading.text = myTransportation.dateLoading
            view.date_unloading.text = myTransportation.dateUnloading
            view.loading_operation_number.text = "1"
            view.unloading_operation_number.text = myTransportation.routeNodesCount.toString()
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
            holder.bind(transportation)
        }
    }
}