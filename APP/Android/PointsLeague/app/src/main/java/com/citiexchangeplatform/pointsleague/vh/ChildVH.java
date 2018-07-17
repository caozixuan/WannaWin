package com.citiexchangeplatform.pointsleague.vh;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.citiexchangeplatform.pointsleague.R;

/**
 * Created by wanjian on 2018/1/29.
 */

public class ChildVH extends RecyclerView.ViewHolder {

    public TextView name;
    public TextView date;

    public ChildVH (View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.textView_exchange_company_name);
        date = itemView.findViewById(R.id.textView_use_card_points);

    }
}
