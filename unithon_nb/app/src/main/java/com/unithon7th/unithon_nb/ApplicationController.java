package com.unithon7th.unithon_nb;

import android.app.Application;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationController  extends Application{
    private NetworkService networkService;
    @Override
    public void onCreate() {
        super.onCreate();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://nb.ljh.app/")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        networkService = retrofit.create(NetworkService.class);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public NetworkService getNetworkService(){
        return networkService;
    }
}
