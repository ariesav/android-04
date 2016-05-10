package com.example.nikolay.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import com.example.nikolay.myapplication.dao.AudioDao;
import com.example.nikolay.myapplication.models.Album;
import com.example.nikolay.myapplication.models.Audio;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.util.Arrays;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
@RunWith(MockitoJUnitRunner.class)
public class AudioDAOTest {

    @Before
    public void beforeTest() {
        File file = new File(AudioDao.FILE_NAME);
        file.delete();
    }

    @Test
    public void testAudioAdd() throws Exception {
        Audio audio = new Audio();
        audio.setTitle("FirstAudio");
        audio.setDuration(111);
        AudioDao audioDao = new AudioDao();
        audioDao.save(audio);

        Audio savedAudio = audioDao.load(audio.getId());

        Assert.assertNotNull(savedAudio);
        Assert.assertEquals(audio.getId(), savedAudio.getId());
        Assert.assertEquals(audio.getTitle(), savedAudio.getTitle());
    }

    @Test
    public void testAudioAddWithAlbum() throws Exception {
        Audio audio = new Audio();
        audio.setTitle("FirstAudio");
        audio.setDuration(111);

        Album album = new Album();
        album.setId("some_random");
        album.setName("FirstAlbum");

        audio.setAlbums(Arrays.asList(album));

        AudioDao audioDao = new AudioDao();
        audioDao.save(audio);

        Audio savedAudio = audioDao.load(audio.getId());

        Assert.assertNotNull(savedAudio);
        Assert.assertEquals(audio.getId(), savedAudio.getId());
        Assert.assertEquals(audio.getTitle(), savedAudio.getTitle());
        Assert.assertNotNull(savedAudio.getAlbums());
        Assert.assertEquals(1, savedAudio.getAlbums().size());

        Album album1 = savedAudio.getAlbums().get(0);
        Assert.assertEquals(album.getId(), album1.getId());
        Assert.assertNull(album1.getName());
    }
}

class PlayerDatabase {
    public static abstract class AudioEntry implements BaseColumns {
        public static final String TABLE_NAME = "AUDIOS";

        public static final String COLUMN_TITLE = "TITLE";
        public static final String COLUMN_DURATION = "DURATION";
    }
}

class PlayerOpenHelper extends SQLiteOpenHelper {

    private static final String CREATE_AUDIOS =
            "create table if not exists " + PlayerDatabase.AudioEntry.TABLE_NAME + " (" +
                    PlayerDatabase.AudioEntry._ID + " integer primary key," +
                    PlayerDatabase.AudioEntry.COLUMN_TITLE + " text," +
                    PlayerDatabase.AudioEntry.COLUMN_DURATION + " integer)";

    private static final String DELETE_AUDIOS =
            "drop table if exists " + PlayerDatabase.AudioEntry.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("");
    }


    private final static String DB_NAME = "player.db";
    private final static int DB_VERSION = 1;

    public PlayerOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //...........


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("");
        onCreate(db);
    }
}
