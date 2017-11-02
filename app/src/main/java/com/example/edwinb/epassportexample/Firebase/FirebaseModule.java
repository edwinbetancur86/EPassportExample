package com.example.edwinb.epassportexample.Firebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.edwinb.epassportexample.root.AppScope;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Edwin B on 10/19/2017.
 */

@Module
public class FirebaseModule {

    private final String TAG = "FirebaseModule";

    @AppScope
    @Provides
    FirebaseHelper provideFirebaseHelper(Context context)
    {
        return new FirebaseHelper(context);
    }


}
