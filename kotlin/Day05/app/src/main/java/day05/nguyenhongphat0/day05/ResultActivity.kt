package day05.nguyenhongphat0.day05

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val bundle = intent.getBundleExtra("INFO")
        val gender = bundle.getString("gender")
        val dateOfBirth = bundle.getString("dateOfBirth")
        button.text = "Gender: $gender and Birthday: $dateOfBirth"
        button.setOnClickListener {
            this.finish()
        }
    }
}
