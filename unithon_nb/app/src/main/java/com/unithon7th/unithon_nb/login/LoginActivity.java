package com.unithon7th.unithon_nb.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.unithon7th.unithon_nb.AddCookiesInterceptor;
import com.unithon7th.unithon_nb.GlobalApplication;
import com.unithon7th.unithon_nb.R;
import com.unithon7th.unithon_nb.ReceivedCookiesInterceptor;
import com.unithon7th.unithon_nb.home.HomeActivity;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity {
    private SessionCallback callback;
    private static OAuthLogin NAuthLogin;
    private Context context;
    private static String OAUTH_CLIENT_ID = "0ncZoX6sxe5LmZtZEB9R";
    private static String OAUTH_CLIENT_SECRET = "xXtCKxrawT";
    private static String OAUTH_CLIENT_NAME = "네이버 아이디로 로그인";
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setPaintFlags(btnLogin.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        try {
            NAuthLogin = OAuthLogin.getInstance();
            NAuthLogin.init(
                    LoginActivity.this
                    ,OAUTH_CLIENT_ID
                    , OAUTH_CLIENT_SECRET
                    , OAUTH_CLIENT_NAME
            );
        }catch (NullPointerException e) {
            Log.e("LA_Error : ",e.getMessage());
        }

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new AddCookiesInterceptor(getApplicationContext()))
                .addInterceptor(new ReceivedCookiesInterceptor(getApplicationContext())).build();


        ((GlobalApplication)getApplication()).setNetworkService(okHttpClient);
    }

//////////////////카카오톡 로그인////////////////////////
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }


    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            requestLogin("kakao");
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if (exception != null) {
                Logger.e(exception);
            }
        }
    }
    ///////////////////////////////////////////////////////////

    ///////////////////네이버 로그인/////////////////////////////////

    @SuppressLint("HandlerLeak")
    private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
        @Override
        public void run(boolean success) {
            if (success) {
                String accessToken = NAuthLogin.getAccessToken(context);
                String refreshToken = NAuthLogin.getRefreshToken(context);
                long expiresAt = NAuthLogin.getExpiresAt(context);
                String tokenType = NAuthLogin.getTokenType(context);
                Log.e("LA_accessToken",accessToken.toString());
                Log.e("LA_refreshToken",refreshToken.toString());
                requestLogin("naver");
            } else {
                String errorCode = NAuthLogin.getLastErrorCode(context).getCode();
                String errorDesc = NAuthLogin.getLastErrorDesc(context);
                Log.e("Handler Error : ",errorCode);
            }
        };
    };

    public void btnOnClick(View  v) throws Throwable {
        switch (v.getId()) {
            case R.id.btnNB2:
                NAuthLogin.startOauthLoginActivity(LoginActivity.this, mOAuthLoginHandler);
                break;
            case R.id.btnSignup:
                //간편 회원가입
                break;
            case R.id.btnLogin:
                //이미 회원인 사람들 로그인
                break;
        }
    }


    public void requestLogin(final String type){
        Map<String, Object> request = new HashMap<>();
        request.put("type", type);
        request.put("email", "unithon9th@gmail.com");
        Call<Map<String,Object>> signIn = ((GlobalApplication)getApplicationContext()).getNetworkService().signIn(request);
        signIn.enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                if(response.isSuccessful()){
                    if((Boolean) response.body().get("success")){
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                        intent.putExtra("type",type);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}

