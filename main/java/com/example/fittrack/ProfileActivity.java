package com.example.fittrack;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

// April 7 - profile setup

public class ProfileActivity extends AppCompatActivity {

    EditText ageInput, heightInput, weightInput;
    Spinner goalSpinner, activitySpinner;

    DatabaseHelper db;
    SessionManager session;

    String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ageInput = findViewById(R.id.ageInput);
        heightInput = findViewById(R.id.heightInput);
        weightInput = findViewById(R.id.weightInput);
        goalSpinner = findViewById(R.id.goalSpinner);
        activitySpinner = findViewById(R.id.activitySpinner);

        db = new DatabaseHelper(this);
        session = new SessionManager(this);

        currentUser = session.getUsername();

        if(currentUser == null || currentUser.isEmpty()){
            Toast.makeText(this, "Session error. Please login again.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        setupSpinners();
        loadProfile();
    }

    private void setupSpinners(){
        String[] goals = {"Lose Weight", "Gain Muscle", "Maintain"};
        String[] activity = {"Beginner", "Intermediate", "Advanced"};

        goalSpinner.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, goals));

        activitySpinner.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, activity));
    }

    private void loadProfile(){
        Cursor cursor = db.getProfile(currentUser);

        if(cursor != null && cursor.moveToFirst()){
            ageInput.setText(cursor.getString(3));
            heightInput.setText(cursor.getString(4));
            weightInput.setText(cursor.getString(5));
            cursor.close();
        }
    }

    public void saveProfile(View view){

        db.saveProfile(
                currentUser,
                ageInput.getText().toString(),
                heightInput.getText().toString(),
                weightInput.getText().toString(),
                goalSpinner.getSelectedItem().toString(),
                activitySpinner.getSelectedItem().toString()
        );

        Toast.makeText(this, "Profile Saved", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}