package com.example.edwinb.epassportexample.root.LoginMVP;

import android.content.Context;

import com.example.edwinb.epassportexample.retrofit.pojos.User;

/**
 * Created by Edwin B on 10/18/2017.
 */

public interface LoginActivityMVP {

    interface View
    {
        String getUsername();
        String getPassword();

        void showUserNotAvailable();
        void showInputError();
        void showUserSavedMessage();

        void setUsername(String username);
        void setPassword(String password);
    }

    interface Presenter
    {
        void setView(LoginActivityMVP.View view);

        void loginButtonClicked();

        void getCurrentUser();

        void saveUser();

    }

    interface Model
    {
        void createUser(String username, String password);

        User getUser();
    }

}
