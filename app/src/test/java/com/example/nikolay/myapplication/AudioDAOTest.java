package com.example.nikolay.myapplication;

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