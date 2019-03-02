package com.fpt.phatnhse63348.sqlite.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.fpt.phatnhse63348.sqlite.utils.CursorIterator;
import com.fpt.phatnhse63348.sqlite.utils.MessengerDbHelper;

public class User {
    public static int LOGGED_USER_ID;
    public static String LOGGED_USER_USERNAME;

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

    public static int checkLogin(MessengerDbHelper dbHelper, String username, String password) {
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
        if (cursor.moveToFirst()) {
            int ID = cursor.getInt(cursor.getColumnIndex(UserEntry.COLUMN_NAME_ID));
            return ID;
        } else {
            return -1;
        }
    }

    public static long register(MessengerDbHelper dbHelper, String username, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserEntry.COLUMN_NAME_USERNAME, username);
        values.put(UserEntry.COLUMN_NAME_PASSWORD, password);
        return db.insert(UserEntry.TABLE_NAME, null, values);
    }

    public static CursorIterator getAllUser(MessengerDbHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + UserEntry.TABLE_NAME, null);
        return new CursorIterator(cursor);
    }

    public static long updatePassword(MessengerDbHelper helper, String password) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserEntry.COLUMN_NAME_PASSWORD, password);
        String[] args = {User.LOGGED_USER_ID + ""};
        return db.update(UserEntry.TABLE_NAME, values, "ID = ?", args);
    }
}
