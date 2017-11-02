package com.example.edwinb.epassportexample.root.LoginMVP;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.edwinb.epassportexample.Firebase.FirebaseHelper;
import com.example.edwinb.epassportexample.R;
import com.example.edwinb.epassportexample.login.LoginActivity;
import com.example.edwinb.epassportexample.retrofit.pojos.User;
import com.example.edwinb.epassportexample.root.App;
import com.example.edwinb.epassportexample.utils.PrefUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

/**
 * Created by Edwin B on 10/18/2017.
 */

public class DatabaseRepository implements LoginRepository {


    private final String TAG = "DataRepository";

    private User user;

    public User getUser() {

        if (user == null)
        {
            User user = new User("fox1", "aliens");
            this.user = user;
            return this.user;
        }
        else
        {
            return user;
        }

    }

    @Override
    public void saveUser(User user) {

        if (user == null)
        {
            user = getUser();
        }

        this.user = user;

        EventBus.getDefault().post(user);

    }




}
