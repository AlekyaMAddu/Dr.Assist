package com.example.drassist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class BodyFat extends AppCompatActivity {
    EditText weightET;
    EditText heightET;
    EditText ageET;
    private String gender="male";
    private int height;
    private double weight;
    private int age;
    boolean isAllFieldsChecked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_fat);

        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        float bodyFat = sp.getFloat("bodyFat", (float)0.0);
        TextView resultTV=findViewById(R.id.tvResult);
        resultTV.setText("The most recent result for BodyFat is "+bodyFat);
    }
    public void onClick(View view) {
        isAllFieldsChecked = CheckAllFields();
        if (isAllFieldsChecked) {
            weightET = findViewById(R.id.weightET);
            heightET = findViewById(R.id.heightET);
            ageET = findViewById(R.id.ageET);
            weight = Double.parseDouble(weightET.getText().toString());
            height = Integer.parseInt(heightET.getText().toString());
            age = Integer.parseInt(ageET.getText().toString());
            double weightInKg = weight / 2.2;
            double heightInMeters = height * 0.01;
            double bmi = (weightInKg / (heightInMeters * heightInMeters));
            Log.d("bmi", String.valueOf(bmi));
            double bodyFat = 0;
            SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            if (gender.equalsIgnoreCase("male")) {
                bodyFat = (1.39 * bmi) + (0.16 * age) - (10.34 * 1) - 9;
            } else {
                bodyFat = (1.39 * bmi) + (0.16 * age) - (10.34 * 0) - 9;
            }
            edit.putFloat("bodyFat",(float)bodyFat);
            edit.commit();
            TextView resultTV = findViewById(R.id.tvResult);

            resultTV.setText("Your Body Fat Percentage is : " + Math.round(bodyFat * 100.0) / 100.0 + " %");
        }
    }
    public void returnToSelfCheckActivityDashboard(View view){
        finish();
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.maleRadioBTN:
                if (checked)
                    gender="male";
                break;
            case R.id.femaleRadioBTN:
                if (checked)
                    gender="female";
                break;
        }
    }

    private boolean CheckAllFields() {
        weightET=findViewById(R.id.weightET);
        heightET=findViewById(R.id.heightET);
        ageET=findViewById(R.id.ageET);
if(ageET.length() == 0 && heightET.length() == 0 && weightET.length() == 0){
    ageET.setError("This field is required");
    heightET.setError("This field is required");
    weightET.setError("This field is required");
    return false;
}
        if (ageET.length() == 0) {
            ageET.setError("This field is required");

            return false;
        }

        if (heightET.length() == 0) {
            heightET.setError("This field is required");

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