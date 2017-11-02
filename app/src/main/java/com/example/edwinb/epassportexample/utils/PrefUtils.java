package com.example.edwinb.epassportexample.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class PrefUtils extends AppCompatActivity {

    private static final String PREF_FIREBASE_MAUTH = "pref_firebase_mAuth";

    /* PREF_FIREBASE_MAUTH */
    public static void setPrefFirebaseMauth(Context c, FirebaseAuth mAuth) {

        Gson gson = new Gson();
        String json = gson.toJson(mAuth);

        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(c);
        sp.edit().putString(PREF_FIREBASE_MAUTH, json).apply();
    }
    public static String getPrefFirebaseMauth(Context c) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(c);
        return sp.getString(PREF_FIREBASE_MAUTH, null);

    }

}
