package org.androidtown.todolist_kotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_list.view.*

class ListViewAdapter(val context: Context, val datalist: ArrayList<ListData>) : RecyclerView.Adapter<ListViewAdapter.ViewHolder>() {

    var items : MutableList<ListData>? = mutableListOf(ListData("test1","13:00"), ListData("test2","09:00"))

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        //return items!!.size
        return datalist.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(datalist[p1])
        /*items?.get(p1).let { item ->
            with(p0) {
                title.text = item?.todo
                time.text = item?.time
            }
        }*/
    }

    class ViewHolder(Itemview: View) : RecyclerView.ViewHolder(Itemview) {
        private val title: TextView = Itemview.findViewById(R.id.todo_title)
        private val time: TextView = Itemview.findViewById(R.id.todo_time)

        fun bind(data: ListData) {
            title.text = data.todo
            time.text = data.time
        }
    }
}