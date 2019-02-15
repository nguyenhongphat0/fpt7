package com.example.day10_database;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MyPreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_preference);
    }

    public void clickToLoad(View view) {
        Intent intent = new Intent(this, PhatNHPreferenceActivity.class);
        startActivity(intent);
    }

    public void clickToDisplay(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.day10_database_preferences", MODE_PRIVATE);
        String name = sharedPreferences.getString("edtPreference", "");
        Toast.makeText(this, name, Toast.LENGTH_LONG).show();
    }

    public void clickToModify(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.day10_database_preferences", MODE_PRIVATE);
        EditText edtValue = findViewById(R.id.edtValue);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("edtPreference", edtValue.getText().toString());
        editor.commit();
    }
}
