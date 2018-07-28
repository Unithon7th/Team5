package com.unithon7th.unithon_nb.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unithon7th.unithon_nb.R;

public class PriceListFragment extends Fragment {
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

        view = inflater.inflate(R.layout.fragment_pricelist,container,false);
        return view;
    }
}
