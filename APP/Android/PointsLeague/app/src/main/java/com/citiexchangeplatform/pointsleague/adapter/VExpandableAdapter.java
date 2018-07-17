package com.citiexchangeplatform.pointsleague.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.citiexchangeplatform.pointsleague.R;
import com.citiexchangeplatform.pointsleague.data.Goods;
import com.citiexchangeplatform.pointsleague.data.Shop;

import com.citiexchangeplatform.pointsleague.vh.ChildVH;
import com.citiexchangeplatform.pointsleague.vh.GroupVH;



import java.util.List;


public class VExpandableAdapter extends ExpandableAdapter<GroupVH, ChildVH> {


    private List<Shop> shopList;

    public VExpandableAdapter(List<Shop> shopList) {
        super();
        this.shopList = shopList;
    }

    @Override
    public int getGroupCount() {
        return shopList.size();
    }

    @Override
    public int getChildCount(int groupIndex) {
        return shopList.get(groupIndex).goods.size();
    }

    @Override
    public GroupVH onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, ">>> onCreateGroupViewHolder: " + viewType);
        Context context = parent.getContext();
        return new GroupVH(LayoutInflater.from(context).inflate(R.layout.h_item_group_all, parent, false));

    }

    @Override
    public void onBindGroupViewHolder(final GroupVH holder, final int position) {
        Log.d(TAG, "onBindGroupViewHolder: " + position + " " + holder);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isExpanded(position)) {
                    collapseGroup(position);
                    //((Button) v).setText("Open");
                } else {
                    expandGroup(position);
                    //((Button) v).setText("Close");
                }
            }
        };

        GroupVH vh = ((GroupVH) holder);
        vh.date.setText(String.valueOf(getChildCount(position)));
        vh.totalPoints.setText(shopList.get(position).shopName);
        vh.refresh.setOnClickListener(listener);
        //vh.refresh.setText(isExpanded(position) ? "Close" : "Open");



    }

    @Override
    public ChildVH onCreateChildViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, ">>> onCreateChildViewHolder: " + viewType);
        Context context = parent.getContext();
        return new ChildVH(LayoutInflater.from(context).inflate(R.layout.h_item_child_clothes, parent, false));

    }

    @Override
    public void onBindChildViewHolder(ChildVH holder, final int groupIndex, final int childIndex) {
        Log.d(TAG, "onBindChildViewHolder: " + groupIndex + ":" + childIndex);
        int type = getChildItemViewType(groupIndex, childIndex);
        ChildVH vh = (ChildVH) holder;
        Goods goods = shopList.get(groupIndex).goods.get(childIndex);

        vh.name.setText(goods.name);



    }


}
