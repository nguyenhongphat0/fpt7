package com.fpt.phatnhse63348.sqlite.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fpt.phatnhse63348.sqlite.model.Message;
import com.fpt.phatnhse63348.sqlite.model.User;

public class MessengerDbHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 3;
    public static final String DB_NAME = "Messenger.db";

    public MessengerDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(User.SQL_CREATE_ENTRIES);
        db.execSQL("INSERT INTO users(username, password) VALUES('admin', 'password')"); // Demo user
        db.execSQL(Message.SQL_CREATE_ENTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL(User.SQL_DELETE_ENTRIES);
            db.execSQL(Message.SQL_DROP_ENTRY);
            onCreate(db);
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
