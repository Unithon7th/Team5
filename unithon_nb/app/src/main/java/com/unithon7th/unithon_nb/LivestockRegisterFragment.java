package com.unithon7th.unithon_nb;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.MyOptionsPickerView;

import java.util.ArrayList;


public class LivestockRegisterFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

//    private OnFragmentInteractionListener mListener;

    public LivestockRegisterFragment() {
    }

    public static LivestockRegisterFragment newInstance(String param1, String param2) {
        LivestockRegisterFragment fragment = new LivestockRegisterFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_livestock_register, container, false);

        final View farmingKindNameLayout = view.findViewById(R.id.livestock_00);

        TextView subSelect = farmingKindNameLayout.findViewById(R.id.tv_sub_select);
        subSelect.setText("마리 수");
        TextView subSelectAnswer = farmingKindNameLayout.findViewById(R.id.tv_sub_selected_answer);
        subSelectAnswer.setText("마리");

        final View currentConditionLayout = view.findViewById(R.id.livestock_01);

        TextView currentConditionLayout_subSelect = currentConditionLayout.findViewById(R.id.tv_sub_select);
        currentConditionLayout_subSelect.setText("총 마리중");
        TextView currentConditionLayout_subSelectAnswer = currentConditionLayout.findViewById(R.id.tv_sub_selected_answer);
        currentConditionLayout_subSelectAnswer.setText("암,수 선택하기");

        final View selectCountLayout = view.findViewById(R.id.livestock_02);
        TextView selectCountLayout_subSelect = selectCountLayout.findViewById(R.id.tv_sub_select);
        selectCountLayout_subSelect.setText("총 마리중");
        TextView selectCountLayout_subSelectAnswer = selectCountLayout.findViewById(R.id.tv_sub_selected_answer);
        selectCountLayout_subSelectAnswer.setText("암,수 선택하기");

        farmingKindNameLayout.setVisibility(View.VISIBLE);
        currentConditionLayout.setVisibility(View.GONE);
        selectCountLayout.setVisibility(View.GONE);

        farmingKindNameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singlePicker(currentConditionLayout);
            }
        });

        currentConditionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singlePicker(selectCountLayout);
            }
        });

        selectCountLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singlePicker();
            }
        });
        return view;
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
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
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
        void onFragmentInteraction(Uri uri);
    }
}
