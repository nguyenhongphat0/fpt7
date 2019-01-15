package day02.nguyenhongphat0.day02

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val enterButton = findViewById<Button>(R.id.enterButton)
        enterButton.setOnClickListener { view ->
            run {
                val intent = Intent(this, InputActivity::class.java)
                startActivityForResult(intent, 1)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val welcomeMessage = findViewById<TextView>(R.id.welcomeMessage)
            welcomeMessage.text = "Welcome " + data!!.getStringExtra("username")
        }
        super.onActivityResult(requestCode, resultCode, data)

    }
}
