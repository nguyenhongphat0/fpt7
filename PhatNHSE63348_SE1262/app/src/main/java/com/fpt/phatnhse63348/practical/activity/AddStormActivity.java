package com.fpt.phatnhse63348.practical.activity;

import android.content.ContentValues;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fpt.phatnhse63348.practical.R;
import com.fpt.phatnhse63348.practical.Storm;
import com.fpt.phatnhse63348.practical.util.StormDBHelper;

public class AddStormActivity extends AppCompatActivity {
    private EditText stormID;
    private EditText stormName;
    private EditText windSpeed;
    private EditText description;
    private Button addStorm;
    private StormDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_storm);
        helper = new StormDBHelper(this);

        collectViews();
        attachEvents();
    }

    private void attachEvents() {
        addStorm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "";
                String s_id = stormID.getText().toString();
                String s_name = stormName.getText().toString();
                String s_speed = windSpeed.getText().toString();
                float f_speed = 0;
                try {
                    f_speed = Float.parseFloat(s_speed);
                } catch(Exception e) {
                    message = "Speed must be a number";
                }
                String s_description = description.getText().toString();
                if (f_speed <= 1) {
                    message = "Speed must > 1";
                } else if (s_id.equals("")) {
                    message = "ID must not be blank";
                } else if (s_name.equals("")) {
                    message = "Name must not be blank";
                } else if (s_description.equals("")) {
                    message = "Description must not be blank";
                }
                if (message.equals("")) {
                    ContentValues storm = new ContentValues();
                    storm.put(Storm.COLUMN_ID, s_id);
                    storm.put(Storm.COLUMN_NAME, s_name);
                    storm.put(Storm.COLUMN_DESCRIPTION, s_description);
                    storm.put(Storm.COLUMN_WINDSPEED, f_speed);
                    try {
                        Storm.insert(helper, storm);
                        Toast.makeText(AddStormActivity.this, "Insert storm successfully!", Toast.LENGTH_LONG).show();
                        finish();
                    } catch (Exception e) {
                        Toast.makeText(AddStormActivity.this, "Error insert storm! ID duplicated", Toast.LENGTH_LONG).show();
                    }
                } else {
                    new AlertDialog.Builder(AddStormActivity.this)
                            .setTitle("Invalid data")
                            .setMessage(message)
                            .show();
                }
            }
        });
    }

    private void collectViews() {
        stormID = findViewById(R.id.stormID);
        stormName = findViewById(R.id.stormName);
        windSpeed = findViewById(R.id.windSpeed);
        description = findViewById(R.id.description);
        addStorm = findViewById(R.id.addStorm);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.close();
    }
}
