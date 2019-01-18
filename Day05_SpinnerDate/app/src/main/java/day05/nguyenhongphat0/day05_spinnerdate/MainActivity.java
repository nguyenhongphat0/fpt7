package day05.nguyenhongphat0.day05_spinnerdate;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Spinner spNationality;
    private String selectedSpinner;
    private TextView txtBirthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtBirthday = findViewById(R.id.txtBirthday);

        final Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR) - 18;
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String date = day + "/" + month + "/" + year;
        txtBirthday.setText(date);

        spNationality = findViewById(R.id.spNationality);
        List<String> datasource = new ArrayList<>();
        datasource.add("Kinh");
        datasource.add("Kh'o me");
        datasource.add("Muong");
        datasource.add("Dao");
        datasource.add("Others");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, datasource);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNationality.setAdapter(dataAdapter);
        spNationality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSpinner = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void clickToGetDate(View view) {
        DialogFragment dateFragment = new DayPickerFragment();
        dateFragment.show(getFragmentManager(), "DatePicker");
    }

    public void clickToRegister(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("birthday", txtBirthday.getText().toString());
        bundle.putString("nationality", selectedSpinner);
        Intent intent = new Intent(this, ShowActivity.class);
        intent.putExtra("INFO", bundle);
        startActivity(intent);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + "/" + (month + 1) + "/" + year;
        txtBirthday.setText(date);
    }
}
