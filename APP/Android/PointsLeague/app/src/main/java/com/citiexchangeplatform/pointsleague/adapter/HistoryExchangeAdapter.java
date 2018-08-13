package com.citiexchangeplatform.pointsleague.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.citiexchangeplatform.pointsleague.R;
import com.citiexchangeplatform.pointsleague.models.HistoryExchangeItemModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryExchangeAdapter extends RecyclerView.Adapter<HistoryExchangeAdapter.VH> {
    public static class VH extends RecyclerView.ViewHolder{
        public final TextView textViewPoints;
        public final TextView textViewExchangePoints;
        public final TextView textViewDate;
        public final ImageView imageView;
        public final View timeLine;

        public VH(View v) {
            super(v);
            textViewPoints = (TextView) v.findViewById(R.id.textView_points_history_exchange_item);
            textViewExchangePoints = (TextView) v.findViewById(R.id.textView_exchange_points_history_exchange_item);
            textViewDate = (TextView) v.findViewById(R.id.textView_date_history_exchange_item);
            imageView = (ImageView) v.findViewById(R.id.imageView_history_exchange_item);
            timeLine = (View) v.findViewById(R.id.timeline);
        }
    }

    private Context context;
    private List<HistoryExchangeItemModel> items;

    public HistoryExchangeAdapter(Context context) {
        items = new ArrayList<HistoryExchangeItemModel>();
        this.context = context;
    }

    @Override
    public void onBindViewHolder(HistoryExchangeAdapter.VH holder, final int position) {
        holder.textViewPoints.setText("-" + items.get(position).getPoint());
        holder.textViewExchangePoints.setText(String.format("+%.1fP",items.get(position).getExchangePoint()));
        holder.textViewDate.setText(items.get(position).getDate());
        if(position == 0){
            holder.timeLine.setVisibility(View.INVISIBLE);
        }
        switch (position%3)
        {
            case 0:
                holder.imageView.setImageResource(R.drawable.ic_dot_red_24dp);
                break;
            case 1:
                holder.imageView.setImageResource(R.drawable.ic_dot_blue_24dp);
                break;
            case 2:
                holder.imageView.setImageResource(R.drawable.ic_dot_green_24dp);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public HistoryExchangeAdapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_exchange, parent, false);
        return new HistoryExchangeAdapter.VH(v);
    }


    public void addData(int point, double exchangePoint, String date) {
        HistoryExchangeItemModel newItem = new HistoryExchangeItemModel(point, exchangePoint, date);
        items.add(newItem);
        notifyDataSetChanged();
    }

}
