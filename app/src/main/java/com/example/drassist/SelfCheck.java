package com.example.drassist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelfCheck extends AppCompatActivity {
    CardView bloodalcohol,bmi,bodyfat,idealweight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_check);

        bloodalcohol=findViewById(R.id.btnalcohol);
        bloodalcohol.setOnClickListener(v -> bloodalcoholCalc(v));
        bmi=findViewById(R.id.btnbmi);
        bmi.setOnClickListener(v -> bmiCalc(v));
        bodyfat = findViewById(R.id.btnbodyfat);
        bodyfat.setOnClickListener(v -> bodyfatCalc(v));
        idealweight = findViewById(R.id.btnidealweight);
        idealweight.setOnClickListener(v -> idealweightCalc(v));
    }

    public void bmiCalc(View v){
        Intent explicit = new Intent(this, BodyMassIndex.class);
        startActivity(explicit );
    }
    public void bloodalcoholCalc(View v){
        Intent explicit = new Intent(this, BloodAlcohol.class);
        startActivity(explicit );
    }
    public void bodyfatCalc(View v){
        Intent explicit = new Intent(this, BodyFat.class);
        startActivity(explicit );
    }
    public void idealweightCalc(View v){
        Intent explicit = new Intent(this, IdealWeight.class);
        startActivity(explicit );
    }
}