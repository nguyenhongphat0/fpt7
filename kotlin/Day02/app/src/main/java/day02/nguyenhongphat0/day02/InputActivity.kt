package day02.nguyenhongphat0.day02

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText

import kotlinx.android.synthetic.main.activity_input.*

class InputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val username = findViewById<EditText>(R.id.usernameInput).text.toString()
            intent.putExtra("username", username)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

}
