package com.example.edwinb.epassportexample.Services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.edwinb.epassportexample.R;

/**
 * Created by Edwin B on 10/29/2017.
 */

public class RingtonePlayingService extends Service{

    private final String TAG = "RingtonePlayingService";

    MediaPlayer mediaPlayer;
    int startId;
    boolean isRunning;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG, "Received start id " + startId + ": " + intent);


        boolean state = intent.getExtras().getBoolean("extra");

        Log.d(TAG, "The ringtone state: extra is " + String.valueOf(state));

        switch (String.valueOf(state))
        {
            case "true":
                startId = 1;
                break;
            case "false":
                startId = 0;
                Log.d(TAG, String.valueOf(state));
                break;
            default:
                startId = 0;
                break;
        }

        if (!this.isRunning && startId == 1)
        {
            Log.d(TAG, "there is no music, and you want to start");
            mediaPlayer = MediaPlayer.create(this, R.raw.pills);
            mediaPlayer.start();

            this.isRunning = true;
            this.startId = 0;
        }
        else if (this.isRunning && startId == 0)
        {
            Log.d(TAG, "there is music, and you want to end");
            mediaPlayer.stop();
            mediaPlayer.reset();

            this.isRunning = false;
            this.startId = 0;

        }
        else if (!this.isRunning && startId == 0)
        {
            Log.d(TAG, "there is no music, and you want to end");

            this.isRunning = false;
            this.startId = 0;

        }
        else if (this.isRunning && startId == 1)
        {
            Log.d(TAG, "there is music, and you want to start");

            this.isRunning = true;
            this.startId = 0;
        }
        else
        {
            Log.d(TAG, "else, somehow you reached this");
        }



        return START_NOT_STICKY;
    }
}
