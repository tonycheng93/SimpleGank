package com.sky.simplegank.Welfare;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky.simplegank.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WelfareFragment extends Fragment {


    public WelfareFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welfare, container, false);
    }

}
