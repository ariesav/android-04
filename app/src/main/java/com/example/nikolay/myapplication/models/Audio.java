package com.example.nikolay.myapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

@Root(name = "audio")
public class Audio implements Serializable {
    @Attribute(name = "title")
    @SerializedName("title")
    private String title;

    @Attribute(name = "duration")
    @SerializedName("duration")
    private int duration;

    @Expose
    private transient List<Author> authors;

    @Expose
    private transient List<Album> albums;

    @Expose
    private transient List<Band> bands;

    public List<Band> getBands() {
        return bands;
    }

    public void setBands(List<Band> bands) {
        this.bands = bands;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(authors.size());
        for (Author au : authors) {
            out.writeLong(au.getId());
        }

    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            Author object = new Author();
            object.setId(in.readLong());
            authors.add(object);
        }
    }
}
