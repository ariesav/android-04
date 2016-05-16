package com.example.nikolay.myapplication;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.test.ApplicationTestCase;
import android.util.Log;
import com.example.nikolay.myapplication.dao.AudioDaoDb;
import com.example.nikolay.myapplication.dao.DbHelper;
import com.example.nikolay.myapplication.models.Audio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    private static final String TAG = ApplicationTest.class.getSimpleName();

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
        AudioDaoDb audioDao = null;// = //new AudioDaoDb(writableDatabase);

        Audio audio = new Audio();
        audio.setTitle("FirstAudio");
        audio.setDuration(111);

        audioDao.save(audio);

        Audio savedAudio = audioDao.getById(audio.getId());

        assertNotNull(savedAudio);
        assertEquals(audio.getId(), savedAudio.getId());
        assertEquals(audio.getTitle(), savedAudio.getTitle());
    }

    public void testWikipedia() throws IOException {

        URL url = new URL("https://www.wikipedia.org/");

        HttpURLConnection urlConnection =
                (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        int responseCode = urlConnection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            Log.d(TAG, "testWikipedia: OK");
            InputStream stream = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(stream));
            String line = null;
            while ((line = reader.readLine())!= null){
                Log.d(TAG, "testWikipedia: ------ " + line);
            }
            reader.close();
        } else {
            Log.d(TAG, "testWikipedia: FAILED");
            InputStream stream = urlConnection.getErrorStream();
        }
    }
}