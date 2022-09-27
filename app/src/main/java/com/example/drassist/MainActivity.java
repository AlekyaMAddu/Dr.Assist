package com.example.drassist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    CardView selfcheck,topics;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selfcheck=findViewById(R.id.btnSelfcheck);
        selfcheck.setOnClickListener(v -> selfCheck(v));
        topics = findViewById(R.id.btnTopics);
        topics.setOnClickListener(v -> topics());
//
//        TopicAPI service = RetrofitClient.getRetrofitInstance().create(TopicAPI.class);
//        Call<TopicsModel> call = service.getAllTopics();
//        call.enqueue(new Callback<TopicsModel>() {
//            @Override
//            public void onResponse(Call<TopicsModel> call, Response<TopicsModel> response) {
//                Log.d(TAG, "Response Code: " + response.code());
//
//                ArrayList<TopicsModel.Article> articles = response.body().getArticles();
//
//                for (TopicsModel.Article a : articles) {
//                    Log.d(TAG,  "Article Title is: " +  a.getTitle());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<TopicsModel> call, Throwable t) {
//
//            }
//        });


    }
    public void selfCheck(View v){
        Intent explicit = new Intent(MainActivity.this, SelfCheck.class);
        startActivity(explicit );
 

    }
    public void topics(){
        startActivity(new Intent(MainActivity.this,Topics.class));
    }

}
