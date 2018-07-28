package com.unithon7th.unithon_nb.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.constraint.solver.GoalRow;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.unithon7th.unithon_nb.AddCookiesInterceptor;
import com.unithon7th.unithon_nb.ApplicationController;
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

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    EditText inputName, inputPhoneNumber, inputAuthNumber, inputPassword, confirmPassword;
    Boolean nameFlag, phoneFlag, authFlag, passwordFlag, confirmFlag;
    TextView btn_auth,btn_authInput,btn_next, btn_back;
    LinearLayout input_authNumber_box, password_container;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        inputName = findViewById(R.id.input_name);
        inputPhoneNumber = findViewById(R.id.input_phoneNumber);
        inputAuthNumber = findViewById(R.id.input_authNumber);
        inputPassword = findViewById(R.id.input_password);
        confirmPassword = findViewById(R.id.confirm_password);
        btn_authInput = findViewById(R.id.btn_authInput);
        btn_auth = findViewById(R.id.btn_auth);
        btn_next = findViewById(R.id.btn_next);
        btn_back = findViewById(R.id.btn_back);
        input_authNumber_box = findViewById(R.id.input_authNumber_box);
        password_container = findViewById(R.id.password_container);
        btn_authInput.setOnClickListener(this);
        btn_auth.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        getPhoneNumber();

        nameFlag = false;
        authFlag = false;
        passwordFlag = false;
        confirmFlag = false;

        type = getIntent().getStringExtra("type");
        inputName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0)
                    nameFlag = true;
                else
                    nameFlag = false;
                setBtnNext();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0)
                    phoneFlag = true;
                else
                    phoneFlag = false;
                setBtnNext();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputAuthNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0){
                    authFlag = true;
                }else{
                    authFlag = false;
                }
                setBtnNext();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0){
                    passwordFlag = true;
                }else{
                    passwordFlag = false;
                }
                setBtnNext();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0){
                    confirmFlag = true;
                }else{
                    confirmFlag = false;
                }

                setBtnNext();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 100){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getPhoneNumber();
            }
        }
    }

    public void getPhoneNumber(){

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED){
            TelephonyManager telManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
            String PhoneNum = telManager.getLine1Number();
            if(PhoneNum.startsWith("+82")){
                PhoneNum = PhoneNum.replace("+82", "0");
            }

            inputPhoneNumber.setText(PhoneNum);
            phoneFlag = true;
        }
        else{
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    100);
        }
    }

    public void setBtnNext(){
        if(nameFlag && phoneFlag && passwordFlag && confirmFlag){
            btn_next.setSelected(true);
            btn_next.setEnabled(true);
        }
        else{
            btn_next.setSelected(false);
            btn_next.setEnabled(false);
        }
    }

    @Override
    public void onClick(View v) {
        if(v == btn_authInput){
            if(nameFlag && phoneFlag){
                btn_authInput.setVisibility(View.GONE);
                input_authNumber_box.setVisibility(View.VISIBLE);
            }
        }
        else if(v == btn_auth){
            if(authFlag){
                Map<String, String> request = new HashMap<>();
                request.put("verify_number",inputAuthNumber.getText().toString());
                Call<Map<String, Object>> postAuthcode = ((GlobalApplication)getApplicationContext()).getNetworkService().postAuthCode(request);
                postAuthcode.enqueue(new Callback<Map<String, Object>>() {
                    @Override
                    public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                        if(response.isSuccessful()){
                            if((Boolean) response.body().get("success")){
                                input_authNumber_box.setVisibility(View.GONE);
                                password_container.setVisibility(View.VISIBLE);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Map<String, Object>> call, Throwable t) {

                    }
                });

            }
        }
        else if(v == btn_next){
            if(inputPassword.getText().toString().compareTo(confirmPassword.getText().toString()) == 0){
                Map<String, Object> request = new HashMap<>();

                if(type.compareTo("general") == 0){
                    request.put("type", getIntent().getStringExtra("type"));
                    request.put("cell_no", inputPhoneNumber.getText().toString());
                    request.put("pw", inputPassword.getText().toString());
                    request.put("name", inputName.getText().toString());
                }else{
                    request.put("type", getIntent().getStringExtra("type"));
                    request.put("cell_no", inputPhoneNumber.getText().toString());
                    request.put("email", "unithon9th@gmail.com");
                    request.put("name", inputName.getText().toString());
                }


                Call<Map<String, Object>> response = ((GlobalApplication)getApplicationContext()).getNetworkService().signUp(request);
                response.enqueue(new Callback<Map<String, Object>>() {
                    @Override
                    public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                        if(response.isSuccessful()){

                            if((Boolean) response.body().get("success")){
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
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

        if(v == btn_back){
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
