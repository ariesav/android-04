package com.example.nikolay.myapplication.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.nikolay.myapplication.models.Audio;

import java.util.ArrayList;
import java.util.List;

public class AudioDaoDb {
    private final SQLiteDatabase db;

    AudioDaoDb(SQLiteDatabase db) {
        this.db = db;
    }

    public boolean save(Audio audio) {
        ContentValues cv = new ContentValues();
        cv.put(DataContracts.AudioEntry.COLUMN_TITLE, audio.getTitle());
        cv.put(DataContracts.AudioEntry.COLUMN_DURATION, audio.getDuration());

        long id = db.insert(DataContracts.AudioEntry.TABLE_NAME, null, cv);
        audio.setId(id);
        return true;
    }

    public boolean delete(Audio audio) {
        int deletedCount = db.delete(DataContracts.AudioEntry.TABLE_NAME,
                DataContracts.AudioEntry._ID + "=?",
                new String[]{String.valueOf(audio.getId())});
        return deletedCount == 1;
    }

    public boolean update(Audio audio) {
        ContentValues cv = new ContentValues();
        cv.put(DataContracts.AudioEntry.COLUMN_TITLE, audio.getTitle());
        cv.put(DataContracts.AudioEntry.COLUMN_DURATION, audio.getDuration());
        int updatedCount = db.update(DataContracts.AudioEntry.TABLE_NAME, cv,
                DataContracts.AudioEntry._ID + "=?",
                new String[]{String.valueOf(audio.getId())});
        return updatedCount == 1;
    }

    public Audio getById(long id) {
        Cursor query = db.query(DataContracts.AudioEntry.TABLE_NAME, null,
                DataContracts.AudioEntry._ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        if (query.moveToFirst()) {

            int titleIndex = query.getColumnIndex(DataContracts.AudioEntry.COLUMN_TITLE);
            int durationIndex = query.getColumnIndex(DataContracts.AudioEntry.COLUMN_DURATION);
            int idIndex = query.getColumnIndex(DataContracts.AudioEntry._ID);


            Audio audio = new Audio();
            audio.setTitle(query.getString(titleIndex));
            audio.setId(query.getInt(idIndex));
            audio.setDuration(query.getInt(durationIndex));
            return audio;
        }
        return null;
    }

    public List<Audio> loadAll() {
        Cursor query = db.query(DataContracts.AudioEntry.TABLE_NAME, null,
                null,
                null,
                null,
                null,
                null);
        List<Audio> audios = new ArrayList<Audio>();
        if (query.moveToFirst()) {

            int titleIndex = query.getColumnIndex(DataContracts.AudioEntry.COLUMN_TITLE);
            int durationIndex = query.getColumnIndex(DataContracts.AudioEntry.COLUMN_DURATION);
            int idIndex = query.getColumnIndex(DataContracts.AudioEntry._ID);

            do {

                Audio audio = new Audio();
                audio.setTitle(query.getString(titleIndex));
                audio.setId(query.getInt(idIndex));
                audio.setDuration(query.getInt(durationIndex));

                audios.add(audio);
            } while (query.moveToNext());
        }
        return audios;
    }
}
