package com.unithon7th.unithon_nb.home;

import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.unithon7th.unithon_nb.ApplicationController;
import com.unithon7th.unithon_nb.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssetFragment extends Fragment implements View.OnClickListener{
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*
        int resid;
        if(아이템이 0이면){
            resid = R.layout.fragment_home_none;
        }

        else{
            resid = R.layout.fragment_asset;
        }
        */
        view = inflater.inflate(R.layout.fragment_asset,container,false);

        view.findViewById(R.id.btn_login).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Map<String, Object> request = new HashMap<>();
        request.put("type", "general");
        request.put("cell_no", "010-2958-3242");
        request.put("pw", "ㅎㅎㅎ");
        request.put("name", "ㅎㅎㅎ");

         Call<Map<String, Object>> response = ((ApplicationController)getActivity().getApplicationContext()).getNetworkService().signUp(request);
         response.enqueue(new Callback<Map<String, Object>>() {
             @Override
             public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                 if(response.isSuccessful()){

                     if((Boolean) response.body().get("success")){

                         LinkedTreeMap<String, String> session = (LinkedTreeMap<String, String>) response.body().get("session");
                         String string = session.get("name") + session.get("cell_no") + session.get("id");

                         Toast.makeText(getActivity().getApplicationContext(), string, Toast.LENGTH_SHORT).show();
                     }
                     Log.d("success","success");
                 }

             }

             @Override
             public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                t.printStackTrace();
             }
         });
    }
}
