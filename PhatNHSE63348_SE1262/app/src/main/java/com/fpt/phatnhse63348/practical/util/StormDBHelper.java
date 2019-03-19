package com.fpt.phatnhse63348.practical.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fpt.phatnhse63348.practical.Storm;

public class StormDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Storm.db";
    public static final int DB_VERSION = 1;

    public StormDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Storm.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Storm.SQL_DROP);
        onCreate(db);
    }
}
