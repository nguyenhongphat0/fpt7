package day01.nguyenhongphat0.day02_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int REQ_CODE = 6789;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = this.getIntent();
//        String username = intent.getStringExtra("username");
//        TextView txtResult = findViewById(R.id.txtResult);
//        txtResult.setText("Welcome " + username + " to Main Activity");
    }

    public void clickToInput(View view) {
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivityForResult(intent, REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQ_CODE) {
            if (resultCode == RESULT_OK) {
                String username = data.getStringExtra("username");
                TextView txtResult = findViewById(R.id.txtResult);
                txtResult.setText("Welcome " + username + " to Main Activity");
            }
        }
    }
}
