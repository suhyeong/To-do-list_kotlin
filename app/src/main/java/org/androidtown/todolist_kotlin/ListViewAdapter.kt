package org.androidtown.todolist_kotlin

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_list.view.*

class ListViewAdapter : RecyclerView.Adapter<ListViewAdapter.ViewHolder>() {

    var items : MutableList<ListData>? = mutableListOf(ListData("test1","13:00"), ListData("test2","09:00"))

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(p0)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        items?.get(p1).let { item ->
            with(p0) {
                title.text = item?.todo
                time.text = item?.time
            }
        }
    }

    class ViewHolder(parent: ViewGroup)
        : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)) {
        val title : TextView = itemView.todo_title
        val time: TextView = itemView.todo_time
    }
}