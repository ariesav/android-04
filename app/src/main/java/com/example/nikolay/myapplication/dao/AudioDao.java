package com.example.nikolay.myapplication.dao;

import com.example.nikolay.myapplication.models.Audio;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AudioDao {

    private static final String FILE_NAME = "audio.dat";

    private List<Audio> audios = new ArrayList<Audio>();

    private boolean loaded = false;

    public boolean save(Audio audio) {
        try {
            Gson gson = new Gson();
            if (!loaded) {
                Audio[] audios = gson.fromJson(new FileReader(FILE_NAME),
                        Audio[].class);
                this.audios.addAll(Arrays.asList(audios));
            }

            audios.add(audio);

            FileWriter writer = new FileWriter(FILE_NAME);
            gson.toJson(audios, writer);
            writer.flush();
            writer.close();
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
