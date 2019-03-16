package com.fpt.phatnhse63348.sqlite.utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CursorIterator {
    public static interface Iterator {
        public void each(Cursor cursor);
    }

    public Cursor cursor;

    public CursorIterator(Cursor cursor) {
        this.cursor = cursor;
    }

    public void finish(Iterator callback) {
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            callback.each(cursor);
        }
        cursor.close();
    }
}
