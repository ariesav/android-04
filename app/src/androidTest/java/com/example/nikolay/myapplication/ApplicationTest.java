package com.example.nikolay.myapplication;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.test.ApplicationTestCase;
import android.util.Log;
import com.example.nikolay.myapplication.dao.AudioDaoDb;
import com.example.nikolay.myapplication.dao.DbHelper;
import com.example.nikolay.myapplication.models.Audio;
import com.example.nikolay.myapplication.rest.ApiService;
import com.example.nikolay.myapplication.rest.responses.AudioGetResponse;
import com.example.nikolay.myapplication.session.Session;
import com.example.nikolay.myapplication.session.SessionStore;
import junit.framework.Assert;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

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
    private ApiService service;
    private Session session;

    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.vk.com/method/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ApiService.class);

        session = SessionStore.restore(getContext());

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
            while ((line = reader.readLine()) != null) {
                Log.d(TAG, "testWikipedia: ------ " + line);
            }
            reader.close();
        } else {
            Log.d(TAG, "testWikipedia: FAILED");
            InputStream stream = urlConnection.getErrorStream();
        }
    }

    public void testAudioGet() throws InterruptedException {

        Assert.assertNotNull(session);
        Assert.assertNotNull(session.getAccessToken());

        Call<AudioGetResponse> audiosCall =
                service.getAudios(session.getAccessToken());

        audiosCall.enqueue(new Callback<AudioGetResponse>() {
            @Override
            public void onResponse(Call<AudioGetResponse> call, Response<AudioGetResponse> response) {
                Log.d(TAG, "onResponse: ok");
                AudioGetResponse audioGetResponse = response.body();
                Log.d(TAG, "onResponse: size = " + audioGetResponse.getCount());
                Assert.assertTrue(audioGetResponse.getCount() > 0);
            }

            @Override
            public void onFailure(Call<AudioGetResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: fail", t);
            }
        });

        Thread.sleep(5000);
    }

    public void testAudioGet2() throws InterruptedException {

        Assert.assertNotNull(session);
        Assert.assertNotNull(session.getAccessToken());

        Call<ResponseBody> audiosCall =
                service.getAudiosRaw(session.getAccessToken());

        audiosCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, "onResponse: ok");
                try {
                    String res = response.body().string();
                    Log.d(TAG, "onResponse: " + res);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: fail", t);
            }
        });

        Thread.sleep(5000);
    }
}