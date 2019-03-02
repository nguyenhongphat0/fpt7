package com.fpt.phatnhse63348.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fpt.phatnhse63348.sqlite.model.User;
import com.fpt.phatnhse63348.sqlite.utils.MessengerDbHelper;

public class ChangePasswordActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "message";
    MessengerDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        dbHelper = new MessengerDbHelper(this);
        Button changePasswordButton = findViewById(R.id.changePasswordButton);
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPassword = ((EditText)findViewById(R.id.oldPassword)).getText().toString();
                String newPassword = ((EditText)findViewById(R.id.newPassword)).getText().toString();
                String confirmPassword = ((EditText)findViewById(R.id.confirmPassword)).getText().toString();

                Intent returnIntent = new Intent();
                if (User.checkLogin(dbHelper, User.LOGGED_USER_USERNAME, oldPassword) == -1) {
                    setResult(Activity.RESULT_CANCELED);
                } else if (!newPassword.equals(confirmPassword)) {
                    returnIntent.putExtra(EXTRA_MESSAGE, "Confirm password not match!");
                    setResult(Activity.RESULT_CANCELED,  returnIntent);
                } else {
                    User.updatePassword(dbHelper, newPassword);
                    returnIntent.putExtra(EXTRA_MESSAGE, "Password changed succesfully!");
                    setResult(Activity.RESULT_OK, returnIntent);
                }
                finish();
            }
        });
    }
}
