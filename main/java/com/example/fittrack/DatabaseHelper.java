package com.example.fittrack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

// April 8 - db para sa login credentials and etc..

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "FitTrackDB";
    private static final int DB_VERSION = 4;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE users (" +
                "id TEXT PRIMARY KEY, " +
                "username TEXT UNIQUE, " +
                "password TEXT, " +
                "age TEXT, height TEXT, weight TEXT, goal TEXT, activity TEXT)");

        db.execSQL("CREATE TABLE workouts (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, muscle TEXT, sets TEXT, reps TEXT, " +
                "completed INTEGER DEFAULT 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS workouts");
        onCreate(db);
    }

    // USER METHODS
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?",
                new String[]{username, password});
        boolean exists = c.getCount() > 0;
        c.close();
        return exists;
    }

    public boolean registerUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put("id", generateUserId());
        v.put("username", username);
        v.put("password", password);
        return db.insert("users", null, v) != -1;
    }

    public String generateUserId() {
        SQLiteDatabase db = this.getReadableDatabase();
        String year = new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date());

        Cursor c = db.rawQuery("SELECT id FROM users WHERE id LIKE '" + year + "%' ORDER BY id DESC LIMIT 1", null);
        if (c.moveToFirst()) {
            String last = c.getString(0);
            int num = Integer.parseInt(last.substring(4)) + 1;
            c.close();
            return year + String.format("%04d", num);
        } else {
            c.close();
            return year + "0001";
        }
    }

    // PROFILE
    public void saveProfile(String username, String age, String height, String weight, String goal, String activity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put("age", age);
        v.put("height", height);
        v.put("weight", weight);
        v.put("goal", goal);
        v.put("activity", activity);
        db.update("users", v, "username=?", new String[]{username});
    }

    public Cursor getProfile(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM users WHERE username=?", new String[]{username});
    }

    // WORKOUTS
    public void addWorkout(String name, String muscle, String sets, String reps) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put("name", name);
        v.put("muscle", muscle);
        v.put("sets", sets);
        v.put("reps", reps);
        v.put("completed", 0);
        db.insert("workouts", null, v);
    }

    public Cursor getWorkouts() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM workouts ORDER BY id DESC", null);
    }

    public void updateWorkoutStatus(int id, int status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put("completed", status);
        db.update("workouts", v, "id=?", new String[]{String.valueOf(id)});
    }

    public void deleteWorkout(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("workouts", "id=?", new String[]{String.valueOf(id)});
    }
}