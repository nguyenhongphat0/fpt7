package com.fpt.phatnhse63348.sqlite.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.fpt.phatnhse63348.sqlite.utils.MessengerDbHelper;

public class User {
    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserEntry.TABLE_NAME + " (" +
                    UserEntry.COLUMN_NAME_ID + " INTEGER NOT NULL PRIMARY KEY, " +
                    UserEntry.COLUMN_NAME_USERNAME + " TEXT, " +
                    UserEntry.COLUMN_NAME_PASSWORD + " TEXT)";
    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME;

    public static boolean checkLogin(MessengerDbHelper dbHelper, String username, String password) {
        System.out.println("CHECKLOGIN FOR USER " + username + " WITH PASSWORD " + password);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = UserEntry.COLUMN_NAME_USERNAME + " = ? AND " + UserEntry.COLUMN_NAME_PASSWORD + " = ?";
        String[] values = {username, password};
        Cursor cursor = db.query(
            UserEntry.TABLE_NAME,
            null,
            selection,
            values,
            null,
            null,
            null
        );
        return cursor.moveToFirst();
    }
}
