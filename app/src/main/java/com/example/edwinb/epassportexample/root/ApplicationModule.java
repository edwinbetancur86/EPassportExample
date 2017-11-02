package com.example.edwinb.epassportexample.root;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Edwin B on 10/18/2017.
 */
@Module
public class ApplicationModule {

    private Application application;


    public ApplicationModule(Application application) {
        this.application = application;
    }

    @AppScope
    @Provides
    public Context provideContext()
    {
        return  application;
    }
}
