package com.unithon7th.unithon_nb;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface NetworkService {
    @PUT("user/")
    Call<Map<String, Object>> signUp(@Body Map<String,Object> request);

    @POST("user/")
    Call<Map<String, Object>> signIn(@Body Map<String, Object> request);

    @PUT("assets/")
    Call<Map<String, Object>> reqistAsset(@Body Map<String, Object> request);

    @GET("assets/reg/list/")
    Call<Map<String, Object>> getAssetList(@Body Map<String, Object> request);
}