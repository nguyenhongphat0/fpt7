package com.example.day09

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val slides = listOf<Int>(
        R.drawable.pic1,
        R.drawable.pic2,
        R.drawable.pic3,
        R.drawable.pic4
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.setOnCreateContextMenuListener(this)
        button2.setOnCreateContextMenuListener { menu, _, _ ->
            menu.add(0, 0, 0, "Annonymous menu 1")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu!!.add(0, 0, 0, "Option 1")
        menu!!.add(0, 0, 0, "Option 2")
        menu!!.add(0, 0, 0, "Option 3")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        Toast.makeText(this, item!!.title, Toast.LENGTH_LONG).show()
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        menu!!.add(0,0,0, "Context 1")
        menu!!.add(0,0,0, "Context 2")
        menu!!.add(0,0,0, "Context 3")
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        Toast.makeText(this, item!!.title, Toast.LENGTH_LONG).show()
        return super.onContextItemSelected(item)
    }
}
