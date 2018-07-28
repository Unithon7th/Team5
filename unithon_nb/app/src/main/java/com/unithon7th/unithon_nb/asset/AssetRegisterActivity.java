package com.unithon7th.unithon_nb.asset;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.NumberPicker;

import com.unithon7th.unithon_nb.R;

import java.util.ArrayList;

public class AssetRegisterActivity extends AppCompatActivity {
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_regist);

        NumberPicker numberPicker = findViewById(R.id.numberPicker);
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(Integer.MAX_VALUE);
        numberPicker.setValue(Integer.MAX_VALUE / 2);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //여기서 완료버튼을 누르기전까지 값을 바꿔서 넣는다.
            }
        });

        list = new ArrayList<>();
        list.add("채소류");
        list.add("과일류");

        NumberPicker.Formatter formatter = new NumberPicker.Formatter() {
            @Override
            public String format(int value) {

                return list.get(value % 2);
            }
        };

        numberPicker.setFormatter(formatter);
    }
}
