package com.fpt.phatnhse63348.sqlite_jaegers;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.fpt.phatnhse63348.sqlite_jaegers.model.Jaeger;
import com.fpt.phatnhse63348.sqlite_jaegers.util.SQLiteHelper;

import java.util.Calendar;

public class AddJeagerActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "jaeger_id";

    private long _ID;
    private Button addButton;
    private EditText model;
    private EditText height;
    private EditText weight;
    private EditText skill;
    private DatePicker dateCreated;
    private Spinner creator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_jeager);
        if (getIntent().hasExtra(EXTRA_ID)) {
            _ID = getIntent().getLongExtra(EXTRA_ID, 0);
        }

        collectViews();
        renderJaeger();
    }

    private void renderJaeger() {
        if (_ID > 0) {
            Cursor cursor = Jaeger.get(new SQLiteHelper(AddJeagerActivity.this), _ID);
            if (cursor.moveToFirst()) {
                model.setText(cursor.getString(cursor.getColumnIndex(Jaeger.COLUMN_MODEL)));
                height.setText(cursor.getString(cursor.getColumnIndex(Jaeger.COLUMN_HEIGHT)));
                weight.setText(cursor.getString(cursor.getColumnIndex(Jaeger.COLUMN_WEIGHT)));
                skill.setText(cursor.getString(cursor.getColumnIndex(Jaeger.COLUMN_SKILL)) + "\n" + cursor.getString(cursor.getColumnIndex(Jaeger.COLUMN_CREATED_DATE)));
                addButton.setText("Update");
                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        commitJaeger(true);
                    }
                });
            }
        }
    }

    private void collectViews() {
        model = findViewById(R.id.model);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        skill = findViewById(R.id.skill);
        dateCreated = findViewById(R.id.dateCreated);
        creator = findViewById(R.id.creator);
        creator.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Japan", "China", "US", "UK", "South Korean", "Viet Nam"}
        ));

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commitJaeger(false);
            }
        });
    }

    private void commitJaeger(boolean update) {
        ContentValues jaeger = new ContentValues();
        jaeger.put(Jaeger.COLUMN_MODEL, model.getText().toString());
        jaeger.put(Jaeger.COLUMN_SKILL, skill.getText().toString());
        jaeger.put(Jaeger.COLUMN_HEIGHT, height.getText().toString());
        jaeger.put(Jaeger.COLUMN_WEIGHT, weight.getText().toString());
        jaeger.put(Jaeger.COLUMN_CREATOR, creator.getSelectedItem().toString());
        Calendar calendar = Calendar.getInstance();
        calendar.set(dateCreated.getYear(), dateCreated.getMonth(), dateCreated.getDayOfMonth());
        String date = calendar.getTime().toString();
        jaeger.put(Jaeger.COLUMN_CREATED_DATE, date);
        if (update) {
            Jaeger.update(new SQLiteHelper(this), _ID, jaeger);
            Toast.makeText(this, "Update success", Toast.LENGTH_LONG).show();
        } else {
            Jaeger.insert(new SQLiteHelper(this), jaeger);
            Toast.makeText(this, "Insert success", Toast.LENGTH_LONG).show();
        }
        finish();
    }
}
