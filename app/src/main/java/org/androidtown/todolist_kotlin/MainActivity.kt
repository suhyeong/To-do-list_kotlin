package org.androidtown.todolist_kotlin

import android.graphics.Typeface.BOLD
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.MenuItem
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val today_fragment = TodayFragment()
    val yesterday_fragment = YesterdayFragment()
    val tomorrow_fragment = TomorrowFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(main_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_drag_handle_black_24dp)

        yesterday_button.setOnClickListener {
            yesterday_button.setBackgroundResource(R.drawable.button_select)
            today_button.setBackgroundResource(R.color.transparency)
            tomorrow_button.setBackgroundResource(R.color.transparency)
            var temp = supportFragmentManager.beginTransaction()
            temp.replace(R.id.todo_fragment, yesterday_fragment)
            temp.commit()
        }
        today_button.setOnClickListener {
            today_button.setBackgroundResource(R.drawable.button_select)
            yesterday_button.setBackgroundResource(R.color.transparency)
            tomorrow_button.setBackgroundResource(R.color.transparency)
            var temp = supportFragmentManager.beginTransaction()
            temp.replace(R.id.todo_fragment, today_fragment)
            temp.commit()
        }
        tomorrow_button.setOnClickListener {
            tomorrow_button.setBackgroundResource(R.drawable.button_select)
            today_button.setBackgroundResource(R.color.transparency)
            yesterday_button.setBackgroundResource(R.color.transparency)
            var temp = supportFragmentManager.beginTransaction()
            temp.replace(R.id.todo_fragment, tomorrow_fragment)
            temp.commit()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId) {
            android.R.id.home->{
                Snackbar.make(main_toolbar, "메뉴 클릭!", Snackbar.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
