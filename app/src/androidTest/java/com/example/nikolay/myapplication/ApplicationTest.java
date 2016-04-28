package com.example.nikolay.myapplication;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.test.ApplicationTestCase;
import com.example.nikolay.myapplication.dao.AudioDaoDb;
import com.example.nikolay.myapplication.dao.DbHelper;
import com.example.nikolay.myapplication.models.Audio;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testDatabase() throws Exception {
        Context context = getContext();
        assertNotNull(context);
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase writableDatabase = helper.getWritableDatabase();
        assertNotNull(writableDatabase);
    }

    public void testTestInsertAudio() throws Exception {
        Context context = getContext();
        assertNotNull(context);
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase writableDatabase = helper.getWritableDatabase();
        assertNotNull(writableDatabase);
        AudioDaoDb audioDao = new AudioDaoDb(writableDatabase);

        Audio audio = new Audio();
        audio.setTitle("FirstAudio");
        audio.setDuration(111);

        audioDao.save(audio);

        Audio savedAudio = audioDao.getById(audio.getId());

        assertNotNull(savedAudio);
        assertEquals(audio.getId(), savedAudio.getId());
        assertEquals(audio.getTitle(), savedAudio.getTitle());

    }
}