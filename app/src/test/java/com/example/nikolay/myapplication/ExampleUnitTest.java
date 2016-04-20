package com.example.nikolay.myapplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void addition_isNotCorrect() throws Exception {
        assertEquals(5, 2 + 2);
    }

    @Test
    public void rectangleCheck() throws Exception {
        Rectangle rectangle = new Rectangle(10, 11);
        assertEquals(rectangle.getX(), 10);
    }
}