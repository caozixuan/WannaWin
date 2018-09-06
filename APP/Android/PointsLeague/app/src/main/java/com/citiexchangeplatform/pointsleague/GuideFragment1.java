package com.citiexchangeplatform.pointsleague;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class GuideFragment1 extends Fragment {



    public GuideFragment1() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        GuideFragment1 fragment = new GuideFragment1();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guide_1, container, false);
    }

}
