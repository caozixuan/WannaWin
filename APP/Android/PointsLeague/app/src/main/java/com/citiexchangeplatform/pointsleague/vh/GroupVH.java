package com.citiexchangeplatform.pointsleague.vh;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.citiexchangeplatform.pointsleague.R;
import com.citiexchangeplatform.pointsleague.adapter.VExpandableAdapter;

/**
 * Created by wanjian on 2018/1/29.
 */

public class GroupVH extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener {



    public TextView id;
    public TextView totalPoints;
    public TextView date;
    public ImageView imageView;
    public View timeLine;
    public View timeLineEnd;
    //public Button refresh;
    private VExpandableAdapter.MyItemClickListener mListener;

    public GroupVH(View itemView,VExpandableAdapter.MyItemClickListener myItemClickListener) {
        super(itemView);

        //将全局的监听赋值给接口
        this.mListener = myItemClickListener;
        itemView.setOnClickListener(this);

        id = itemView.findViewById(R.id.textView_id);
        totalPoints = itemView.findViewById(R.id.textView_total_exchange_points);
        date = itemView.findViewById(R.id.textView_exchange_date);
        imageView = itemView.findViewById(R.id.imageView_history_exchange_item);
        timeLine = itemView.findViewById(R.id.timeline);
        timeLineEnd = itemView.findViewById(R.id.timeline_end);

        //refresh = itemView.findViewById(R.id.refresh);
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onItemClick(v, Integer.parseInt(id.getText().toString()));
        }
    }
}
