package day06.nguyenhongphat0.day06_dynamicui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView txtUsername = findViewById(R.id.txtUsername);
        TextView txtFullname = findViewById(R.id.txtFullname);
        Intent intent = this.getIntent();
        txtUsername.setText(intent.getStringExtra("username"));
        txtFullname.setText(intent.getStringExtra("fullname"));
    }
}
