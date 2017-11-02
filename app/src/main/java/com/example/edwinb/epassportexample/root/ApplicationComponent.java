package com.example.edwinb.epassportexample.root;

import android.content.Context;

import com.example.edwinb.epassportexample.Firebase.FirebaseHelper;
import com.example.edwinb.epassportexample.Firebase.FirebaseModule;
import com.example.edwinb.epassportexample.Fragments.AlertFragment;
import com.example.edwinb.epassportexample.NewPrescription.NewPrescriptionActivity;
import com.example.edwinb.epassportexample.SQLiteOpenHelper.SQLModule;
import com.example.edwinb.epassportexample.SQLiteOpenHelper.SQLiteHelper;
import com.example.edwinb.epassportexample.SignOut.SignOutActivity;
import com.example.edwinb.epassportexample.login.LoginActivity;
import com.example.edwinb.epassportexample.retrofit.ApiModule;
import com.example.edwinb.epassportexample.root.LoginMVP.DatabaseRepository;
import com.example.edwinb.epassportexample.root.LoginMVP.LoginModule;
import com.example.edwinb.epassportexample.root.NewPrecriptionMVP.DatabaseRepository2;
import com.example.edwinb.epassportexample.root.NewPrecriptionMVP.NewPrescriptionModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Edwin B on 10/18/2017.
 */
// Doing it as a singleton is best practice since
    // You are instansitating ans using a module
    // component once
@AppScope
@Component(modules = {ApplicationModule.class, LoginModule.class,
        ApiModule.class, FirebaseModule.class, SQLModule.class,
        NewPrescriptionModule.class})

public interface ApplicationComponent {

    FirebaseHelper provideFirebaseHelper();

    SQLiteHelper provideSQLiteHelper();

    void inject (NewPrescriptionActivity target);

    void inject (LoginActivity target);

    void inject (AlertFragment target);

    void inject (SignOutActivity target);


}
