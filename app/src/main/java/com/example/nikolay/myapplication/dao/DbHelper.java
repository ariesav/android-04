package com.example.nikolay.myapplication.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "player.db";

    private static final String CREATE_AUDIOS =
            "create table if not exists " + DataContracts.AudioEntry.TABLE_NAME + " (" +
                    DataContracts.AudioEntry._ID + " integer primary key," +
                    DataContracts.AudioEntry.COLUMN_TITLE + " text," +
                    DataContracts.AudioEntry.COLUMN_DURATION + " integer)";

    private static final String DELETE_AUDIOS =
            "drop table if exists " + DataContracts.AudioEntry.TABLE_NAME;

    private static final String CREATE_AUTHORS =
            "create table if not exists " + DataContracts.AuthorEntry.TABLE_NAME + " (" +
                    DataContracts.AuthorEntry._ID + " integer primary key," +
                    DataContracts.AuthorEntry.COLUMN_FIRST_NAME + " text," +
                    DataContracts.AuthorEntry.COLUMN_LAST_NAME + " text," +
                    DataContracts.AuthorEntry.COLUMN_DATE_OF_BIRTH + " integer)";

    private static final String DELETE_AUTHORS =
            "drop table if exists " + DataContracts.AuthorEntry.TABLE_NAME;

    private static final String CREATE_AUTHORS_AUTHIOS =
            "create table if not exists " + DataContracts.AudioAuthorLink.TABLE_NAME + " (" +
                    DataContracts.AudioAuthorLink._ID + " integer primary key," +
                    DataContracts.AudioAuthorLink.COLUMN_AUDIO_ID + " integer," +
                    DataContracts.AudioAuthorLink.COLUMN_AUTHOR_ID + " integer)";

    private static final String DELETE_AUTHORS_AUTHIOS =
            "drop table if exists " + DataContracts.AuthorEntry.TABLE_NAME;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_AUDIOS);
        db.execSQL(CREATE_AUTHORS);
        db.execSQL(CREATE_AUTHORS_AUTHIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_AUDIOS);
        db.execSQL(DELETE_AUTHORS);
        db.execSQL(DELETE_AUTHORS_AUTHIOS);
        onCreate(db);
    }
}
