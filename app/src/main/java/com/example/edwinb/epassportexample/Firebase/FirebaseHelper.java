package com.example.edwinb.epassportexample.Firebase;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.edwinb.epassportexample.MainMenu.MainMenuActivity;
import com.example.edwinb.epassportexample.R;
import com.example.edwinb.epassportexample.SQLiteOpenHelper.SQLiteHelper;
import com.example.edwinb.epassportexample.root.App;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

import javax.inject.Inject;

/**
 * Created by Edwin B on 10/20/2017.
 */

public class FirebaseHelper {

    private final String TAG = "FirebaseHelper";
    private final String SIGNEDINPREF = "SignedInPref";
    private Context context;

    @Inject
    SQLiteHelper sqLiteHelper;

    public FirebaseHelper(Context context)
    {
        this.context = context;
    }

    public FirebaseAuth getFirebaseAuthInstance()
    {
        return FirebaseAuth.getInstance();
    }

    public FirebaseAuth.AuthStateListener getAuthStateListener() {
        return new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getEmail());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }

            }
        };
    }

    public void createAccount(String e, String p)
    {
        getFirebaseAuthInstance().createUserWithEmailAndPassword(e, p)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(context, R.string.mAuth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(context, R.string.mAuth_successful,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void signIn(String e, String p)
    {

        getFirebaseAuthInstance().signInWithEmailAndPassword(e, p)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(context, R.string.mAuth_failed,
                                    Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            Toast.makeText(context, R.string.mAuth_successful,
                                    Toast.LENGTH_SHORT).show();

                            sqLiteHelper = new SQLiteHelper(context);

                            boolean isInserted = sqLiteHelper.insertData(getFirebaseAuthInstance().getCurrentUser().getEmail(),
                                                    getFirebaseAuthInstance().getCurrentUser().getDisplayName(),
                                                    String.valueOf(getFirebaseAuthInstance().getCurrentUser().getPhotoUrl()));

                            if (isInserted)
                            {
                                Toast.makeText(context, "Data Inserted", Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(context, "Data not inserted", Toast.LENGTH_LONG).show();
                            }

                            SharedPreferences sharedpreferences = context.getSharedPreferences(SIGNEDINPREF, Context.MODE_PRIVATE);
                            SharedPreferences.Editor prefsEditor = sharedpreferences.edit();
                            prefsEditor.putBoolean("SignedInPref", true);
                            prefsEditor.apply();

                            SharedPreferences sharedPreferences = context.getSharedPreferences(SIGNEDINPREF, Context.MODE_PRIVATE);
                            boolean signedInKnowledgement = sharedPreferences.getBoolean("SignedInPref", false);

                            Log.d(TAG, "signed in acknowledgement is: " + signedInKnowledgement);

                            context.startActivity(new Intent(context, MainMenuActivity.class));

                            /*Intent intent = new Intent(context, MainMenuActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            context.startActivity(intent);*/


                        }
                    }
                });

    }

}
