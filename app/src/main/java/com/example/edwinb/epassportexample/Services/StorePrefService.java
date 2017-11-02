package com.example.edwinb.epassportexample.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.edwinb.epassportexample.retrofit.pojos.PreSheet;
import com.google.gson.Gson;

/**
 * Created by Edwin B on 11/2/2017.
 */

public class StorePrefService extends Service {

    private final String TAG = "StorePrefService";
    private final String SP_ALERT_SAVE_DATA = "AlertData";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "I'm in the store shared pref service");
        SharedPreferences sharedpreferences = getSharedPreferences(SP_ALERT_SAVE_DATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedpreferences.edit();
        Log.d(TAG, "PreSheet toString: " + intent.getStringExtra("PreSheetTitle") + "\n" + intent.getStringExtra("PreSheetStartDate"));
        prefsEditor.putString("PreSheetTitle", intent.getStringExtra("PreSheetTitle"));
        prefsEditor.putString("PreSheetStartDate", intent.getStringExtra("PreSheetStartDate"));
        prefsEditor.apply();

        return START_NOT_STICKY;
    }
}
