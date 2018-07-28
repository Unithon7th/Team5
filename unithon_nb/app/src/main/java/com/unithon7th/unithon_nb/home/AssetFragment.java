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

public class AssetFragment extends Fragment{
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
        return view;
    }

}
