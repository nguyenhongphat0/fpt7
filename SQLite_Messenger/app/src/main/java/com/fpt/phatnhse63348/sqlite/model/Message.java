package com.fpt.phatnhse63348.sqlite.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fpt.phatnhse63348.sqlite.utils.CursorIterator;
import com.fpt.phatnhse63348.sqlite.utils.MessengerDbHelper;

import java.util.Calendar;
import java.util.Date;

public class Message {
    public static final String TABLE_NAME = "messages";
    public static final String ID = "id";
    public static final String SENDER = "sender";
    public static final String RECEIVER = "receiver";
    public static final String CONTENT = "content";
    public static final String SEEN = "seen";
    public static final String DATE_SENT = "sent_at";

    public static final String SQL_CREATE_ENTRY =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " INTEGER NOT NULL PRIMARY KEY, " +
                    SENDER + " INTEGER REFERENCES USERS(ID), " +
                    RECEIVER + " INTEGER REFERENCES USERS(ID), " +
                    CONTENT + " TEXT, " +
                    SEEN + " INT, " +
                    DATE_SENT + " TEXT)";
    public static final String SQL_DROP_ENTRY = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static CursorIterator getAllMessagesFrom(MessengerDbHelper dbHelper, long userID) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] conditions = {userID + "", User.LOGGED_USER_ID + "", User.LOGGED_USER_ID + "", userID + ""};
        Cursor cursor = db.query(
                TABLE_NAME,
                null,
                "("+SENDER+" = ? AND "+RECEIVER+" = ?) OR ("+SENDER+" = ? AND "+RECEIVER+" = ?)",
                conditions,
                null,
                null,
                null
        );
        return new CursorIterator(cursor);
    }

    public static long sendMessage(MessengerDbHelper dbHelper, String content, long receiver) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SENDER, User.LOGGED_USER_ID);
        values.put(RECEIVER, receiver);
        values.put(CONTENT, content);
        values.put(DATE_SENT, new Date().toString());
        long id = db.insert(TABLE_NAME, null, values);
        return id;
    }

    public static long clearMessage(MessengerDbHelper helper, long receiver) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] conditions = {receiver + "", User.LOGGED_USER_ID + "", User.LOGGED_USER_ID + "", receiver + ""};
        return db.delete(TABLE_NAME, "("+SENDER+" = ? AND "+RECEIVER+" = ?) OR ("+SENDER+" = ? AND "+RECEIVER+" = ?)", conditions);
    }
}
