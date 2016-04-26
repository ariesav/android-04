package com.example.nikolay.myapplication;

import com.example.nikolay.myapplication.dao.AudioDao;
import com.example.nikolay.myapplication.models.Audio;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {

    @Before
    public void beforeTest() {
        System.out.println("before test");
    }

    @After
    public void afterTest() {
        System.out.println("after test");
    }
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void addition_isNotCorrect() throws Exception {
        Assert.assertNotEquals(5, 2 + 2);
    }

    @Test
    public void testAudioWrite() throws Exception {
        Audio audio = new Audio();
        audio.setTitle("aaaaa");
        audio.setDuration(111);
        AudioDao audioDao = new AudioDao();
        audioDao.save(audio);
    }
}