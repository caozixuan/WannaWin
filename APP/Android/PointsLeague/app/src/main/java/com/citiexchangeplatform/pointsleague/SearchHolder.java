package com.citiexchangeplatform.pointsleague;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.citiexchangeplatform.pointsleague.models.PayingData;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wang on 2016/3/18.
 */
public class SearchHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.textview_business_name)
    TextView name;



    public SearchHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(PayingData data) {

        ArrayList<Integer> avatarList = new ArrayList<>();

        name.setText(data.getName());

    }


}
