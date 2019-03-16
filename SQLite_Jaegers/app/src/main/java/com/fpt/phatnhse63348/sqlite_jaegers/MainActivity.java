package com.fpt.phatnhse63348.sqlite_jaegers;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.fpt.phatnhse63348.sqlite_jaegers.adapter.JaegersListAdapter;
import com.fpt.phatnhse63348.sqlite_jaegers.model.Jaeger;
import com.fpt.phatnhse63348.sqlite_jaegers.util.SQLiteHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_ADD_JAEGER = 1;

    private ListView jaegersList;
    private Button addButton;
    private EditText searchKeyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        attachEvent();
        renderJaegers();
    }

    private void attachEvent() {
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, AddJeagerActivity.class), REQUEST_ADD_JAEGER);
            }
        });
        searchKeyword = findViewById(R.id.searchKeyword);
        searchKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Cursor cursor = Jaeger.search(new SQLiteHelper(MainActivity.this), s.toString());
                ((JaegersListAdapter)jaegersList.getAdapter()).getCursor().close();
                jaegersList.setAdapter(new JaegersListAdapter(MainActivity.this, cursor));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD_JAEGER) {
            renderJaegers();
        }
    }

    public void renderJaegers() {
        jaegersList = findViewById(R.id.jaegersList);
        Cursor cursor = Jaeger.all(new SQLiteHelper(this));
        jaegersList.setAdapter(new JaegersListAdapter(this, cursor));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Add Jaeger");
        menu.add("Close");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle() == "Add Jaeger") {
            startActivityForResult(new Intent(MainActivity.this, AddJeagerActivity.class), REQUEST_ADD_JAEGER);
        } else {
            if (item.getTitle() == "Close") {
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
