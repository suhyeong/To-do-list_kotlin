package org.androidtown.todolist_kotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class TomorrowFragment : Fragment(), View.OnClickListener {

    val tomorrow_data: ArrayList<ListData> = ArrayList()
    lateinit var tomorrow_recyclerView: RecyclerView
    lateinit var tomorrow_none: TextView
    lateinit var tomorrow_add: FloatingActionButton

    lateinit var next_todo_text:String
    lateinit var next_todo_time:String

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

        tomorrow_add = view.findViewById(R.id.tomorrow_todo_add) as FloatingActionButton
        tomorrow_add.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View?) {
        val addIntent = Intent(requireContext(), TomorrowTodoAddActivity::class.java)
        startActivityForResult(addIntent, 2000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            when(requestCode) {
                2000-> {
                    next_todo_text = data!!.getStringExtra("todotext")
                    next_todo_time = data.getStringExtra("todotime")
                    tomorrow_data.add(ListData(next_todo_text, next_todo_time))
                    tomorrow_recyclerView.adapter?.notifyDataSetChanged()
                }
            }
        }
    }
}
