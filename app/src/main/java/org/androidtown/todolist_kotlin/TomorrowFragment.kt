package org.androidtown.todolist_kotlin

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_today.*
import kotlinx.android.synthetic.main.fragment_tomorrow.*

class TomorrowFragment : Fragment() {

    val tomorrow_data: ArrayList<ListData> = ArrayList()
    lateinit var tomorrow_recyclerView: RecyclerView
    lateinit var tomorrow_none: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tomorrow, container, false)

        tomorrow_none = view.findViewById(R.id.tomorrow_todolist_none_text)
        tomorrow_recyclerView = view.findViewById(R.id.tomorrow_todolistview) as RecyclerView
        tomorrow_recyclerView.layoutManager = LinearLayoutManager(requireContext())
        tomorrow_recyclerView.adapter = ListViewAdapter(requireContext(), tomorrow_data)

        if(ListViewAdapter(requireContext(), tomorrow_data).itemCount == 0) {
           tomorrow_none.visibility = View.VISIBLE
        }

        return view
    }
}
