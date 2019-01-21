package day06.nguyenhongphat0.day06

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL

        val title = TextView(this)
        title.text = "Welcome to dynamic UI!"
        title.layoutParams = params

        val number1 = EditText(this)
        val number2 = EditText(this)
        number2.id = R.id.editNumber2

        val button = Button(this)
        button.text = "SUM"
        button.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("number1", number1.text.toString().toInt())
            val editNumber2 = findViewById<EditText>(R.id.editNumber2)
            intent.putExtra("number2", number2.text.toString().toInt())
            startActivity(intent)
        }

        layout.addView(title)
        layout.addView(number1)
        layout.addView(number2)
        layout.addView(button)

        this.setContentView(layout, params)
    }
}
