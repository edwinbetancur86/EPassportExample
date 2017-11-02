package com.example.edwinb.epassportexample.root.LoginMVP;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Edwin B on 10/18/2017.
 */

@Module
public class LoginModule {


    @Provides
    public LoginActivityMVP.Presenter provideLoginActivityPresenter(LoginActivityMVP.Model model)
    {
        return new LoginActivityPresenter(model);
    }


    @Provides
    public LoginActivityMVP.Model provideLoginActivityModel(LoginRepository repository)
    {
        return new LoginModel(repository);
    }

    @Provides
    public LoginRepository provideLoginRepository()
    {
        return new DatabaseRepository();
    }



}
