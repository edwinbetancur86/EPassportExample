package com.example.edwinb.epassportexample.root;

import android.app.Application;
import android.content.Context;

import com.example.edwinb.epassportexample.Firebase.FirebaseModule;
import com.example.edwinb.epassportexample.SQLiteOpenHelper.SQLModule;
import com.example.edwinb.epassportexample.retrofit.ApiModule;
import com.example.edwinb.epassportexample.root.LoginMVP.LoginModule;
import com.example.edwinb.epassportexample.root.NewPrecriptionMVP.NewPrescriptionModule;


/**
 * Created by Edwin B on 10/18/2017.
 */

public class App extends Application {

    private ApplicationComponent component;


    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .loginModule(new LoginModule())
                .apiModule(new ApiModule())
                .firebaseModule(new FirebaseModule())
                .sQLModule(new SQLModule())
                .newPrescriptionModule(new NewPrescriptionModule())
                .build();

    }


    public ApplicationComponent getComponent()
    {
        return component;
    }

}
