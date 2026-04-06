package com.example.fittrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

// March 29 - dashboard setup
// UPDATED:April 2 - navigation + dropdowns added
// gawa ng login+register after ng database

public class MainActivity extends AppCompatActivity {

    Spinner goalSpinner, activitySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        goalSpinner = findViewById(R.id.goalSpinner);
        activitySpinner = findViewById(R.id.activitySpinner);

        ArrayAdapter<CharSequence> goalAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.fitness_goals,
                android.R.layout.simple_spinner_item
        );
        goalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        goalSpinner.setAdapter(goalAdapter);

        ArrayAdapter<CharSequence> activityAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.activity_levels,
                android.R.layout.simple_spinner_item
        );
        activityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activitySpinner.setAdapter(activityAdapter);
    }

    public void goHome(View view) {}

    public void goTracker(View view) {
        startActivity(new Intent(this, WorkoutActivity.class));
    }

    public void goBilling(View view) {
        startActivity(new Intent(this, BillingActivity.class));
    }

    public void goMore(View view) {
        startActivity(new Intent(this, TrainerDashboardActivity.class));
    }
}