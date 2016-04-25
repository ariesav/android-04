package com.example.nikolay.myapplication.models;

import java.util.List;

public class Album {

    private String name;
    private int dateOfPublishing;
    private List<Audio> audios;
    private List<Band> bands;

    public List<Band> getBands() {
        return bands;
    }

    public void setBands(List<Band> bands) {
        this.bands = bands;
    }

    public List<Audio> getAudios() {
        return audios;
    }

    public void setAudios(List<Audio> audios) {
        this.audios = audios;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDateOfPublishing() {
        return dateOfPublishing;
    }

    public void setDateOfPublishing(int dateOfPublishing) {
        this.dateOfPublishing = dateOfPublishing;
    }
}
