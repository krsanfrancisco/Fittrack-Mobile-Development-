package com.example.fittrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

// UPDATED:April 2 - trainer dashboard

public class TrainerDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_dashboard);
    }

    public void goHome(View view) {
        Intent intent = new Intent(TrainerDashboardActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void goTracker(View view) {
        Intent intent = new Intent(TrainerDashboardActivity.this, WorkoutActivity.class);
        startActivity(intent);
    }

    public void goBilling(View view) {
        Intent intent = new Intent(TrainerDashboardActivity.this, BillingActivity.class);
        startActivity(intent);
    }

    public void goMore(View view) {
    }
}