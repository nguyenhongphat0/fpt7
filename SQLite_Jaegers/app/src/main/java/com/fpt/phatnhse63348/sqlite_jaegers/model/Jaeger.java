package com.fpt.phatnhse63348.sqlite_jaegers.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.fpt.phatnhse63348.sqlite_jaegers.util.SQLiteHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jaeger implements BaseColumns {
    public static final String TABLE_NAME = "Jaeger";
    public static final String COLUMN_MODEL = "Model";
    public static final String COLUMN_CREATOR = "Creator";
    public static final String COLUMN_HEIGHT = "Height";
    public static final String COLUMN_WEIGHT = "Weight";
    public static final String COLUMN_SKILL = "Skill";
    public static final String COLUMN_CREATED_DATE = "Created_Date";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "(" +
            _ID + " INTEGER PRIMARY KEY," +
            COLUMN_MODEL + " TEXT," +
            COLUMN_CREATOR + " TEXT," +
            COLUMN_HEIGHT + " NUMBER," +
            COLUMN_WEIGHT + " NUMBER," +
            COLUMN_SKILL + " TEXT," +
            COLUMN_CREATED_DATE + " TEXT)";
    public static final String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static Cursor all(SQLiteHelper helper) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        List<Map<String, String>> jaegers = new ArrayList<>();
        return cursor;
    }

    public static Cursor search(SQLiteHelper helper, String keyword) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                null,
                COLUMN_MODEL + " LIKE ?",
                new String[] {"%" + keyword + "%"},
                null,
                null,
                COLUMN_MODEL + " ASC"
        );
        return cursor;
    }

    public static Cursor get(SQLiteHelper helper, long id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                null,
                _ID + " = ?",
                new String[] {id + ""},
                null,
                null,
                COLUMN_MODEL + " ASC"
        );
        return cursor;
    }

    public static long insert(SQLiteHelper helper, ContentValues jaeger) {
        SQLiteDatabase db = helper.getWritableDatabase();
        long id = db.insert(TABLE_NAME, null, jaeger);
        db.close();
        return id;
    }

    public static long delete(SQLiteHelper helper, long id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int rows = db.delete(TABLE_NAME, _ID + " = ?", new String[] {id + ""});
        db.close();
        return rows;
    }

    public static long update(SQLiteHelper helper, long id, ContentValues jaeger) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int rows = db.update(TABLE_NAME, jaeger, _ID + " = ?", new String[] {id + ""});
        db.close();
        return rows;
    }
}
