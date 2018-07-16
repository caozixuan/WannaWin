package com.citiexchangeplatform.pointsleague;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.MyViewHolder> {
    private Context context;
    //构造方法
    public MyOrderAdapter() {

    }

    @NonNull
    @Override
    public MyOrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*列表布局*/
        return new MyOrderAdapter.MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_paying, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    /**
     * ViewHolder类，用于缓存控件
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        View view;




        public MyViewHolder(View view) {
            super(view);


        }


    }

}
