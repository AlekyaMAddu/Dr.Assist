package com.example.drassist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopicAdapter extends
        RecyclerView.Adapter<TopicAdapter.TopicViewHolder>{
    private static final String TAG = "TopicsAdapter";
    ArrayList<TopicsModel.Article> articles = new ArrayList<>();;
    public static class TopicViewHolder extends RecyclerView.ViewHolder {

        public String moreUsefulThings = "";

        public TopicViewHolder (View v, String abit){
            super(v);
            moreUsefulThings = abit;

        }
        public TopicViewHolder(View v){
            super(v);
        }
    }
// Need to recreate model instance
   // private PlayersModel theModel = null;

    public ArrayList<TopicsModel.Article> getdata(){
        Log.d(TAG,  "size of this in getdata  " +  articles.size());

        return articles;

    }
    Context context ;
    public TopicAdapter(Context context){
        super();

       this.context=context;
        TopicAPI service = RetrofitClient.getRetrofitInstance().create(TopicAPI.class);
        Call<TopicsModel> call = service.getAllTopics();
        call.enqueue(new Callback<TopicsModel>() {
            @Override
            public void onResponse(Call<TopicsModel> call, Response<TopicsModel> response) {
                Log.d(TAG, "Response Code: " + response.code());
                //  articles = response.body().getArticles();
                ArrayList<TopicsModel.Article> ar = response.body().getArticles();

                articles = new ArrayList<>();
                for (TopicsModel.Article a : ar) {
                    articles.add(a);
                }
                for (TopicsModel.Article a : articles) {
                    Log.d(TAG,  "Article Title is loop: " +  a.getTitle());
                }
            }

            @Override
            public void onFailure(Call<TopicsModel> call, Throwable t) {

            }
        });


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG,  "size of this is  " +  articles.size());

        for (TopicsModel.Article a : articles) {
            Log.d(TAG,  "Article Title is out loop: " +  a.getTitle());
        }

        // Need to create and instance
       // theModel = PlayersModel.getSingleton();
    }
    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a TextHolder
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_topic_view, parent, false);
        TopicViewHolder vh = new TopicViewHolder(v);

        for (TopicsModel.Article a : articles) {
            Log.d(TAG,  "Article Title is create: " +  a.getTitle());
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {
        Log.d(TAG,  "In  bind");

        ConstraintLayout holderCV = (ConstraintLayout) holder.itemView;
        TextView title = holderCV.findViewById(R.id.rt1);
        TextView description = holderCV.findViewById(R.id.rt2);
        TextView image = holderCV.findViewById(R.id.rt3);

        ImageView myImageView = holderCV.findViewById(R.id.rimg1);
        Picasso.with(this.context).load(articles.get(position).getUrlToImage())
                .into(myImageView);
        image.setText(articles.get(position).getUrlToImage());
        //title.setText(articles.get(position).getTitle());
        title.setText(articles.get(position).getTitle());
        description.setText(articles.get(position).getDescription());
        Log.d(TAG,  "Article Title is in bind: " +  articles.get(position).getTitle());




    }

    @Override
    public int getItemCount() {
        return articles.size();
    }


}
