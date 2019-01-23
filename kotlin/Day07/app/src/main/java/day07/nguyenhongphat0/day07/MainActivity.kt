package day07.nguyenhongphat0.day07

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.ActionBar
import android.widget.Toast

class MainActivity : AppCompatActivity(), ActionBar.TabListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.navigationMode = ActionBar.NAVIGATION_MODE_TABS
        for (i in 1..5) {
            val tab = supportActionBar!!.newTab()
            tab.text = "Tab $i"
            tab.setTabListener(this)
            supportActionBar!!.addTab(tab)
        }
//        supportActionBar!!.displayOptions = 1
    }

    override fun onTabReselected(p0: ActionBar.Tab?, p1: FragmentTransaction?) {
    }

    override fun onTabUnselected(p0: ActionBar.Tab?, p1: FragmentTransaction?) {
    }

    override fun onTabSelected(p0: ActionBar.Tab?, p1: FragmentTransaction?) {
        val fragment = TabFragment()
        fragment.arguments = Bundle().apply {
            putString("index", p0!!.text.toString())
        }
        p1!!.replace(android.R.id.content, fragment)
    }

}
