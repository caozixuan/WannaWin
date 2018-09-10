package com.citiexchangeplatform.pointsleague;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class GuideFragment2 extends Fragment {


    private Button button;

    public GuideFragment2() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        GuideFragment2 fragment = new GuideFragment2();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_guide_2, container, false);
        button = (Button) view.findViewById(R.id.button_start);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Objects.requireNonNull(getActivity()), MainActivity.class);
                startActivity(intent);
                getActivity().onBackPressed();//销毁自己
            }
        });

        return view;
    }

}
