package com.example.mynotifactiondemo.ui.limits

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynotifactiondemo.R
import kotlinx.android.synthetic.main.fragment_limits_list.*

class LimitsListFragment : Fragment() {

    private val limits = listOf(
        LimitListItemModel("Raising Arizona", 1987, false),
        LimitListItemModel("Vampire's Kiss", 1988,true),
        LimitListItemModel("Con Air", 1997, false),
        LimitListItemModel("Gone in 60 Seconds", 1997, true),
        LimitListItemModel("National Treasure", 2004,false),
        LimitListItemModel("The Wicker Man", 2006, false),
        LimitListItemModel("Ghost Rider", 2007, false),
        LimitListItemModel("Knowing", 2009, false)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_limits_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        limits_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = LimitsListAdapter(limits)
        }
    }
}