package com.example.edwinb.epassportexample.root.NewPrecriptionMVP;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.LocaleDisplayNames;
import android.util.Log;
import android.widget.Toast;

import com.example.edwinb.epassportexample.Firebase.FirebaseHelper;
import com.example.edwinb.epassportexample.NewPrescription.NewPrescriptionActivity;
import com.example.edwinb.epassportexample.retrofit.ApiModule;
import com.example.edwinb.epassportexample.retrofit.SampleAPI;
import com.example.edwinb.epassportexample.retrofit.pojos.PreSheet;
import com.example.edwinb.epassportexample.retrofit.pojos.User;
import com.example.edwinb.epassportexample.root.App;
import com.example.edwinb.epassportexample.root.LoginMVP.DatabaseRepository;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Edwin B on 10/26/2017.
 */

public class DatabaseRepository2 implements NewPrescriptionRepository {

    private final String TAG = "DataRepository2";

    //private PreSheet preSheet;


    @Override
    public PreSheet getPreSheet() {

        // There is no reason to have this logic here
        // but copy this logic over to the alerts

        /*if (preSheet == null)
        {
            PreSheet preSheet = new PreSheet("fox1", "aliens", "1", "2", "2");
            this.preSheet = preSheet;
            return this.preSheet;
        }
        else
        {
            return preSheet;
        }*/
        return null;
    }

    @Override
    public void postPresheet(PreSheet preSheet) {

        if (preSheet == null)
        {
            Log.d(TAG, "the PreSheet object was null");
            //preSheet = getPreSheet();
        }
        else
        {
            EventBus.getDefault().postSticky(preSheet);
            Log.d(TAG, "Event sent from DBRepo2");
        }

        //this.preSheet = preSheet;



    }
}
