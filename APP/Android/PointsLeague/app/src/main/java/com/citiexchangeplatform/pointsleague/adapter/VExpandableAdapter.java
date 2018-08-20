package com.citiexchangeplatform.pointsleague.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.citiexchangeplatform.pointsleague.ItemClickListener;
import com.citiexchangeplatform.pointsleague.R;
import com.citiexchangeplatform.pointsleague.data.RecordChild;
import com.citiexchangeplatform.pointsleague.data.RecordParent;

import com.citiexchangeplatform.pointsleague.models.PointsExchangeModel;
import com.citiexchangeplatform.pointsleague.vh.ChildVH;
import com.citiexchangeplatform.pointsleague.vh.GroupVH;


import java.util.ArrayList;
import java.util.List;


public class VExpandableAdapter extends ExpandableAdapter<GroupVH, ChildVH> {


    private List<RecordParent> recordList = new ArrayList<>();
    private Context context;


    //点击
    private MyItemClickListener mItemClickListener;


    public VExpandableAdapter(Context context) {
        super();
        this.context = context;
    }

    public void addData(RecordParent recordParent){
        recordList.add(recordParent);
        notifyDataSetChanged();
    }

    /**
     * 创建一个回调接口
     */
    public interface MyItemClickListener {
        void onItemClick(View view, int position);
    }

    /**
     * 在activity里面adapter就是调用的这个方法,将点击事件监听传递过来,并赋值给全局的监听
     *
     * @param myItemClickListener
     */
    public void setItemClickListener(MyItemClickListener myItemClickListener) {
        this.mItemClickListener = myItemClickListener;
    }


    public List<RecordParent> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<RecordParent> recordList) {
        this.recordList = recordList;
    }

    @Override
    public int getGroupCount() {
        return recordList.size();
    }

    @Override
    public int getChildCount(int groupIndex) {
        return recordList.get(groupIndex).childs.size();
    }

    @Override
    public GroupVH onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, ">>> onCreateGroupViewHolder: " + viewType);
        Context context = parent.getContext();
        //将全局的监听传递给holder
        return new GroupVH(LayoutInflater.from(context).inflate(R.layout.item_points_record_parent, parent, false),mItemClickListener);

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
        //if(position == 0){
        //    vh.timeLine.setVisibility(View.INVISIBLE);
        //}
        //vh.date.setText(String.valueOf(getChildCount(position)));
        vh.id.setText(String.valueOf(position));
        vh.totalPoints.setText(recordList.get(position).totalExchangePoint);
        vh.date.setText(recordList.get(position).date);
        //vh.refresh.setOnClickListener(listener);
        //vh.refresh.setText(isExpanded(position) ? "Close" : "Open");

        switch (position%3)
        {
            case 0:
                vh.imageView.setImageResource(R.drawable.ic_dot_red_24dp);
                break;
            case 1:
                vh.imageView.setImageResource(R.drawable.ic_dot_blue_24dp);
                break;
            case 2:
                vh.imageView.setImageResource(R.drawable.ic_dot_green_24dp);
                break;
        }



    }

    @Override
    public ChildVH onCreateChildViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, ">>> onCreateChildViewHolder: " + viewType);
        Context context = parent.getContext();
        return new ChildVH(LayoutInflater.from(context).inflate(R.layout.item_points_record_child, parent, false));

    }

    @Override
    public void onBindChildViewHolder(ChildVH holder, final int groupIndex, final int childIndex) {
        Log.d(TAG, "onBindChildViewHolder: " + groupIndex + ":" + childIndex);
        int type = getChildItemViewType(groupIndex, childIndex);
        ChildVH vh = (ChildVH) holder;
        RecordChild recordChild = recordList.get(groupIndex).childs.get(childIndex);

        vh.name.setText(recordChild.name);
        vh.usePoints.setText(recordChild.usePoints);
        vh.exchangePoints.setText(recordChild.exchangePoints);

    }



}
