package com.citiexchangeplatform.pointsleague;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderTabFragment extends Fragment {


    public OrderTabFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        OrderTabFragment fragment = new OrderTabFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_tab, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_myOrder);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new MyOrderAdapter());
        return view;
    }

}
