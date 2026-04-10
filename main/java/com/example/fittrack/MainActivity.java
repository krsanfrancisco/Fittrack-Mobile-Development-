package com.example.fittrack;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

// March 29 - dashboard setup
// UPDATED:April 2 - navigation + dropdowns added
// gawa ng login+register after ng database

public class MainActivity extends AppCompatActivity {

    Spinner goalSpinner, activitySpinner;
    ListView workoutList;
    DatabaseHelper db;
    SessionManager session;

    ArrayList<Integer> workoutIds = new ArrayList<>();
    ArrayList<String> workoutData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goalSpinner = findViewById(R.id.goalSpinner);
        activitySpinner = findViewById(R.id.activitySpinner);
        workoutList = findViewById(R.id.workoutList);

        db = new DatabaseHelper(this);
        session = new SessionManager(this);

        setupSpinners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadWorkouts();
    }

    private void setupSpinners(){
        String[] goals = {"Lose Weight", "Gain Muscle", "Maintain"};
        String[] activity = {"Beginner", "Intermediate", "Advanced"};

        ArrayAdapter<String> goalAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, goals);
        goalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        goalSpinner.setAdapter(goalAdapter);

        ArrayAdapter<String> activityAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, activity);
        activityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activitySpinner.setAdapter(activityAdapter);
    }

    private void loadWorkouts(){

        workoutIds.clear();
        workoutData.clear();

        Cursor cursor = db.getWorkouts();

        if(cursor != null && cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String muscle = cursor.getString(2);
                String sets = cursor.getString(3);
                String reps = cursor.getString(4);
                int completed = cursor.getInt(5);

                workoutIds.add(id);

                String text = name + " (" + muscle + ") - " + sets + "x" + reps;
                if(completed == 1){
                    text += " ✔ Completed";
                }

                workoutData.add(text);

            } while(cursor.moveToNext());

            cursor.close();
        }

        workoutList.setAdapter(new WorkoutAdapter());
    }

    class WorkoutAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return workoutData.size();
        }

        @Override
        public Object getItem(int i) {
            return workoutData.get(i);
        }

        @Override
        public long getItemId(int i) {
            return workoutIds.get(i);
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {

            if(view == null){
                view = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.item_workout, parent, false);
            }

            TextView text = view.findViewById(R.id.workoutText);
            Button deleteBtn = view.findViewById(R.id.deleteBtn);

            text.setText(workoutData.get(i));

            deleteBtn.setOnClickListener(v -> {
                db.deleteWorkout(workoutIds.get(i));
                loadWorkouts();
                Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
            });

            return view;
        }
    }

    public void logout(View view){
        session.logout();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
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