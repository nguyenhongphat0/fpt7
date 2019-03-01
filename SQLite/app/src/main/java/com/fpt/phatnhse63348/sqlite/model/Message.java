package com.fpt.phatnhse63348.sqlite.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fpt.phatnhse63348.sqlite.utils.MessengerDbHelper;

public class Message {
    public static final String TABLE_NAME = "messages";
    public static final String ID = "id";
    public static final String SENDER = "sender";
    public static final String CONTENT = "content";
    public static final String SEEN = "seen";
    public static final String DATE_SENT = "sent_at";

    public static final String SQL_CREATE_ENTRY =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " INTEGER NOT NULL PRIMARY KEY, " +
                    SENDER + " INTEGER REFERENCES USERS(ID), " +
                    CONTENT + " TEXT, " +
                    SEEN + " INT, " +
                    DATE_SENT + " TEXT)";
    public static final String SQL_DROP_ENTRY = "DROP TABLE IF EXIST " + TABLE_NAME;

    public static void getAllMessages(MessengerDbHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Cursor cursor = db.query(
//                TABLE_NAME,
//                null,
//                null,
//                null,
//                null,
//                null,
//                null
//        );
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            System.out.println(cursor.getPosition());
            System.out.println(cursor.getType(cursor.getColumnIndex(ID)));
            System.out.println(cursor.getType(cursor.getColumnIndex(SENDER)));
            System.out.println(cursor.getType(cursor.getColumnIndex(CONTENT)));
            System.out.println(cursor.getType(cursor.getColumnIndex(SEEN)));
            System.out.println(cursor.getType(cursor.getColumnIndex(DATE_SENT)));
        }
    }
}
