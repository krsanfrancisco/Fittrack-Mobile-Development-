package com.example.fittrack;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

// April 7 - register
// April 8 - may db

public class RegisterActivity extends AppCompatActivity {

    EditText usernameInput, passwordInput;
    DatabaseHelper db;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);

        db = new DatabaseHelper(this);
        session = new SessionManager(this);
    }

    public void registerUser(View view){
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if(db.registerUser(username, password)){
            session.login(username); // ✅ FIXED HERE
            Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this, ProfileActivity.class));
            finish();

        } else {
            Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
        }
    }
}