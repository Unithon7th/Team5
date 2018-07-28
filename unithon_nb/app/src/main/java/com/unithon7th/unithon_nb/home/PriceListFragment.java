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

public class PriceListFragment extends Fragment{
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pricelist,container,false);
        return view;
    }
}
