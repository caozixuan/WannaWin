package com.citiexchangeplatform.pointsleague;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link OrderTabOverdueFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderTabOverdueFragment extends Fragment {
    RecyclerView overdueOrderRecyclerView;
    private MyOrderAdapter orderAdapter;



    public OrderTabOverdueFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        OrderTabOverdueFragment fragment = new OrderTabOverdueFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_tab_overdue, container, false);

        overdueOrderRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_myOrder_overdue);

        //设置RecyclerView管理器
        setRecyclerView();

        //获得初始化数据
        initData();

        return view;
    }


    /*订单：totalPoints,description,date*/
    protected void initData()
    {
        orderAdapter.addData("niki","5元代金券","2018-7-23");
        orderAdapter.addData("中国联通","5元代金券","2018-7-26");
        orderAdapter.addData("中国移动","5元代金券","2018-7-25");
        orderAdapter.addData("中国电信","5元代金券","2018-7-27");
        orderAdapter.addData("niki","5元代金券","2018-7-25");
        orderAdapter.addData("中国联通","5元代金券","2018-7-27");




    }

    protected void setRecyclerView(){

        orderAdapter = new MyOrderAdapter(getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        overdueOrderRecyclerView.setLayoutManager(layoutManager);
        orderAdapter = new MyOrderAdapter(getActivity());
        overdueOrderRecyclerView.setAdapter(orderAdapter);
    }



}
