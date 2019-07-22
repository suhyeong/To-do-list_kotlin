package org.androidtown.todolist_kotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_today.*
import kotlinx.android.synthetic.main.fragment_today.view.*

class TodayFragment : Fragment(), View.OnClickListener {

    val today_data: ArrayList<ListData> = ArrayList()
    lateinit var today_recyclerView: RecyclerView
    lateinit var todo_none: TextView
    lateinit var today_add: FloatingActionButton

    lateinit var todo_text:String
    lateinit var todo_time:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_today, container, false)

        todo_none = view.findViewById(R.id.today_todolist_none_text) as TextView

        today_recyclerView = view.findViewById(R.id.today_todolistview) as RecyclerView
        today_recyclerView.layoutManager = LinearLayoutManager(requireContext())
        today_recyclerView.adapter = ListViewAdapter(requireContext(), today_data)

        if(ListViewAdapter(requireContext(), today_data).itemCount == 0) {
            todo_none.visibility = View.VISIBLE
        }

        today_add = view.findViewById(R.id.today_todo_add) as FloatingActionButton
        today_add.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View?) {
        val addIntent = Intent(requireContext(), TodoAddActivity::class.java)
        startActivityForResult(addIntent, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            when(requestCode) {
                1000-> {
                    todo_text = data!!.getStringExtra("todotext")
                    todo_time = data?.getStringExtra("todotime")
                    today_data.add(ListData(todo_text, todo_time))
                    today_recyclerView.adapter?.notifyDataSetChanged()
                }
            }
        }
    }
}
