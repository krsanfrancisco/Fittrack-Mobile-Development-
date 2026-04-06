package com.example.fittrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

// March 30 - workout tracker screen
// UPDATED:April 2 - navigation added to match footer

public class WorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
    }

    public void goHome(View view) {
        Intent intent = new Intent(WorkoutActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void goTracker(View view) {
    }

    public void goBilling(View view) {
        Intent intent = new Intent(WorkoutActivity.this, BillingActivity.class);
        startActivity(intent);
    }

    public void goMore(View view) {
        Intent intent = new Intent(WorkoutActivity.this, TrainerDashboardActivity.class);
        startActivity(intent);
    }
}