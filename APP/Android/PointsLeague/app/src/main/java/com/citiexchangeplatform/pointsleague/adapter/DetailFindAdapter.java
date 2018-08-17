package com.citiexchangeplatform.pointsleague.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.citiexchangeplatform.pointsleague.DetailFindActivity;
import com.citiexchangeplatform.pointsleague.DetailFindPayActivity;
import com.citiexchangeplatform.pointsleague.R;
import com.citiexchangeplatform.pointsleague.models.DetailFindItemModel;
import com.citiexchangeplatform.pointsleague.models.FindItemModel;

import java.util.ArrayList;
import java.util.List;

public class DetailFindAdapter extends RecyclerView.Adapter<DetailFindAdapter.VH>{

    public static class VH extends RecyclerView.ViewHolder{
        public final TextView textViewName;
        public final TextView textViewDescription;
        public final TextView textViewPoints;

        public VH(View v) {
            super(v);
            textViewName = (TextView) v.findViewById(R.id.textView_name_detail_find);
            textViewDescription = (TextView) v.findViewById(R.id.textView_description_detail_find);
            textViewPoints = (TextView) v.findViewById(R.id.textView_points_detail_find);
        }
    }

    private Context context;
    private List<DetailFindItemModel> items;

    public DetailFindAdapter(Context context) {
        items = new ArrayList<DetailFindItemModel>();
        this.context = context;
    }

    @Override
    public void onBindViewHolder(DetailFindAdapter.VH holder, final int position) {
        holder.textViewName.setText(items.get(position).getName());
        holder.textViewDescription.setText(items.get(position).getDescription());
        holder.textViewPoints.setText(String.format("%dP",items.get(position).getPoints()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToDetailFindPay = new Intent(context, DetailFindPayActivity.class);
                intentToDetailFindPay.putExtra("itemID", items.get(position).getItemID());
                context.startActivity(intentToDetailFindPay);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public DetailFindAdapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_find, parent, false);
        return new DetailFindAdapter.VH(v);
    }


    public void addData(DetailFindItemModel newItem) {
        items.add(newItem);
        notifyDataSetChanged();
    }




}
