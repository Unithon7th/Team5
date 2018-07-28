package com.unithon7th.unithon_nb;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {
    Context context;

    public AddCookiesInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        SharedPreferences sharedPreferences = context.getSharedPreferences("cookie",Context.MODE_PRIVATE);
        String cookie = sharedPreferences.getString("Cookie",null);
        if(cookie !=null){
            builder.addHeader("Cookie",cookie);
            builder.removeHeader("User-Agent").addHeader("User-Agent", "Android");
        }
        return chain.proceed(builder.build());
    }
}
