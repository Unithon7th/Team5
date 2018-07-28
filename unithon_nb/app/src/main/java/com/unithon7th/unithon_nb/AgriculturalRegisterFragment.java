package com.unithon7th.unithon_nb;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.MyOptionsPickerView;

import java.util.ArrayList;


public class AgriculturalRegisterFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    public AgriculturalRegisterFragment() {
    }

    public static AgriculturalRegisterFragment newInstance(String param1, String param2) {
        AgriculturalRegisterFragment fragment = new AgriculturalRegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agricultural_register, container, false);

        View selectMiniLayout = view.findViewById(R.id.select_mini_classification);

        final View farmingKindNameLayout = view.findViewById(R.id.farming_kind_name);

        TextView subSelect = farmingKindNameLayout.findViewById(R.id.tv_sub_select);
        subSelect.setText("품목");
        TextView subSelectAnswer = farmingKindNameLayout.findViewById(R.id.tv_sub_selected_answer);
        subSelectAnswer.setText("항목 선택하기");

        final View currentConditionLayout = view.findViewById(R.id.current_condition);

        TextView currentConditionLayout_subSelect = currentConditionLayout.findViewById(R.id.tv_sub_select);
        currentConditionLayout_subSelect.setText("재배 면적");
        TextView currentConditionLayout_subSelectAnswer = currentConditionLayout.findViewById(R.id.tv_sub_selected_answer);
        currentConditionLayout_subSelectAnswer.setText("땅 단위 면적");

        final View currentConditionGroundLayout = view.findViewById(R.id.current_condition_ground);

        TextView currentConditionGroundLayout_subSelect = currentConditionGroundLayout.findViewById(R.id.tv_sub_select);
        currentConditionGroundLayout_subSelect.setText("재배 현황");
        TextView currentConditionGroundLayout_subSelectAnswer = currentConditionGroundLayout.findViewById(R.id.tv_sub_selected_answer);
        currentConditionGroundLayout_subSelectAnswer.setText("재배 현황 선택하기");

        final View currentFarmingCountLayout = view.findViewById(R.id.current_farming_count);
        TextView currentFarmingCountLayout_subSelect = currentFarmingCountLayout.findViewById(R.id.tv_sub_select);
        currentFarmingCountLayout_subSelect.setText("수량");
        TextView currentFarmingCountLayout_subSelectAnswer = currentFarmingCountLayout.findViewById(R.id.tv_sub_selected_answer);
        currentFarmingCountLayout_subSelectAnswer.setText("kg");


        selectMiniLayout.setVisibility(View.VISIBLE);
        farmingKindNameLayout.setVisibility(View.GONE);
        currentConditionLayout.setVisibility(View.GONE);
        currentConditionGroundLayout.setVisibility(View.GONE);
        currentFarmingCountLayout.setVisibility(View.GONE);

        selectMiniLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singlePicker(farmingKindNameLayout);
            }
        });

        farmingKindNameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singlePicker(currentConditionLayout);
            }
        });

        currentConditionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singlePicker(currentConditionGroundLayout);
            }
        });

        currentConditionGroundLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singlePicker(currentFarmingCountLayout);
            }
        });

        currentFarmingCountLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singlePicker();
            }
        });
        return view;
    }

    private void setSelectText(View layoutview, String option1, String option2) {
        TextView subSelect = layoutview.findViewById(R.id.tv_sub_select);
        subSelect.setText(option1);
        TextView subSelectAnswer = layoutview.findViewById(R.id.tv_sub_selected_answer);
        subSelectAnswer.setText(option2);
    }

    private void singlePicker(final View view) {
        MyOptionsPickerView<String> singlePicker = new MyOptionsPickerView<String>(getActivity());
        final ArrayList<String> items = new ArrayList<String>();
        items.add("A");
        items.add("B");
        items.add("C");
        items.add("D");
        items.add("E");
        singlePicker.setPicker(items);
        singlePicker.setCyclic(false);
        singlePicker.setSelectOptions(0);
        singlePicker.setOnoptionsSelectListener(new MyOptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                Toast.makeText(getActivity(), "" + items.get(options1), Toast.LENGTH_SHORT).show();
                view.setVisibility(View.VISIBLE);
            }
        });
        singlePicker.show();
    }

    private void singlePicker() {
        MyOptionsPickerView<String> singlePicker = new MyOptionsPickerView<String>(getActivity());
        final ArrayList<String> items = new ArrayList<String>();
        items.add("A");
        items.add("B");
        items.add("C");
        items.add("D");
        items.add("E");
        singlePicker.setPicker(items);
        singlePicker.setCyclic(false);
        singlePicker.setSelectOptions(0);
        singlePicker.setOnoptionsSelectListener(new MyOptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                Toast.makeText(getActivity(), "" + items.get(options1), Toast.LENGTH_SHORT).show();
            }
        });
        singlePicker.show();
    }

    public void onButtonPressed(Uri uri) {
    }


//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
