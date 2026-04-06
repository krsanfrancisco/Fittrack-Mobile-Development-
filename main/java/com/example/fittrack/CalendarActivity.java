package com.example.fittrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

// UPDATED:April 1 - calendar screen

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
    }

    // Footer Navigation

    public void goHome(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

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