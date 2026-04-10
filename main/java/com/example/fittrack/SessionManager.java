package com.example.fittrack;

import android.content.Context;
import android.content.SharedPreferences;

// April 8 - placeholder ng session

public class SessionManager {

    private static final String PREF_NAME = "FitTrackSession";
    private static final String KEY_LOGIN = "isLoggedIn";
    private static final String KEY_USER = "username";

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public SessionManager(Context context){
        sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void login(String username){
        editor.putBoolean(KEY_LOGIN, true);
        editor.putString(KEY_USER, username);
        editor.apply();
    }

    public boolean isLoggedIn(){
        return sp.getBoolean(KEY_LOGIN, false);
    }

    public String getUsername(){
        return sp.getString(KEY_USER, "");
    }

    public void logout(){
        editor.clear();
        editor.apply();
    }
}