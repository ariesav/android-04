package com.example.nikolay.myapplication.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DaoSession {

    private AudioDaoDb audioDao;

    public DaoSession(Context context) {
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();
        audioDao = new AudioDaoDb(database);
    }

    public AudioDaoDb getAudioDao() {
        return audioDao;
    }
}
