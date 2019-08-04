package org.androidtown.todolist_kotlin

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.MenuItem
import android.view.View
import android.widget.TimePicker
import kotlinx.android.synthetic.main.activity_today_todo_add.select_time
import kotlinx.android.synthetic.main.activity_tomorrow_todo_add.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class TomorrowTodoAddActivity : AppCompatActivity(), View.OnClickListener, TimePicker.OnTimeChangedListener {

    lateinit var time: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tomorrow_todo_add)

        setSupportActionBar(tomorrow_add_layout_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var c = Calendar.getInstance()
        var sdf = SimpleDateFormat("EEE, MMM dd", Locale.ENGLISH)
        c.add(Calendar.DATE, 1)
        tomorrow_toolbar_text.setText(sdf.format(c.time))

        tomorrow_select_time.setIs24HourView(true)
        tomorrow_select_time.setOnTimeChangedListener(this)

        tomorrow_todo_add_now_button.setOnClickListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onTimeChanged(view: TimePicker?, hourOfDay: Int, minute: Int) {
        if(hourOfDay <= 9)
            time = "0$hourOfDay:$minute"
        else
            time = "$hourOfDay:$minute"
    }

    override fun onClick(v: View?) {
        Snackbar.make(tomorrow_add_layout_toolbar, "ADD to TO DO LIST ?", Snackbar.LENGTH_SHORT).setAction("YES") {
            var todotext = tomorrow_todo_text.text.toString()
            val intent = Intent()
            intent.putExtra("todotext", todotext)
            intent.putExtra("todotime", time)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }.show()
    }

}
