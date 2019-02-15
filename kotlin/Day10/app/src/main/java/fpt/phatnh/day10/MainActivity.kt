package fpt.phatnh.day10

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreferences = getSharedPreferences("fpt.phatnh.day10_preferences", Context.MODE_PRIVATE)
        loadPreferenceButton.setOnClickListener {
            startActivity(Intent(this, PreferenceActivity::class.java))
        }
        viewPreferenceButton.setOnClickListener {
            Toast.makeText(this, sharedPreferences.getString("username", "This is the default value"), Toast.LENGTH_LONG).show()
        }
        updatePreferenceButton.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.putString("username", editText.text.toString())
            editor.commit()
        }
    }
}
