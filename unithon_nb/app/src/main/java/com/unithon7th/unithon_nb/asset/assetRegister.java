package com.unithon7th.unithon_nb.asset;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.unithon7th.unithon_nb.AgriculturalRegisterFragment;
import com.unithon7th.unithon_nb.LivestockRegisterFragment;
import com.unithon7th.unithon_nb.R;
import com.unithon7th.unithon_nb.RegisterFragment;

public class AssetRegister extends AppCompatActivity implements RegisterFragment.OnFragmentRegisterListener {


    static final int ADD_ARGU = 1;
    static final int ADD_LIVESTOCK = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_register_main);

        //content_main
        FragmentManager fm = getSupportFragmentManager();
        RegisterFragment registerFragment = new RegisterFragment();
        fm.beginTransaction()
                .add(R.id.register_main_contents_layout, registerFragment)
                .commit();

    }

    @Override
    public void onFragmentInteraction(int i) {

        if(i == ADD_ARGU){
            AgriculturalRegisterFragment arf = new AgriculturalRegisterFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.register_main_contents_layout, arf)
                    .commit();
        } else if(i == ADD_LIVESTOCK){

            LivestockRegisterFragment srf = new LivestockRegisterFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.register_main_contents_layout, srf)
                    .commit();

        }


    }
}
