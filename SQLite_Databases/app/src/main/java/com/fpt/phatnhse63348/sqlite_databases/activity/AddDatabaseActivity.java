package com.fpt.phatnhse63348.sqlite_databases.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fpt.phatnhse63348.sqlite_databases.R;
import com.fpt.phatnhse63348.sqlite_databases.model.Database;
import com.fpt.phatnhse63348.sqlite_databases.util.PracticalHelper;

import java.util.Calendar;

public class AddDatabaseActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "id";

    private long id;
    private PracticalHelper helper;
    private Button addButton;
    private EditText name;
    private EditText description;
    private EditText version;
    private EditText creator;
    private DatePicker createdDate;
    private RadioGroup typeGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_database);

        helper = new PracticalHelper(this);
        collectViews();
        attachEvents();
    }

    private void collectViews() {
        addButton = findViewById(R.id.addButton);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        version = findViewById(R.id.version);
        creator = findViewById(R.id.creator);
        createdDate = findViewById(R.id.createdDate);
        typeGroup = findViewById(R.id.typeGroup);

        if (getIntent().hasExtra(EXTRA_ID)) {
            id = getIntent().getLongExtra(EXTRA_ID, 0);
        }
        if (id > 0) {
            addButton.setText("Update");
            Cursor cursor = Database.get(helper, id);
            if (cursor.moveToFirst()) {
                name.setText(cursor.getString(cursor.getColumnIndex(Database.COLUMN_NAME)));
                description.setText(cursor.getString(cursor.getColumnIndex(Database.COLUMN_DESCRIPTION)));
                creator.setText(cursor.getString(cursor.getColumnIndex(Database.COLUMN_CREATOR)));
                version.setText(cursor.getString(cursor.getColumnIndex(Database.COLUMN_VERSION)));
                int type = cursor.getInt(cursor.getColumnIndex(Database.COLUMN_TYPE));
                RadioButton radio = findViewById(type);
                if (radio != null) {
                    radio.setChecked(true);
                }
            }
        }
    }

    private void attachEvents() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commitDatabase(id > 0);
            }
        });
    }

    private void commitDatabase(boolean update) {
        ContentValues database = new ContentValues();
        String dbName = name.getText().toString();
        if (dbName.equals("")) {
            new AlertDialog.Builder(AddDatabaseActivity.this)
                    .setTitle("No title")
                    .setMessage("A database must have a title, please fill in the title!")
                    .show();
        } else {
            database.put(Database.COLUMN_NAME, dbName);
            database.put(Database.COLUMN_DESCRIPTION, description.getText().toString());
            database.put(Database.COLUMN_CREATOR, creator.getText().toString());
            database.put(Database.COLUMN_VERSION, version.getText().toString());
            database.put(Database.COLUMN_TYPE, typeGroup.getCheckedRadioButtonId());
//        switch (typeGroup.getCheckedRadioButtonId()) {
//            case R.id.typeSQL:
//                database.put(Database.COLUMN_TYPE, "SQL");
//                break;
//            case R.id.typeNoSQL:
//                database.put(Database.COLUMN_TYPE, "No SQL");
//                break;
//        }
            Calendar calendar = Calendar.getInstance();
            calendar.set(createdDate.getYear(), createdDate.getMonth(), createdDate.getDayOfMonth());
            database.put(Database.COLUMN_CREATED_DATE, calendar.getTime().toString());
            if (update) {
                Database.update(helper, id, database);
            } else {
                Database.insert(helper, database);
            }
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Delete this database");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Database.delete(helper, id);
        finish();
        return super.onOptionsItemSelected(item);
    }
}
