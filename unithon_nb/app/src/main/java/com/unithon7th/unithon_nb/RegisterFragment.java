package com.unithon7th.unithon_nb;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class RegisterFragment extends Fragment {

    static final int ADD_ARGU = 1;
    static final int ADD_LIVESTOCK = 2;

    private OnFragmentRegisterListener mListener;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mainLayout = inflater.inflate(R.layout.fragment_register, container, false);

        View contentMainLinearLayout = mainLayout.findViewById(R.id.layout_contents_main);

        ViewGroup agriculturalViewGroup = mainLayout.findViewById(R.id.view_group_agricultural);

        View add_layout_agricultural = mainLayout.findViewById(R.id.add_agricultural_container);

        add_layout_agricultural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction(ADD_ARGU);
            }
        });

        ViewGroup livestockViewGroup = mainLayout.findViewById(R.id.view_group_livestock);

        View add_layout_livestock = mainLayout.findViewById(R.id.add_livestock_container);

        add_layout_livestock.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction(ADD_LIVESTOCK);
            }
        });
        return mainLayout;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentRegisterListener) {
            mListener = (OnFragmentRegisterListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentRegisterListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentRegisterListener {
        void onFragmentInteraction(int i);
    }
}
