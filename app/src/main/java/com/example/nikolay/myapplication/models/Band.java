package com.example.nikolay.myapplication.models;

import java.util.List;

public class Band {
    private String name;

    private List<Author> participants;

    private List<Audio> audios;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Author> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Author> participants) {
        this.participants = participants;
    }

    public List<Audio> getAudios() {
        return audios;
    }

    public void setAudios(List<Audio> audios) {
        this.audios = audios;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
