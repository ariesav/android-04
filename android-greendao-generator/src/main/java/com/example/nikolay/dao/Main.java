package com.example.nikolay.dao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class Main {
    public static void main(String[] args)
            throws Exception {
        Schema schema = new Schema(1, "com.example.nikolay.myapplication.gdao");

        Entity audio = schema.addEntity("Audio");
        audio.addIdProperty().notNull();
        audio.addStringProperty("title").notNull();
        audio.addIntProperty("duration");
        Property authorId = audio
                .addLongProperty("authorId")
                .notNull().getProperty();

        Entity author = schema.addEntity("Author");
        author.addIdProperty().notNull();
        author.addStringProperty("name");
        author.addDateProperty("birthday");

        audio.addToMany(author, authorId);

        String output = "app/src/main/java/";

        new DaoGenerator().generateAll(schema, output);
    }
}
