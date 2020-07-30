package com.example.mynotifactiondemo.ui.limits

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotifactiondemo.R
import kotlinx.android.synthetic.main.limits_item.view.*

class LimitsListAdapter(private val limitItems: List<LimitListItemModel>)
    : RecyclerView.Adapter<LimitsListAdapter.LimitHolder>() {

    class LimitHolder(v: View): RecyclerView.ViewHolder(v) {
        private var view: View = v

        fun bind(limit: LimitListItemModel) {
            view.limit_name.text = limit.name
            view.limit_volume.text = limit.volume.toString()
            view.limit_blocked.isChecked = !limit.blocked
            view.limit_blocked.text = when (limit.blocked) {
                true -> "Выключен"
                false -> "Включен"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LimitHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.limits_item, parent, false)
        return LimitHolder(view)
    }

    override fun getItemCount(): Int = limitItems.size

    override fun onBindViewHolder(holder: LimitHolder, position: Int) {
        val limitItem = limitItems[position];
        holder.bind(limitItem)
    }
}