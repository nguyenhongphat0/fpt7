package day05.nguyenhongphat0.day05

import android.app.DatePickerDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

fun formatDate(year: Int, month: Int, dayOfMonth: Int): String {
    var month = month + 1
    return "$dayOfMonth/$month/$year"
}

class MainActivity : AppCompatActivity() {
    lateinit var dateOfBirth: String
    lateinit var gender: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getdateButton.setOnClickListener {
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener {view: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
                this.dateOfBirth = formatDate(month = month, year = year, dayOfMonth = dayOfMonth)
                dateText.text = dateOfBirth
            }, 2018, 1, 1).show()
        }

        val genders = listOf<String>("Male", "Female", "Other")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, genders)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        genderSpinner.adapter = adapter
        genderSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                this@MainActivity.gender = genderSpinner.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@MainActivity, "Nothing selected", Toast.LENGTH_LONG).show()
            }

        }

        submitButton.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            val bundle = Bundle()
            bundle.putString("gender", gender)
            bundle.putString("dateOfBirth", dateOfBirth)
            intent.putExtra("INFO", bundle)
            startActivity(intent)
        }
    }
}
