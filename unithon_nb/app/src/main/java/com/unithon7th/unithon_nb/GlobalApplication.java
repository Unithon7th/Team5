package com.unithon7th.unithon_nb;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.provider.SyncStateContract;
import android.util.Log;
import android.widget.Toast;

import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthType;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;
import com.unithon7th.unithon_nb.login.LoginActivity;

import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GlobalApplication extends Application {
    private static volatile GlobalApplication instance = null;
    private static OAuthLogin NAuthLogin;
    private Context context;
    private NetworkService networkService;

    //////////////////////////카카오톡 로그인/////////////////////////////////////////
    public static GlobalApplication getGlobalApplicationContext(){
        if(instance==null)
            throw new IllegalStateException("error");
        return instance;
    }

    private static class KakaoSDKAdapter extends KakaoAdapter {
        @Override
        public ISessionConfig getSessionConfig() {
            return new ISessionConfig() {
                @Override
                public AuthType[] getAuthTypes() {
                    return new AuthType[] {AuthType.KAKAO_LOGIN_ALL};
                }

                @Override
                public boolean isUsingWebviewTimer() {
                    return false;
                }

                @Override
                public boolean isSecureMode() {
                    return false;
                }

                @Override
                public ApprovalType getApprovalType() {
                    return ApprovalType.INDIVIDUAL;
                }

                @Override
                public boolean isSaveFormData() {
                    return true;
                }
            };
        }

        @Override
        public IApplicationConfig getApplicationConfig() {
            return new IApplicationConfig() {
                @Override
                public Context getApplicationContext() {
                    return GlobalApplication.getGlobalApplicationContext();
                }
            };
        }
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        KakaoSDK.init(new KakaoSDKAdapter());

    }

    public void setNetworkService(OkHttpClient client){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://nb.ljh.app/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        networkService = retrofit.create(NetworkService.class);
    }

    public NetworkService getNetworkService(){
        return networkService;
    }
}