package com.example.drassist;

import android.graphics.ColorSpace;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TopicAPI {

    @GET("v2/top-headlines?q=health&apiKey=374df263f8fc4f3fa573ab276f1a1226")
   Call<TopicsModel> getAllTopics();
}
