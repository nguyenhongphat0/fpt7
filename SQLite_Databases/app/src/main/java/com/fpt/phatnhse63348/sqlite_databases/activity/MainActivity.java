package com.fpt.phatnhse63348.sqlite_databases.activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.fpt.phatnhse63348.sqlite_databases.R;
import com.fpt.phatnhse63348.sqlite_databases.adapter.DatabaseListAdapter;
import com.fpt.phatnhse63348.sqlite_databases.model.Database;
import com.fpt.phatnhse63348.sqlite_databases.util.PracticalHelper;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_ADD_DATABASE = 1;

    private PracticalHelper helper;
    private ListView databaseList;
    private Button addButton;
    private EditText searchKeywords;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new PracticalHelper(this);
        databaseList = findViewById(R.id.databaseList);
        renderDatabases();
        attachEvents();
    }

    private void attachEvents() {
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddDatabaseActivity.class);
                startActivityForResult(intent, REQUEST_ADD_DATABASE);
            }
        });
        searchKeywords = findViewById(R.id.searchKeywords);
        searchKeywords.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (cursor != null) {
                    cursor.close();
                }
                cursor = Database.search(helper, s.toString());
                databaseList.setAdapter(new DatabaseListAdapter(MainActivity.this, cursor));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD_DATABASE) {
            renderDatabases();
        }
    }

    private void renderDatabases() {
        if (cursor != null) {
            cursor.close();
        }
        cursor = Database.all(helper);
        databaseList.setAdapter(new DatabaseListAdapter(this, cursor));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.close();
    }
}
