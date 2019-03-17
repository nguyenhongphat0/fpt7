package com.fpt.phatnhse63348.sqlite_databases.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.fpt.phatnhse63348.sqlite_databases.util.PracticalHelper;

public class Database implements BaseColumns {
    public static final String TABLE = "Database";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_CREATOR = "Creator";
    public static final String COLUMN_TYPE = "Type";
    public static final String COLUMN_CREATED_DATE = "CreatedDate";
    public static final String COLUMN_VERSION = "Version";
    public static final String COLUMN_DESCRIPTION = "Description";

    public static final String SQL_CREATE = "CREATE TABLE " +
            TABLE + " (" +
            _ID + " INTEGER PRIMARY KEY," +
            COLUMN_NAME + " TEXT," +
            COLUMN_CREATOR + " TEXT," +
            COLUMN_TYPE + " TEXT," +
            COLUMN_CREATED_DATE + " TEXT," +
            COLUMN_VERSION + " NUMBER," +
            COLUMN_DESCRIPTION + " TEXT)";
    public static final String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE;

    public static Cursor all(PracticalHelper helper) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE,
                null,
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }

    public static Cursor get(PracticalHelper helper, long id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE,
                null,
                _ID + " = ?",
                new String[] {id + ""},
                null,
                null,
                null
        );
        return cursor;
    }

    public static Cursor search(PracticalHelper helper, String keywords) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE,
                null,
                COLUMN_NAME + " LIKE ? OR " + COLUMN_DESCRIPTION + " LIKE ?",
                new String[] {"%" + keywords + "%", "%" + keywords + "%"},
                null,
                null,
                null
        );
        return cursor;
    }

    public static long insert(PracticalHelper helper, ContentValues values) {
        SQLiteDatabase db = helper.getWritableDatabase();
        long id = db.insert(TABLE, null, values);
        db.close();
        return id;
    }

    public static long update(PracticalHelper helper, long id, ContentValues values) {
        SQLiteDatabase db = helper.getWritableDatabase();
        long rows = db.update(TABLE, values, _ID + " =  ?", new String[] {id + ""});
        db.close();
        return rows;
    }

    public static long delete(PracticalHelper helper, long id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        long rows = db.delete(TABLE, _ID + " =  ?", new String[] {id + ""});
        db.close();
        return rows;
    }
}
