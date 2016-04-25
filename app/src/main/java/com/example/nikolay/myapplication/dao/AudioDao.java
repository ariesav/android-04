package com.example.nikolay.myapplication.dao;

import com.example.nikolay.myapplication.models.Audio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class AudioDao {

    private static final String FILE_NAME = "audio.dat";

    public boolean save(Audio audio) {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(FILE_NAME);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(audio);
            out.close();
            fileOut.close();
            return true;
        } catch (IOException i) {
            i.printStackTrace();
        }
        return false;
    }

    public Audio load(long id) {
        return null;
    }

    public List<Audio> loadAll() {
        return null;
    }
}
