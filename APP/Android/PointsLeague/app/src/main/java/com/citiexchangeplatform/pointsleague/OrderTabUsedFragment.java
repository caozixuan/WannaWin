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
public class OrderTabUsedFragment extends Fragment {

    RecyclerView usedOrderRecyclerView;
    private MyOrderAdapter orderAdapter;


    public OrderTabUsedFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        OrderTabUsedFragment fragment = new OrderTabUsedFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_tab, container, false);

        usedOrderRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_myOrder);

        //设置RecyclerView管理器
        setRecyclerView();

        //获得初始化数据
        initData();

        return view;
    }

    /*订单：name,description,date*/
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
        usedOrderRecyclerView.setLayoutManager(layoutManager);
        orderAdapter = new MyOrderAdapter(getActivity());
        usedOrderRecyclerView.setAdapter(orderAdapter);
    }

}
