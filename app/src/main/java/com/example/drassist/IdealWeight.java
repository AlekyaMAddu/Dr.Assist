package com.example.drassist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class IdealWeight extends AppCompatActivity {
private String gender="male";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideal_weight);
        
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        float idealWeight = sp.getFloat("idealWeight", (float)0.0);
        TextView resultTV=findViewById(R.id.resultTV);
        resultTV.setText("The most recent ideal Weight you calculated is "+idealWeight+"lb");

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




    public void calculateIdealWeight(View view){
        TextView resultTV=findViewById(R.id.resultTV);
        double idealWeight=0;
        EditText heightET=findViewById(R.id.heightET);
        double height=0;
        try {
            height  = Integer.parseInt(heightET.getText().toString());
            if(height>140) {
                double heightInInch = height / 2.54;
                if (gender == "male") {
                    idealWeight = 52 + (1.9 * (heightInInch - 60));

                } else {
                    idealWeight = 49 + (1.7 * (heightInInch - 60));

                }
                double weightInkg=Math.round(idealWeight*100.0)/100.0;
                double weightInLb=Math.round((idealWeight*2.2)*100.0)/100.0;
                SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putFloat("idealWeight",(float)weightInLb);
                resultTV.setText("Your Ideal Body Weight is "+weightInkg+" kg or "+weightInLb +" lb");
                edit.commit();
                resultTV.setText("Your Ideal Body Weight is "+weightInkg+" kg or "+weightInLb +" lb");
            }
            else{
                heightET.setError("please enter height greater than 140cm");
            }



        }
        catch (Exception exception){
            heightET.setError("This field is required");
        }


    }

}