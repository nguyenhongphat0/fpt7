package com.fpt.phatnhse63348.sqlite;

import android.app.Activity;
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
        attachEvent();
    }

    private void attachEvent() {
        final TextView usernameTV = findViewById(R.id.username);
        final TextView passwordTV = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.loginButton);
        Button registerButton = findViewById(R.id.registerButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameTV.getText().toString();
                String password = passwordTV.getText().toString();
                int ID = User.checkLogin(dbHelper, username, password);
                if (ID >= 0) {
                    User.LOGGED_USER_ID = ID;
                    User.LOGGED_USER_USERNAME = username;
                    Intent intent = new Intent(MainActivity.this, MessagesActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Error login for user " + username, Toast.LENGTH_LONG).show();
                    passwordTV.setText("");
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
    }
}
