package com.example.drassist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Topics extends AppCompatActivity {

    CardView topic1, topic2, topic3,topic4;
    private static final String TAG = "Topics";
    ArrayList<TopicsModel.Article> articles = new ArrayList<>();;


    private TopicAdapter TopicServer = null;
    private RecyclerView TopicRecycler= null;
    private GestureDetectorCompat detector = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);



 // need to chnage it .
        TopicRecycler = findViewById(R.id.roster_item);
        TopicServer =new TopicAdapter(this);
        TopicRecycler.setAdapter(TopicServer);
        LinearLayoutManager myManager = new LinearLayoutManager((this));
        //GridLayoutManager myManager = new GridLayoutManager(this, 10);
        TopicRecycler.setLayoutManager(myManager);
        detector = new GestureDetectorCompat(this,
                new RecyclerViewOnGestureListener());
        TopicRecycler.addOnItemTouchListener(
                new RecyclerView.SimpleOnItemTouchListener() {
                    @Override
                    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                        return detector.onTouchEvent(e);
                    }
                });
        TopicServer.notifyDataSetChanged();
        System.out.println("output");
//
//        topic1 = findViewById(R.id.topic1);
//        topic1.setOnClickListener(v -> playVideo("ONTly_lRM4I"));
//        topic2 = findViewById(R.id.topic2);
//        topic2.setOnClickListener(v -> topicdetails(2));
//        topic3 = findViewById(R.id.topic3);
//        topic3.setOnClickListener(v -> playVideo("LPoOFqYr6vE"));
//        topic4 = findViewById(R.id.topic4);
//        topic4.setOnClickListener(v -> topicdetails(4));
    }

    private void playVideo(String videoId) {
        Intent intent = new Intent(this, VideoActivity.class);
        intent.putExtra("videoid", videoId);
        startActivity(intent);

    }

//     private void topicdetails(int position) {


//         Log.d(TAG,  "size of this is  " +  articles.size());



//         for (TopicsModel.Article a : articles) {
//             Log.d(TAG,  "Article Title is method: " +  a.getTitle());
//         }
// TopicAdapter ta=new TopicAdapter();

//         try {
//             Thread.sleep(2000);
//             //topicdetails(position);
//         } catch (InterruptedException e1) {
//             e1.printStackTrace();
//         }

//         Intent i =new Intent(Topics.this,TopicDetails.class);
//         i.putExtra("title",ta.articles.get(position).getTitle());
//         startActivity(i);

//     }


    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view =TopicRecycler.findChildViewUnder(e.getX(), e.getY());

            if (view != null) {
                RecyclerView.ViewHolder holder = TopicRecycler.getChildViewHolder(view);
                if (holder instanceof TopicAdapter.TopicViewHolder) {
                    int position = holder.getAdapterPosition();

                    ConstraintLayout holderCV = (ConstraintLayout) holder.itemView;
                    TextView title = holderCV.findViewById(R.id.rt1);
                    TextView description = holderCV.findViewById(R.id.rt2);
                    TextView image = holderCV.findViewById(R.id.rt3);

//                    ImageView image = holderCV.findViewById(R.id.rimg1);

                    Intent i =new Intent(Topics.this,TopicDetails.class);
                    i.putExtra("title",title.getText());
                    i.putExtra("description",description.getText());
                    i.putExtra("image",image.getText());
                    startActivity(i);
                    return true; // Use up the tap gesture
                }
            }
// we didn't handle the gesture so pass it on
            return false;
        }
    }
}