package com.example.mynotifactiondemo.ui.cargoes.own

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotifactiondemo.R
import kotlinx.android.synthetic.main.fragment_my_transportations_list_item.view.*

class TransportationsListAdapter(private val transportations: List<TransportationListItemModel>) :
    RecyclerView.Adapter<TransportationsListAdapter.TransportationHolder>() {

    class TransportationHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v

        fun bind(transportation: TransportationListItemModel) {
            view.number_and_status_change_date.text = transportation.numberAndStatusChangeDate
            view.cost.text = transportation.cost
            view.cost_without_vat.text = transportation.costWithoutVat

            view.status.text = transportation.statusText
            view.status.setTextColor(statusToColor(transportation.status))

            view.city_loading.text = transportation.cityLoading
            view.city_unloading.text = transportation.cityUnloading
            view.date_loading.text = transportation.dateLoading
            view.date_unloading.text = transportation.dateUnloading
            view.loading_operation_number.text = "1"
            view.unloading_operation_number.text = transportation.routeNodesCount.toString()
        }

        private fun statusToColor(status: Int): Int = when (status) {
            3 -> Color.RED
            7 -> Color.BLUE
            else -> Color.GRAY
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransportationHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_my_transportations_list_item, parent, false)
        return TransportationHolder(view)
    }

    override fun getItemCount(): Int = transportations.size

    override fun onBindViewHolder(holder: TransportationHolder, position: Int) {
        val transportation = transportations[position];
        holder.bind(transportation)
    }
}