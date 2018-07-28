package com.unithon7th.unithon_nb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

public class SetUpActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);

        findViewById(R.id.btn_scrap).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), ScrapActivity.class);
        startActivity(intent);
    }
}
