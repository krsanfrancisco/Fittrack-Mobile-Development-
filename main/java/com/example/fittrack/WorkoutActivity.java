package com.example.fittrack;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.*;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

// March 30 - workout tracker screen
// UPDATED:April 2 - navigation added to match footer
//UPDATED: April 7 - added inputs (name, muscle group, sets, reps)

public class WorkoutActivity extends AppCompatActivity {

    EditText nameInput, setsInput, repsInput;
    Spinner muscleSpinner;
    ListView checklist;

    DatabaseHelper db;

    ArrayList<Integer> ids = new ArrayList<>();
    ArrayList<String> data = new ArrayList<>();
    ArrayList<Boolean> status = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        nameInput = findViewById(R.id.workoutNameInput);
        setsInput = findViewById(R.id.setsInput);
        repsInput = findViewById(R.id.repsInput);
        muscleSpinner = findViewById(R.id.muscleSpinner);
        checklist = findViewById(R.id.checklist);

        db = new DatabaseHelper(this);

        String[] muscles = {"Chest","Back","Legs","Arms","Shoulders","Core"};
        muscleSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, muscles));

        loadChecklist();
    }

    public void saveWorkout(View v){
        db.addWorkout(
                nameInput.getText().toString(),
                muscleSpinner.getSelectedItem().toString(),
                setsInput.getText().toString(),
                repsInput.getText().toString()
        );

        loadChecklist();
    }

    private void loadChecklist(){

        ids.clear();
        data.clear();
        status.clear();

        Cursor c = db.getWorkouts();

        if(c.moveToFirst()){
            do{
                ids.add(c.getInt(0));
                data.add(c.getString(1));
                status.add(c.getInt(5) == 1);
            }while(c.moveToNext());
        }
        c.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice, data);

        checklist.setAdapter(adapter);
        checklist.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        for(int i=0;i<status.size();i++){
            checklist.setItemChecked(i, status.get(i));
        }

        checklist.setOnItemClickListener((parent, view, pos, id) -> {
            boolean checked = checklist.isItemChecked(pos);
            db.updateWorkoutStatus(ids.get(pos), checked ? 1 : 0);
        });
    }
}