package com.fpt.phatnhse63348.sqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fpt.phatnhse63348.sqlite.model.User;
import com.fpt.phatnhse63348.sqlite.utils.MessengerDbHelper;

public class MainActivity extends AppCompatActivity {
    MessengerDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MessengerDbHelper(this);
        insertDemoUser();
        attachEvent();
    }

    private void insertDemoUser() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.UserEntry.COLUMN_NAME_USERNAME, "admin");
        values.put(User.UserEntry.COLUMN_NAME_PASSWORD, "password");
        long id = db.insert(User.UserEntry.TABLE_NAME, null, values);
        Toast.makeText(this, "User with " + id + " created successfully", Toast.LENGTH_LONG).show();
    }

    private void attachEvent() {
        final TextView usernameTV = findViewById(R.id.username);
        final TextView passwordTV = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameTV.getText().toString();
                String password = passwordTV.getText().toString();
                if (User.checkLogin(dbHelper, username, password)) {
                    startActivity(new Intent(MainActivity.this, MessagesActivity.class));
                } else {
                    Toast.makeText(MainActivity.this, "Error login for user " + username, Toast.LENGTH_LONG).show();
                    passwordTV.setText("");
                }
            }
        });

    }
}
