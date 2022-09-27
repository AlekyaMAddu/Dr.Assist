package com.example.drassist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class BodyMassIndex extends AppCompatActivity {
    EditText heighET;
    EditText weightET;
    boolean isAllFieldsChecked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_mass_index);

        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        float bmi = sp.getFloat("bmi", (float)0.0);
        TextView resultTV=findViewById(R.id.resultTV);
        resultTV.setText("The most recent bmi you calculated is "+bmi);
    }
    public void returnToSelfCheckActivityDashboard(View view){
        finish();
    }

    public void onClick(View view){
        isAllFieldsChecked = CheckAllFields();
        if (isAllFieldsChecked) {
            heighET = findViewById(R.id.heightET);
            weightET = findViewById(R.id.weightET);
            double height = Double.parseDouble(heighET.getText().toString());
            double weight = Double.parseDouble(weightET.getText().toString());
            double bmi = Math.round(((703) * (weight / (height * height))) * 100.0) / 100.0;
            TextView resultTV = findViewById(R.id.resultTV);

            SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();

            if (bmi <= 18.5) {
                resultTV.setText("Your Body Mass Index Value is : " + bmi + "\n Your Weight status is : Underweight");
            } else if (bmi >= 18.6 && bmi <= 24.9) {
                resultTV.setText("Your Body Mass Index Value is : " + bmi + "\n Your Weight status is : Normal weight");
            } else if (bmi >= 25 && bmi <= 29.9) {
                resultTV.setText("Your Body Mass Index Value is : " + bmi + "\n Your Weight status is : Overweight");
            } else {
                resultTV.setText("Your Body Mass Index Value is : " + bmi + "\n Your Weight status is : Obesity");
            }

            edit.putFloat("bmi",(float)bmi);
            edit.commit();

        }
    }
    private boolean CheckAllFields() {

        heighET=findViewById(R.id.heightET);
        weightET=findViewById(R.id.weightET);
        if(heighET.length()==0 && weightET.length()==0){
            heighET.setError("This field is required");
            weightET.setError("This field is required");
            return false;
        }
        if (heighET.length() == 0) {
            heighET.setError("This field is required");

            return false;
        }

        if (weightET.length() == 0) {
            weightET.setError("This field is required");

            return false;
        }



        // after all validation return true.
        return true;
    }
}