package com.example.mynotifactiondemo.ui.cargoes.own

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynotifactiondemo.R
import kotlinx.android.synthetic.main.fragment_my_transportations.*
import java.util.*

class MyTransportationsFragment : Fragment() {

    private val transportations = listOf(
        TransportationListItemModel(
            UUID.randomUUID(),
            "СП157523/2",
            "70 000,00 ₽",
            "58 333,33 ₽ (без НДС)",
            3,
            "Требуется переподписание",
            "Уфа",
            "Санкт-Петербург",
            "09.07.2020 08:00",
            "13.07.2020 22:00",
            3
        ),
        TransportationListItemModel(
            UUID.randomUUID(),
            "СП157523/2",
            "70 000,00 ₽",
            "58 333,33 ₽ (без НДС)",
            7,
            "На исполнении",
            "Уфа",
            "Санкт-Петербург",
            "09.07.2020 08:00",
            "13.07.2020 22:00",
            3
        ),
        TransportationListItemModel(
            UUID.randomUUID(),
            "СП157523/2",
            "70 000,00 ₽",
            "58 333,33 ₽ (без НДС)",
            3,
            "Требуется переподписание",
            "Уфа",
            "Санкт-Петербург",
            "09.07.2020 08:00",
            "13.07.2020 22:00",
            3
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_transportations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transportations_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = TransportationsListAdapter(transportations)
        }
    }
}