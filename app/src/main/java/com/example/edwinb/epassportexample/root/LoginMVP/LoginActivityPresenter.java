package com.example.edwinb.epassportexample.root.LoginMVP;

import android.content.Context;
import android.support.annotation.Nullable;

import com.example.edwinb.epassportexample.retrofit.pojos.User;

/**
 * Created by Edwin B on 10/18/2017.
 */

public class LoginActivityPresenter implements LoginActivityMVP.Presenter{

    @Nullable
    private LoginActivityMVP.View view;
    private LoginActivityMVP.Model model;


    public LoginActivityPresenter(LoginActivityMVP.Model model) {
        this.model = model;

    }


    public void setView(LoginActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void loginButtonClicked() {

        if (view != null)
        {
            if (view.getUsername().trim().equals("") || view.getPassword().trim().equals(""))
            {
                view.showInputError();
            }
            else
            {
                model.createUser(view.getUsername(), view.getPassword());
                view.showUserSavedMessage();
            }
        }


    }

    @Override
    public void getCurrentUser() {

        User user = model.getUser();

        if (user != null)
        {
            if (view != null)
            {
                view.setUsername(user.getUsername());
                view.setPassword(user.getPassword());
            }
        }
        else
        {
            if (view != null)
            {
                view.showUserNotAvailable();
            }

        }
    }

    @Override
    public void saveUser() {

        if (view != null)
        {
            if (view.getUsername().trim().equals("") || view.getPassword().trim().equals(""))
            {
                view.showInputError();
            }
        }
        else
        {
            model.createUser(view.getUsername(), view.getPassword());
        }

    }
}
