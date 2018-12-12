package com.example.magnetification.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.DropBoxManager;
import android.view.View;

import java.security.KeyStore;
import java.util.Map;

public class EntryDatabase extends SQLiteOpenHelper {

    private static EntryDatabase instance;

    public static EntryDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new EntryDatabase(context);
        }

        return instance;
    }

    private EntryDatabase(Context context) {
        super(context, "entries", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "create table entries ( _id INTEGER PRIMARY KEY, " +
                                            "title TEXT, " +
                                            "content TEXT, " +
                                            "mood INTEGER, " +
                                            "timestamp TEXT)");
        db.execSQL("INSERT INTO entries (title, content, mood, timestamp)" +
                "VALUES ('first day', 'pretty good man thanks a lot', '2', 'DWDD')");
        db.execSQL("INSERT INTO entries (title, content, mood, timestamp)" +
                "VALUES ('second day', 'a little bit less than yesterday', '5', 'DWDDD')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS entries");
        onCreate(db);
    }

    public Cursor selectAll() {
        return instance.getWritableDatabase().rawQuery("SELECT * FROM entries", null);
    }

    public void insert(JournalEntry entry) {

        ContentValues entries = new ContentValues();
        entries.put("title", entry.getTitle());
        entries.put("content", entry.getContent());
        entries.put("mood", entry.getMood());
        entries.put("timestamp", entry.getTimestamp());
        instance.getWritableDatabase().insert("entries", null, entries);
    }

    public void deleteEntry(long id) {
        instance.getWritableDatabase().delete("entries", "_id = '" + id +"'", null);
    }
}

