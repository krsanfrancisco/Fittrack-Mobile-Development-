package com.example.fittrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

// UPDATED:April 2 - billing screen

public class BillingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);
    }

    public void goHome(View view) {
        Intent intent = new Intent(BillingActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void goTracker(View view) {
        Intent intent = new Intent(BillingActivity.this, WorkoutActivity.class);
        startActivity(intent);
    }

    public void goBilling(View view) {
    }

    public void goMore(View view) {
        Intent intent = new Intent(BillingActivity.this, TrainerDashboardActivity.class);
        startActivity(intent);
    }
}