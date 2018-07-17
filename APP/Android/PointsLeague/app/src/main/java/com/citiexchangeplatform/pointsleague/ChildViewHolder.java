package com.citiexchangeplatform.pointsleague;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.citiexchangeplatform.pointsleague.models.PointsExchangeModel;

public class ChildViewHolder extends BaseViewHolder {

    private Context mContext;
    private View view;
    private TextView msCardPoint;
    private TextView exchangePoint;

    public ChildViewHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
        this.view = itemView;
    }

    public void bindView(final PointsExchangeModel model, final int pos){

        msCardPoint = (TextView) view.findViewById(R.id.textView_record_msCard_point);
        exchangePoint = (TextView) view.findViewById(R.id.textView_record_exchange_point);

        msCardPoint.setText(model.getMsCardPoint());
        exchangePoint.setText(model.getExchangePoint());

    }
}
