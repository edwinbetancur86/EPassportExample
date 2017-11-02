package com.example.edwinb.epassportexample.root.LoginMVP;

import android.content.Context;

import com.example.edwinb.epassportexample.retrofit.pojos.User;

/**
 * Created by Edwin B on 10/18/2017.
 */

public class LoginModel implements LoginActivityMVP.Model{

    private LoginRepository repository;

    public LoginModel(LoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createUser(String username, String password) {
        repository.saveUser(new User(username, password));
    }

    @Override
    public User getUser() {
        return repository.getUser();
    }
}
