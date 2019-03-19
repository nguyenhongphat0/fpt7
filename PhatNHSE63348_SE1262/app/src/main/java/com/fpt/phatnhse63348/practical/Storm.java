package com.fpt.phatnhse63348.practical;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.fpt.phatnhse63348.practical.util.StormDBHelper;

public class Storm implements BaseColumns {
    public static final String TABLE = "Storms";
    public static final String COLUMN_ID = "StormID";
    public static final String COLUMN_NAME = "StormName";
    public static final String COLUMN_WINDSPEED = "WindSpeed";
    public static final String COLUMN_DESCRIPTION = "StormDescription";

    public static final String SQL_CREATE = "CREATE TABLE " +
            TABLE + " (" +
            COLUMN_ID + " TEXT PRIMARY KEY," +
            COLUMN_NAME + " TEXT," +
            COLUMN_WINDSPEED + " REAL," +
            COLUMN_DESCRIPTION + " TEXT)";
    public static final String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE;

    public static Cursor all(StormDBHelper helper) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE;
        return db.rawQuery(sql, null);
    }

    public static void insert(StormDBHelper helper, ContentValues values) throws Exception {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.insertOrThrow(TABLE, null, values);
        db.close();
    }
}
