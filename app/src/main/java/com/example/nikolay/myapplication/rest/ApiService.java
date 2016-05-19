package com.example.nikolay.myapplication.rest;

import com.example.nikolay.myapplication.models.Audio;
import com.example.nikolay.myapplication.rest.responses.AudioGetResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface ApiService {

    @GET("audio.get")
    Call<AudioGetResponse> getAudios(@Query("access_token") String token);

    @GET("audio.get")
    Call<ResponseBody> getAudiosRaw(@Query("access_token") String token);
}
