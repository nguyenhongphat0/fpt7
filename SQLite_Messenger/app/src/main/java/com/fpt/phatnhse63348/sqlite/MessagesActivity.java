package com.fpt.phatnhse63348.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.fpt.phatnhse63348.sqlite.model.Message;
import com.fpt.phatnhse63348.sqlite.model.User;
import com.fpt.phatnhse63348.sqlite.utils.CursorIterator;
import com.fpt.phatnhse63348.sqlite.utils.MessengerDbHelper;

import java.util.ArrayList;

public class MessagesActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_CHANGE_PASSWORD = 1;

    MessengerDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Welcome " + User.LOGGED_USER_USERNAME + "!");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent changePasswordIntent = new Intent(MessagesActivity.this, ChangePasswordActivity.class);
                startActivityForResult(changePasswordIntent, REQUEST_CODE_CHANGE_PASSWORD);
            }
        });

        dbHelper = new MessengerDbHelper(this);
        ListView userList = findViewById(R.id.userList);
        final ArrayList<String> users = new ArrayList<>();
        final ArrayList<Long> ids = new ArrayList<>();
        User.getAllUser(dbHelper).finish(new CursorIterator.Iterator() {
            @Override
            public void each(Cursor cursor) {
                String username = cursor.getString(cursor.getColumnIndex(User.UserEntry.COLUMN_NAME_USERNAME));
                users.add(username);
                long id = cursor.getLong(cursor.getColumnIndex(User.UserEntry.COLUMN_NAME_ID));
                ids.add(id);
            }
        });
        userList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, users));
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MessagesActivity.this, ReadMessageActivity.class);
                intent.putExtra(ReadMessageActivity.PARAM_USER_ID, ids.get(position));
                startActivity(intent);
            }
        });
        userList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                long result = Message.clearMessage(dbHelper, ids.get(position));
                Toast.makeText(MessagesActivity.this, result + " messages deleted successfully!", Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FloatingActionButton fab = findViewById(R.id.fab);
        if (requestCode == REQUEST_CODE_CHANGE_PASSWORD) {
            if (resultCode == Activity.RESULT_OK) {
                Snackbar.make(fab, data.getStringExtra(ChangePasswordActivity.EXTRA_MESSAGE), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            } else {
                if (data == null) {
                    Snackbar.make(fab, "Incorrect old password (Result with no intent data)", Snackbar.LENGTH_LONG)
                            .setAction(resultCode + "", null).show();
                } else {
                    Snackbar.make(fab, data.getStringExtra(ChangePasswordActivity.EXTRA_MESSAGE), Snackbar.LENGTH_LONG)
                            .setAction(resultCode + "", null).show();
                }
            }
        }
    }
}
