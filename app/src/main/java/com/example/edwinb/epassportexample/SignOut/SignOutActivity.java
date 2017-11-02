package com.example.edwinb.epassportexample.SignOut;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.edwinb.epassportexample.R;
import com.example.edwinb.epassportexample.SQLiteOpenHelper.SQLiteHelper;
import com.example.edwinb.epassportexample.login.LoginActivity;
import com.example.edwinb.epassportexample.root.App;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignOutActivity extends AppCompatActivity {

    private final String SIGNEDINPREF = "SignedInPref";
    private final String TAG = "SignOutActivity";
    @BindView(R.id.signOutButton)
    Button signOutButton;
    @Inject
    SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_out);

        ((App) getApplication()).getComponent().inject(this);

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ButterKnife.bind(this);

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedpreferences = getSharedPreferences(SIGNEDINPREF, Context.MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = sharedpreferences.edit();
                prefsEditor.putBoolean("SignedInPref", false);
                prefsEditor.apply();

                SharedPreferences sharedPreferences = getSharedPreferences(SIGNEDINPREF, Context.MODE_PRIVATE);
                boolean signedInKnowledgement = sharedPreferences.getBoolean("SignedInPref", false);

                Log.d(TAG, "signed in acknowledgement is: " + signedInKnowledgement);

                Intent intent = new Intent(SignOutActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                sqLiteHelper.deleteAllData();
                startActivity(intent);
            }
        });
    }
}
