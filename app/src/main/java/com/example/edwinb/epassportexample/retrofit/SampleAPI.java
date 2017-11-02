package com.example.edwinb.epassportexample.retrofit;

import android.support.annotation.Nullable;

import com.example.edwinb.epassportexample.retrofit.pojos.PreSheet;
import com.example.edwinb.epassportexample.retrofit.pojos.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Edwin B on 10/19/2017.
 */

public interface SampleAPI {

    // "messages" is the object in RTB that you want write under.
    // "new" is the name of the object to that you want to post
    // under "messages"
    @POST("/upload/{new}.json")
    Observable<PreSheet> setPreSheet(@Path("new") String s1, @Body PreSheet preSheet);


    @GET("/upload/PreSheets.json")
    Call<PreSheet> getPreSheet();

    @PUT("/upload/{new}.json")
    Call<PreSheet> setPreSheetWithoutRandomness(@Path("new") String s1, @Body PreSheet preSheet);

    // I believe this deletes the entire RTDB
    @DELETE("{deletePlaceHolder}.json")
    Call<PreSheet> deletePreSheet(@Path("deletePlaceHolder") String id);


    //-------------------------
    // 2nd version below
    //-------------------------
  /*  @POST("{new}.json")
    Call<PreSheet> setPreSheet(@Path("new") String s1, @Body PreSheet preSheet);


    @GET("/PreSheets.json")
    Call<PreSheet> getPreSheet();

    @PUT("{new}.json")
    Call<PreSheet> setPreSheetWithoutRandomness(@Path("new") String s1, @Body PreSheet preSheet);

    // I believe this deletes the entire RTDB
    @DELETE("{deletePlaceHolder}.json")
    Call<PreSheet> deletePreSheet(@Path("deletePlaceHolder") String id);*/

}
