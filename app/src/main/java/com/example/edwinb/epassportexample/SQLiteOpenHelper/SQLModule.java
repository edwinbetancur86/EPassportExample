package com.example.edwinb.epassportexample.SQLiteOpenHelper;

import android.content.Context;

import com.example.edwinb.epassportexample.root.AppScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Edwin B on 10/23/2017.
 */

@Module
public class SQLModule {

    private final String TAG = "SQLiteModule";

    @AppScope
    @Provides
    SQLiteHelper provideSQLHelper(Context context)
    {
        return new SQLiteHelper(context);
    }

}
