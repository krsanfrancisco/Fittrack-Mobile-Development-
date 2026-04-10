package com.example.fittrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// April 7 - login
// April 8 - updated, may db + etc

public class LoginActivity extends AppCompatActivity {

    EditText usernameInput, passwordInput;
    DatabaseHelper db;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        session = new SessionManager(this);

        if(session.isLoggedIn()){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        setContentView(R.layout.activity_login);

        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);

        db = new DatabaseHelper(this);
    }

    public void loginUser(View view) {
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        if(db.checkUser(username, password)){
            session.login(username); // ✅ THIS IS THE FIX
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, ProfileActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
        }
    }

    public void goRegister(View view){
        startActivity(new Intent(this, RegisterActivity.class));
    }
}