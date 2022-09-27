package com.example.drassist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class TopicDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_details);
        String title=getIntent().getStringExtra("title");
        String description=getIntent().getStringExtra("description");
        String image=getIntent().getStringExtra("image");
        TextView t1 =findViewById(R.id.title);
        TextView t2 =findViewById(R.id.description);
        ImageView myImageView =findViewById(R.id.image);

        Picasso.with(this).load(image)
                .into(myImageView);
        t1.setText(title);
        t2.setText(description);

    }
}