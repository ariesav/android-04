package com.example.nikolay.myapplication.dao;

import android.util.Log;
import com.example.nikolay.myapplication.models.Album;
import com.example.nikolay.myapplication.models.Audio;
import com.example.nikolay.myapplication.models.Author;
import com.example.nikolay.myapplication.models.Band;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AudioDao {

    public static final String FILE_NAME = "audio.dat";
    private static final String TAG = AudioDao.class.getSimpleName();

    public boolean save(Audio audio) {
        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(Album.class, new AlbumParser());
        gsonBuilder.registerTypeAdapter(Band.class, new BandParser());
        gsonBuilder.registerTypeAdapter(Author.class, new AuthorParser());

        Gson gson = gsonBuilder.create();
        final List<Audio> audios = new ArrayList<Audio>();
        File data = new File(FILE_NAME);

        audio.setId(UUID.randomUUID().clockSequence());

        if (data.exists()) {
            FileReader fileReader = null;
            try {
                fileReader = new FileReader(data);
                Type audioType = new TypeToken<List<Audio>>() {
                }.getType();
                List<Audio> existedAudios = gson.fromJson(fileReader,
                        audioType);
                audios.addAll(existedAudios);
            } catch (FileNotFoundException e) {
                Log.e(TAG, "save: ", e);
                return false;
            } finally {
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException e) {
                        Log.w(TAG, "save: ", e);
                    }
                }
            }
        }

        audios.add(audio);

        FileWriter writer = null;
        try {
            writer = new FileWriter(FILE_NAME);
            gson.toJson(audios, writer);
            writer.flush();
        } catch (IOException e) {
            Log.e(TAG, "save: ", e);
            return false;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    Log.e(TAG, "save: ", e);
                }
            }
        }
        return true;
    }

    public Audio load(long id) {
        List<Audio> audios = loadAll();
        if (audios == null) {
            return null;
        }
        for (Audio audio : audios) {
            if (audio.getId() == id) {
                return audio;
            }
        }
        return null;
    }

    public List<Audio> loadAll() {
        File data = new File(FILE_NAME);

        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(Album.class, new AlbumParser());
        gsonBuilder.registerTypeAdapter(Band.class, new BandParser());
        gsonBuilder.registerTypeAdapter(Author.class, new AuthorParser());

        Gson gson = gsonBuilder.create();

        if (data.exists()) {
            FileReader fileReader = null;
            try {
                fileReader = new FileReader(data);
                Type audioType = new TypeToken<List<Audio>>() {
                }.getType();
                List<Audio> existedAudios = gson.fromJson(fileReader,
                        audioType);
                return existedAudios;
            } catch (FileNotFoundException e) {
                Log.e(TAG, "save: ", e);
                return null;
            } finally {
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException e) {
                        Log.w(TAG, "save: ", e);
                    }
                }
            }
        }
        return null;
    }

    private static class AlbumParser implements JsonSerializer<Album>,
            JsonDeserializer<Album> {

        @Override
        public JsonElement serialize(Album src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.getId());
        }

        @Override
        public Album deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            Album album = new Album();
            album.setId(json.getAsString());
            return album;
        }
    }

    private static class BandParser implements JsonSerializer<Band>,
            JsonDeserializer<Band> {

        @Override
        public JsonElement serialize(Band src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.getId());
        }


        @Override
        public Band deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            Band band = new Band();
            band.setId(json.getAsString());
            return band;
        }
    }

    private static class AuthorParser implements JsonSerializer<Author>,
            JsonDeserializer<Author> {

        @Override
        public JsonElement serialize(Author src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.getId());
        }

        @Override
        public Author deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            Author author = new Author();
            author.setId(json.getAsLong());
            return author;
        }
        //TODO 1
    }
}
