package com.example.mynotifactiondemo.ui.cargoes.own

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotifactiondemo.R
import kotlinx.android.synthetic.main.fragment_my_transportations_list_item.view.*

class MyTransportationsListAdapter(private val myTransportations: MutableList<MyTransportationListItemModel>) :
    RecyclerView.Adapter<MyTransportationsListAdapter.TransportationHolder>() {

    class TransportationHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v

        fun bind(myTransportation: MyTransportationListItemModel) {
            view.number_and_status_change_date.text = myTransportation.numberAndStatusChangeDate
            view.cost.text = myTransportation.cost
            view.cost_without_vat.text = myTransportation.costWithoutVat

            view.status.text = myTransportation.statusText
            view.status.setTextColor(statusToColor(myTransportation.status))

            view.city_loading.text = myTransportation.cityLoading
            view.city_unloading.text = myTransportation.cityUnloading
            view.date_loading.text = myTransportation.dateLoading
            view.date_unloading.text = myTransportation.dateUnloading
            view.loading_operation_number.text = "1"
            view.unloading_operation_number.text = myTransportation.routeNodesCount.toString()
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

    override fun getItemCount(): Int = myTransportations.size

    override fun onBindViewHolder(holder: TransportationHolder, position: Int) {
        val transportation = myTransportations[position];
        holder.bind(transportation)
    }

    fun addData(list: Collection<MyTransportationListItemModel>) {
        myTransportations.addAll(list)
    }
}