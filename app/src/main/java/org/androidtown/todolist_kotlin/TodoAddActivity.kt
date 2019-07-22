package org.androidtown.todolist_kotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.NavUtils
import android.view.MenuItem
import android.view.View
import android.widget.TimePicker
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.main_toolbar
import kotlinx.android.synthetic.main.activity_todo_add.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.min

class TodoAddActivity : AppCompatActivity(), View.OnClickListener, TimePicker.OnTimeChangedListener {

    lateinit var time: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_add)

        setSupportActionBar(add_layout_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var today_date = LocalDate.now()
        var strnow = today_date.format(DateTimeFormatter.ofPattern("EEE, MMM dd", Locale.ENGLISH))
        toolbar_text.setText(strnow)

        select_time.setIs24HourView(true)
        select_time.setOnTimeChangedListener(this)

        todo_add_now_button.setOnClickListener(this)
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

    override fun onClick(v: View?) {
        Snackbar.make(add_layout_toolbar, "ADD to TO DO LIST ?", Snackbar.LENGTH_SHORT).setAction("YES") {
            var todotext = todo_text.text.toString()
            val intent = Intent()
            intent.putExtra("todotext", todotext)
            intent.putExtra("todotime", time)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }.show()
    }

    override fun onTimeChanged(view: TimePicker?, hourOfDay: Int, minute: Int) {
        if(hourOfDay <= 9)
            time = "0$hourOfDay:$minute"
        else
            time = "$hourOfDay:$minute"
    }
}
