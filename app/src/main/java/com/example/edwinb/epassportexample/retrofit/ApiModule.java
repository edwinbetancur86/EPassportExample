package com.example.edwinb.epassportexample.retrofit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Edwin B on 10/19/2017.
 */

@Module
public class ApiModule {

    public final String BASE_URL = "https://edwinbfirebaseui.firebaseio.com";



    @Provides
    public Retrofit provideRetrofit(String baseURL)
    {
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    @Provides
    public SampleAPI provideApiService()
    {
        return provideRetrofit(BASE_URL).create(SampleAPI.class);
    }

}
