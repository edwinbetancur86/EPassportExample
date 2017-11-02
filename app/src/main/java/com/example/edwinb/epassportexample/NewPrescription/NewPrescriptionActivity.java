package com.example.edwinb.epassportexample.NewPrescription;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.edwinb.epassportexample.MainMenu.MainMenuActivity;
import com.example.edwinb.epassportexample.R;
import com.example.edwinb.epassportexample.Recievers.AlarmReceiver;
import com.example.edwinb.epassportexample.SQLiteOpenHelper.SQLiteHelper;
import com.example.edwinb.epassportexample.Services.StorePrefService;
import com.example.edwinb.epassportexample.retrofit.SampleAPI;
import com.example.edwinb.epassportexample.retrofit.pojos.PreSheet;
import com.example.edwinb.epassportexample.root.App;
import com.example.edwinb.epassportexample.root.NewPrecriptionMVP.NewPrescriptionMVP;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPrescriptionActivity extends AppCompatActivity implements NewPrescriptionMVP.View, DatePickerDialog.OnDateSetListener {

    private final String TAG = "NewPrescriptionActivity";
    private final int DIALOG_ID = 0;
    private final String RT_PRESHEET_LABEL = "PreSheets";
    private final String SP_ALERT_SAVE_DATA = "AlertData";
    @BindView(R.id.preTitleEdit)
    EditText preTitleEdit;
    @BindView(R.id.preDosageEdit)
    EditText preDosageEdit;
    @BindView(R.id.preStartDay)
    EditText preStartEdit;
    @BindView(R.id.preTimeEdit)
    EditText preTimeEdit;
    @BindView(R.id.imageButtonAddTime)
    ImageButton addTime;
    @BindView(R.id.imageButtonCancel)
    ImageButton imageButtonCancel;

    private boolean sharePrefPass = false;

    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    Intent alarmReceiverIntent;
    Calendar calendar;

    int hour;
    int minute;

    @Inject
    NewPrescriptionMVP.Presenter presenter;

    @Inject
    SQLiteHelper sqLiteHelper;

    @Inject
    SampleAPI sampleAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_prescription);

        ((App) getApplication()).getComponent().inject(this);

        ButterKnife.bind(this);

        if (getSupportActionBar() != null)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmReceiverIntent = new Intent(this, AlarmReceiver.class);

        addTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
                view.startAnimation(animation);
                presenter.addTimeButtonClicked();
            }
        });

        imageButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
                imageButtonCancel.startAnimation(animation);

                if (pendingIntent != null)
                {
                    alarmManager.cancel(pendingIntent);
                    alarmReceiverIntent.putExtra("extra", false);
                    sendBroadcast(alarmReceiverIntent);
                }

                presenter.cancelTimeButtonClicked();

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public String getUserEmail() {


        Cursor res = sqLiteHelper.getAllData();

        if (res.getCount() == 0) {
            Toast.makeText(this, "Error, no email value in database", Toast.LENGTH_LONG).show();
            return null;
        } else {
            ArrayList<String> cursorResList = new ArrayList<>();

            while (res.moveToNext()) {
                cursorResList.add(res.getString(1));
            }
            Log.d(TAG, String.valueOf(cursorResList.get(0)));
            Toast.makeText(this, String.valueOf(cursorResList.get(0)), Toast.LENGTH_LONG).show();

            return cursorResList.get(0);

        }


    }

    @Override
    public String getPreTitle() {
        return preTitleEdit.getText().toString();
    }

    @Override
    public String getPreDosage() {
        return preDosageEdit.getText().toString();
    }

    @Override
    public String getPreStartDay() {
        return preStartEdit.getText().toString();
    }

    @Override
    public String getPreTime() {
        return preTimeEdit.getText().toString();
    }

    @Override
    public void showUserInputError() {

        Toast.makeText(this, "All entries must be filled", Toast.LENGTH_LONG).show();
    }


    @Override
    public void showUserAddPrescriptionSuccessMessage() {
        Toast.makeText(this, "Prescription was added in successfully", Toast.LENGTH_LONG).show();

        alarmReceiverIntent.putExtra("extra", true);

        pendingIntent = PendingIntent
                .getBroadcast(NewPrescriptionActivity.this,
                        0, alarmReceiverIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Set the alarm manager
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

    }

    @Override
    public void setPreTitle(String preTitle) {
        preTitleEdit.setText(preTitle);
    }

    @Override
    public void setPreDosage(String preDosage) {
        preDosageEdit.setText(preDosage);
    }

    @Override
    public void setPreStartDay(String preStartDay) {
        preStartEdit.setText(preStartDay);
    }

    @Override
    public void setPreTime(String preTime) {
        preTimeEdit.setText(preTime);
    }

    @OnClick(R.id.addStartDayButton)
    public void datePicker(View view) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "date");
    }

    private void setDate(final Calendar calendar) {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        preStartEdit.setText(dateFormat.format(calendar.getTime()));
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }


    public static class DatePickerFragment extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(),
                    (DatePickerDialog.OnDateSetListener)
                            getActivity(), year, month, day);

        }
    }


    protected TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {

            calendar = Calendar.getInstance();
            hour = i;
            minute = i1;

            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);

            String hour_string = String.valueOf(hour);
            String minute_string = String.valueOf(minute);

            if (hour > 12) {
                hour_string = String.valueOf(hour - 12);
            }

            if (minute < 10) {
                minute_string = "0" + String.valueOf(minute);
            }

            preTimeEdit.setText(hour_string + ":" + minute_string);


        }
    };

    protected Dialog onCreateDialog(int id) {

        if (id == DIALOG_ID)
            return new TimePickerDialog(NewPrescriptionActivity.this, timePickerListener, hour, minute, false);
        return null;
    }

    @OnClick(R.id.timeButton)
    public void timePicker() {
        showDialog(0);


    }



    public void sendPreSheetToRealBD(String s, final PreSheet preSheet)
    {

        Observable<PreSheet> observableCall = sampleAPI.setPreSheet(s, preSheet);

        observableCall.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PreSheet>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull PreSheet preSheet) {

                        Toast.makeText(NewPrescriptionActivity.this,
                                "PreSheet Successfully sent",
                                Toast.LENGTH_LONG).show();
                        Log.d(TAG, "sharedPrefPass(in subscriber) is:" + sharePrefPass);
                        sharePrefPass = true;

                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                        Toast.makeText(NewPrescriptionActivity.this,
                                "failed to send the PreSheet",
                                Toast.LENGTH_LONG).show();
                        e.printStackTrace();

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Log.d(TAG, "sharedPrefPass is:" + sharePrefPass);
        if (sharePrefPass)
        {
            SharedPreferences sharedpreferences = getSharedPreferences(SP_ALERT_SAVE_DATA, Context.MODE_PRIVATE);
            SharedPreferences.Editor prefsEditor = sharedpreferences.edit();
            Gson gson = new Gson();
            Log.d(TAG, "PreSheet toString: " + preSheet.getPreStartDay() + "\n" + preSheet.getPreTitle());
            String json = gson.toJson(preSheet);
            prefsEditor.putString("PreSheetData", json);
            prefsEditor.apply();
            sharePrefPass = false;
        }


        /*call.enqueue(new Callback<PreSheet>() {
            @Override
            public void onResponse(Call<PreSheet> call, Response<PreSheet> response) {
                Toast.makeText(NewPrescriptionActivity.this,
                        "PreSheet Successfully sent",
                        Toast.LENGTH_LONG).show();

                SharedPreferences sharedpreferences = getSharedPreferences(SP_ALERT_SAVE_DATA, Context.MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = sharedpreferences.edit();
                Gson gson = new Gson();
                Log.d(TAG, "Presheet toString: " + preSheet.getPreStartDay() + "\n" + preSheet.getPreTitle());
                String json = gson.toJson(preSheet);
                prefsEditor.putString("PreSheetData", json);
                prefsEditor.apply();

            }

            @Override
            public void onFailure(Call<PreSheet> call, Throwable t) {
                Toast.makeText(NewPrescriptionActivity.this,
                        "failed to send the PreSheet",
                        Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });*/


    }


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PreSheet preSheet) {

        Log.d(TAG, "Inside EventBus: "
                + preSheet.getPreTitle() + "\n"
                + preSheet.getPreDosage() + "\n"
                + preSheet.getPreStartDay() + "\n"
                + preSheet.getEmail() + "\n"
                + preSheet.getPreTime());

        Toast.makeText(this, "Inside EventBus!", Toast.LENGTH_LONG).show();

        sendPreSheetToRealBD(RT_PRESHEET_LABEL, preSheet);


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
