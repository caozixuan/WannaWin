package com.citiexchangeplatform.pointsleague.vh;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.citiexchangeplatform.pointsleague.R;

/**
 * Created by wanjian on 2018/1/29.
 */

public class GroupVH extends RecyclerView.ViewHolder {



    public TextView totalPoints;
    public TextView date;
    public Button refresh;

    public GroupVH(View itemView) {
        super(itemView);

        totalPoints = itemView.findViewById(R.id.textView_total_exchange_points);
        date = itemView.findViewById(R.id.textView_exchange_date);
        refresh = itemView.findViewById(R.id.refresh);
    }
}
