package com.example.edwinb.epassportexample.root.LoginMVP;

import android.content.Context;

import com.example.edwinb.epassportexample.retrofit.pojos.User;

/**
 * Created by Edwin B on 10/18/2017.
 */

public interface LoginRepository {

    User getUser();

    void saveUser(User user);

}
