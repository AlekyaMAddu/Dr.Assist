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

public class BloodAlcohol extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String gender="male";
    EditText weightET;
    EditText numberOfDrinksET;
    EditText hoursET;
    boolean isAllFieldsChecked = false;
    TextView   tvResult ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_alcohol);
        Spinner spinner = (Spinner) findViewById(R.id.beverageTypeSpinner);
        spinner.setOnItemSelectedListener(this);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.beverage_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        float BAC = sp.getFloat("BAC", (float)0.0);
        TextView resultTV=findViewById(R.id.tvResult);
        resultTV.setText("The most recent BAC you calculated is "+BAC+"%");
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item=  adapterView.getItemAtPosition(i).toString();
        Log.d("hello",item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void calculateBAC(View view){
        weightET=findViewById(R.id.weightET);
        numberOfDrinksET=findViewById(R.id.numberOFDrinksET);
        hoursET=findViewById(R.id.hoursET);
        isAllFieldsChecked = CheckAllFields();
        if (isAllFieldsChecked) {
            int numberOfDrinks = Integer.parseInt(numberOfDrinksET.getText().toString());
            double hours = Double.parseDouble(hoursET.getText().toString());
            double r = 0;

            if (gender == "male") {
                r = 0.55;
            } else {
                r = 0.68;
            }
            double bodyWeightInGrams = 453.592 * Double.parseDouble(weightET.getText().toString());
            double alcoholConsumedInGrams = numberOfDrinks * 14;
            double percentageBAC = ((alcoholConsumedInGrams) / (bodyWeightInGrams * r)) * 100;
            double approxBAC = Math.round((percentageBAC - (hours * 0.015)) * 100.0) / 100.0;
            Log.d("approxBAC :", String.valueOf(approxBAC));

            SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putFloat("BAC",(float)approxBAC);
            edit.commit();
            tvResult = findViewById(R.id.tvResult);
            tvResult.setText("Your Blood Alcohol Content : " + String.valueOf(approxBAC));
        }
    }

    private boolean CheckAllFields() {

        if(weightET.length()==0 && numberOfDrinksET.length()==0 && hoursET.length() == 0){
            weightET.setError("This field is required");
            numberOfDrinksET.setError("This field is required");
            hoursET.setError("This field is required");
            return false;
        }
        if (weightET.length() == 0) {
            weightET.setError("This field is required");

            return false;
        }

        if (numberOfDrinksET.length() == 0) {
            numberOfDrinksET.setError("This field is required");

            return false;
        }

        if (hoursET.length() == 0) {
            hoursET.setError("This field is required");

            return false;
        }

        // after all validation return true.
        return true;
    }
}