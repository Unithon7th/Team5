package com.unithon7th.unithon_nb.home;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.unithon7th.unithon_nb.R;
import com.unithon7th.unithon_nb.SetUpActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    ViewPager viewPager;
    ImageView btn_setup;
    Boolean complete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.home_toolBar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("");
        complete = false;
        btn_setup = findViewById(R.id.btn_setup);
        btn_setup.setOnClickListener(this);

        TabLayout tabLayout = findViewById(R.id.home_tab);
        tabLayout.addTab(tabLayout.newTab().setText("자산관리"));
        tabLayout.addTab(tabLayout.newTab().setText("물가시세"));
        tabLayout.addTab(tabLayout.newTab().setText("이슈"));

        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager());


        viewPager = findViewById(R.id.home_viewPager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v == btn_setup){
            Intent intent = new Intent(getApplicationContext(), SetUpActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 100 && resultCode == RESULT_OK){
            complete = data.getBooleanExtra("complete",false);
        }
    }

    class TabPagerAdapter extends FragmentStatePagerAdapter{
        List<Fragment> pages;
        private TabPagerAdapter(FragmentManager fm) {
            super(fm);
            pages = new ArrayList<>();
            pages.add(new AssetFragment());
            pages.add(new PriceListFragment());
            pages.add(new IssueFragment());

        }

        @Override
        public Fragment getItem(int position) {

            return pages.get(position);
        }

        @Override
        public int getCount() {
            return pages.size();
        }
    }

    public Boolean getComplete(){
        return complete;
    }
}
