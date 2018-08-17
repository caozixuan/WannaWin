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

import com.bumptech.glide.Glide;
import com.citiexchangeplatform.pointsleague.DetailActivityActivity;
import com.citiexchangeplatform.pointsleague.DetailFindPayActivity;
import com.citiexchangeplatform.pointsleague.R;
import com.citiexchangeplatform.pointsleague.models.DetailActivityItemModel;
import com.citiexchangeplatform.pointsleague.models.FindActivityItemModel;

import java.util.ArrayList;
import java.util.List;

public class DetailActivityAdapter extends RecyclerView.Adapter<DetailActivityAdapter.VH>{

    public static class VH extends RecyclerView.ViewHolder{
        public final TextView textView;
        public final ImageView imageViewLogo;

        public VH(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.textView_activity_find);
            imageViewLogo = (ImageView) v.findViewById(R.id.imageView_activity_find_item);
        }
    }

    private Context context;
    private List<DetailActivityItemModel> items;

    public DetailActivityAdapter(Context context) {
        items = new ArrayList<DetailActivityItemModel>();
        this.context = context;
    }

    @Override
    public void onBindViewHolder(DetailActivityAdapter.VH holder, final int position) {
        Glide.with(context)
                .load(items.get(position).getLogoURL())
                .centerCrop()
                .error(R.drawable.loading_card)
                .into(holder.imageViewLogo);
        holder.textView.setText(items.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToDetailActivity = new Intent(context, DetailActivityActivity.class);
                intentToDetailActivity.putExtra("activityID",items.get(position).getActivityID());
                context.startActivity(intentToDetailActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public DetailActivityAdapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_find, parent, false);
        return new DetailActivityAdapter.VH(v);
    }


    public void addData(String activityID, String name, String logoURL) {
        DetailActivityItemModel newItem = new DetailActivityItemModel(activityID, name, logoURL);
        items.add(newItem);
        notifyDataSetChanged();
    }




}
