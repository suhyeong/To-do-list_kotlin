package org.androidtown.todolist_kotlin

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface.*
import android.os.Build
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(main_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_drag_handle_black_24dp)

        SelectedButton(today_button)
        setFragment(TodayFragment())

        yesterday_button.setOnClickListener {
            SelectedButton(yesterday_button)
            unSelectedButton(today_button)
            unSelectedButton(tomorrow_button)
            setFragment(YesterdayFragment())
        }

        today_button.setOnClickListener {
            SelectedButton(today_button)
            unSelectedButton(yesterday_button)
            unSelectedButton(tomorrow_button)
            setFragment(TodayFragment())
        }

        tomorrow_button.setOnClickListener {
            SelectedButton(tomorrow_button)
            unSelectedButton(today_button)
            unSelectedButton(yesterday_button)
            setFragment(TomorrowFragment())
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

    private fun unSelectedButton(button: Button?) {
        button?.setTypeface(SANS_SERIF, NORMAL)
        button?.setBackgroundResource(R.color.white)
        button?.setTextColor(Color.parseColor("#CECECE"))
    }

    private fun SelectedButton(button: Button?) {
        button?.setTypeface(SANS_SERIF,BOLD)
        button?.setBackgroundResource(R.drawable.button_select)
        button?.setTextColor(Color.parseColor("#000000"))
    }

    private fun setFragment(fragment: Fragment?) {
        fragment?.let { supportFragmentManager.beginTransaction().replace(R.id.todo_fragment, it).commit() }
    }
}
