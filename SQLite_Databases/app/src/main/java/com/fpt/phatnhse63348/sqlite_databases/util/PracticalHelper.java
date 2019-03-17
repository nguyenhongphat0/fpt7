package com.fpt.phatnhse63348.sqlite_databases.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fpt.phatnhse63348.sqlite_databases.model.Database;

public class PracticalHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "databases.db";
    public static final int DB_VERSION = 1;

    public PracticalHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Database.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Database.SQL_DROP);
        onCreate(db);
    }
}
