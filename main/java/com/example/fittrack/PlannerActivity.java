package com.example.fittrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

// March 31 - planner screen

public class PlannerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner);
    }

    public void goHome(View view) {
        Intent intent = new Intent(PlannerActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void goTracker(View view) {
        Intent intent = new Intent(PlannerActivity.this, WorkoutActivity.class);
        startActivity(intent);
    }

    public void goBilling(View view) {
        Intent intent = new Intent(PlannerActivity.this, BillingActivity.class);
        startActivity(intent);
    }

    public void goMore(View view) {
        Intent intent = new Intent(PlannerActivity.this, TrainerDashboardActivity.class);
        startActivity(intent);
    }
}