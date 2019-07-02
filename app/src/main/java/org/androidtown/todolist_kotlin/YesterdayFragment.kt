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

class YesterdayFragment : Fragment() {

    val yesterday_data: ArrayList<ListData> = ArrayList()
    lateinit var yesterday_recyclerView: RecyclerView
    lateinit var yesterday_none: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_yesterday, container, false)

        yesterday_none = view.findViewById(R.id.yesterday_todolist_none_text)
        yesterday_recyclerView = view.findViewById(R.id.yesterday_todolistview) as RecyclerView
        yesterday_recyclerView.layoutManager = LinearLayoutManager(requireContext())
        yesterday_recyclerView.adapter = ListViewAdapter(requireContext(), yesterday_data)

        if(ListViewAdapter(requireContext(), yesterday_data).itemCount == 0) {
            yesterday_none.visibility = View.VISIBLE
        }

        return view
    }
}
