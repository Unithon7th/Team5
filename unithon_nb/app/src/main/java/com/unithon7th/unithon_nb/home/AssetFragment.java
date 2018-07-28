package com.unithon7th.unithon_nb.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unithon7th.unithon_nb.R;
import com.unithon7th.unithon_nb.asset.AssetRegister;

public class AssetFragment extends Fragment implements View.OnClickListener{
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int resid;

        if(((HomeActivity)getActivity()).complete){
            resid = R.layout.fragment_asset;
        }
        else{
            resid = R.layout.fragment_home_none;
        }
        view = inflater.inflate(resid,container,false);

        view.findViewById(R.id.asset_btn_start).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity().getApplicationContext(), AssetRegister.class);
        startActivityForResult(intent,100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
