package com.example.nikolay.myapplication.rest.responses;

import com.example.nikolay.myapplication.models.Audio;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AudioGetResponse {

    @SerializedName("response")
    private List<Audio> response;

    public List<Audio> getResponse() {
        return response;
    }

    public int getCount(){
        return response.size();
    }
}
