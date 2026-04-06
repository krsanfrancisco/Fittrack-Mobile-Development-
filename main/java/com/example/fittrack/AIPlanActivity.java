package com.example.fittrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

// UPDATED:April 1 - AI workout plan screen, place holder. API ng ai dito

public class AIPlanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_plan);
    }


    public void goHome(View view) {
        Intent intent = new Intent(AIPlanActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void goTracker(View view) {
        Intent intent = new Intent(AIPlanActivity.this, WorkoutActivity.class);
        startActivity(intent);
    }

    public void goBilling(View view) {
        Intent intent = new Intent(AIPlanActivity.this, BillingActivity.class);
        startActivity(intent);
    }

    public void goMore(View view) {
        Intent intent = new Intent(AIPlanActivity.this, TrainerDashboardActivity.class);
        startActivity(intent);
    }
}