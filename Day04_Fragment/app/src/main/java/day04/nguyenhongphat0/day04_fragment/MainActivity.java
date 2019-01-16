package day04.nguyenhongphat0.day04_fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickToAdd(View view) {
        TextView txtResult = findViewById(R.id.txtResult);
        EditText editNumber1 = findViewById(R.id.editNumber1);
        EditText editNumber2 = findViewById(R.id.editNumber2);
        int result = Integer.parseInt(editNumber1.getText().toString()) + Integer.parseInt(editNumber2.getText().toString());
        txtResult.setText("Result = " + result);
    }
}
