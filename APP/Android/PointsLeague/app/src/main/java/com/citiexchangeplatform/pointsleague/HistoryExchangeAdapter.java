package com.citiexchangeplatform.pointsleague;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.citiexchangeplatform.pointsleague.models.HistoryExchangeItemModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryExchangeAdapter extends RecyclerView.Adapter<HistoryExchangeAdapter.VH> {
    public static class VH extends RecyclerView.ViewHolder{
        public final TextView textViewPoints;
        public final TextView textViewExchangePoints;
        public final TextView textViewDate;

        public VH(View v) {
            super(v);
            textViewPoints = (TextView) v.findViewById(R.id.textView_points_history_exchange_item);
            textViewExchangePoints = (TextView) v.findViewById(R.id.textView_exchange_points_history_exchange_item);
            textViewDate = (TextView) v.findViewById(R.id.textView_date_history_exchange_item);
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
        holder.textViewPoints.setText(String.valueOf(items.get(position).getPoint()));
        holder.textViewExchangePoints.setText(String.format("%.1f",items.get(position).getExchangePoint()));
        holder.textViewDate.setText(items.get(position).getDate());
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
