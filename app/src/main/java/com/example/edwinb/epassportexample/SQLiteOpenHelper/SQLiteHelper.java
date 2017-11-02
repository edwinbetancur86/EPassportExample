package com.example.edwinb.epassportexample.SQLiteOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Edwin B on 10/23/2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper{

    private final String TAG = "SQLiteHelper";
    private Context context;

    private static final int DB_VERSION = 1;
    public static final String DB_NAME = "users.db";
    public static final String TABLE_NAME = "user_info_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "EMAIL";
    public static final String COL_3 = "DISPLAYNAME";
    public static final String COL_4 = "PHOTOURL";

    private static final String SQL_CREATE_USERS =
            "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, EMAIL TEXT, DISPLAYNAME TEXT, PHOTOURL TEXT)";

    private static final String SQL_DELETE_USERS =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    public SQLiteHelper (Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        addUsersTable(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        deleteUsersTable(sqLiteDatabase);
        onCreate(sqLiteDatabase);
    }

    private void addUsersTable(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USERS);
    }

    private void deleteUsersTable(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_USERS);
    }

    public boolean insertData(String email, String displayname, String photourl)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, email);
        contentValues.put(COL_3, displayname);
        contentValues.put(COL_4, photourl);
        Long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public void deleteAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }


}
