package com.example.edwinb.epassportexample.login;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.edwinb.epassportexample.Firebase.FirebaseHelper;
import com.example.edwinb.epassportexample.MainMenu.MainMenuActivity;
import com.example.edwinb.epassportexample.R;
import com.example.edwinb.epassportexample.SQLiteOpenHelper.SQLModule;
import com.example.edwinb.epassportexample.SQLiteOpenHelper.SQLiteHelper;
import com.example.edwinb.epassportexample.retrofit.SampleAPI;
import com.example.edwinb.epassportexample.retrofit.pojos.User;
import com.example.edwinb.epassportexample.root.App;
import com.example.edwinb.epassportexample.root.ApplicationComponent;
import com.example.edwinb.epassportexample.root.LoginMVP.LoginActivityMVP;
import com.example.edwinb.epassportexample.utils.PrefUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements LoginActivityMVP.View{

    private final String TAG = "LoginActivity";
    private final String SIGNEDINPREF = "SignedInPref";

    @Inject
    LoginActivityMVP.Presenter presenter;

    @Inject
    FirebaseHelper firebaseHelper;

    @Inject
    SQLiteHelper sqLiteHelper;

    /*@Inject
    SampleAPI sampleAPI;*/

    private FirebaseAuth mAuth;

    @BindView(R.id.usernameText)
    EditText userN;

    @BindView(R.id.passwordText)
    EditText pass;

    @BindView(R.id.loginButton)
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ((App) getApplication()).getComponent().inject(this);
        ButterKnife.bind(this);
        firebaseHelper = new FirebaseHelper(this);
        mAuth = FirebaseAuth.getInstance();


    }

    @OnClick(R.id.loginButton)
    public void loginUser()
    {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        login.startAnimation(animation);
        presenter.loginButtonClicked();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        mAuth.addAuthStateListener(firebaseHelper.getAuthStateListener());
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
        if (firebaseHelper.getAuthStateListener() != null) {
            mAuth.removeAuthStateListener(firebaseHelper.getAuthStateListener());
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        //presenter.getCurrentUser();

        SharedPreferences sharedPreferences = getSharedPreferences(SIGNEDINPREF, Context.MODE_PRIVATE);
        boolean signedInKnowledgement = sharedPreferences.getBoolean("SignedInPref", false);

        Log.d(TAG, "signed in acknowledgement is: " + signedInKnowledgement);

        if (signedInKnowledgement)
        {
            startActivity(new Intent(this, MainMenuActivity.class));
        }
    }

    @Override
    public String getUsername() {
        return userN.getText().toString();
    }

    @Override
    public String getPassword() {
        return pass.getText().toString();
    }

    @Override
    public void showUserNotAvailable() {
        Toast.makeText(this, "Error the user is not available", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInputError() {
        Toast.makeText(this, "Username or pass cannot be empty", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserSavedMessage() {
        //Toast.makeText(this, "User saved!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUsername(String username) {
        userN.setText(username);
    }

    @Override
    public void setPassword(String password) {
        pass.setText(password);
    }


    @Subscribe(sticky = false, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(User user) {



         Log.d(TAG, "Inside EventBus: "
                 + user.getUsername() + "\n"
                 + user.getPassword());

        signInWithEmailAndPass(user.getUsername(), user.getPassword());


    }




    /*private void createEmailAndPassAccount(String e, String p) {

        firebaseHelper.createAccount(e, p);

    }*/

    private void signInWithEmailAndPass(String u, String p)
    {
        firebaseHelper.signIn(u, p);
    }


}
