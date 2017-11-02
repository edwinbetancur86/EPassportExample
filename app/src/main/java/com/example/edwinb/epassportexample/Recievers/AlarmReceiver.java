package com.example.edwinb.epassportexample.Recievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.edwinb.epassportexample.Services.RingtonePlayingService;

/**
 * Created by Edwin B on 10/29/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {

    private final String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG, "We are in the AlarmReceiver!");

        Boolean getBoolean = intent.getExtras().getBoolean("extra");

        Log.d(TAG, String.valueOf(getBoolean));

        //create an intent to the ringtone service
        Intent serviceIntent = new Intent(context, RingtonePlayingService.class);

        serviceIntent.putExtra("extra", getBoolean);

        // start the ringtone service
        context.startService(serviceIntent);

    }
}
