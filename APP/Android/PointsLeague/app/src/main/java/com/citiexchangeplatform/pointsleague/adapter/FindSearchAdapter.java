package com.citiexchangeplatform.pointsleague.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.citiexchangeplatform.pointsleague.DetailActivityActivity;
import com.citiexchangeplatform.pointsleague.DetailFindActivity;
import com.citiexchangeplatform.pointsleague.DetailFindPayActivity;
import com.citiexchangeplatform.pointsleague.R;
import com.citiexchangeplatform.pointsleague.models.FindItemModel;
import com.citiexchangeplatform.pointsleague.models.FindSearchModel;

import java.util.ArrayList;
import java.util.List;

public class FindSearchAdapter extends RecyclerView.Adapter<FindSearchAdapter.VH> {

    private Context context;
    private List<FindSearchModel> sourceItems;


    public FindSearchAdapter(Context context) {
        sourceItems = new ArrayList<FindSearchModel>();
        this.context = context;
    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new FindSearchAdapter.VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, @SuppressLint("RecyclerView") final int position) {
        Glide.with(context)
                .load(sourceItems.get(position).getLogoURL())
                .placeholder(R.drawable.ic_points_black_24dp)
                .error(R.drawable.ic_points_black_24dp)
                .into(holder.imageViewLogo);
        holder.textViewName.setText(sourceItems.get(position).getName());

        holder.textViewDescription.setText(sourceItems.get(position).getDescription());


        switch (sourceItems.get(position).getType()){
            case "coupon":
                holder.textViewType.setText("");
                //点击
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentToDetailFind = new Intent(context, DetailFindPayActivity.class);
                        intentToDetailFind.putExtra("itemID",sourceItems.get(position).getId());
                        context.startActivity(intentToDetailFind);
                    }
                });
                break;

            case "activity":
                holder.textViewType.setText("");
                //点击
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentToDetailFind = new Intent(context, DetailActivityActivity.class);
                        intentToDetailFind.putExtra("activityID",sourceItems.get(position).getId());
                        context.startActivity(intentToDetailFind);
                    }
                });
                break;

            default:
                holder.textViewType.setText(sourceItems.get(position).getType());
                //点击
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentToDetailFind = new Intent(context, DetailFindActivity.class);
                        intentToDetailFind.putExtra("merchantID",sourceItems.get(position).getId());
                        context.startActivity(intentToDetailFind);
                    }
                });

                break;

        }


    }

    //下面两个方法提供给页面刷新和加载时调用
    public void loadMore(List<FindSearchModel> addMessageList) {
        //增加数据
        int position = sourceItems.size();
        sourceItems.addAll(position, addMessageList);
        System.out.println("source size: " + sourceItems.size());

        notifyDataSetChanged();
        //notifyItemInserted(position);
    }

    public void refresh(List<FindSearchModel> newList) {
        //刷新数据
        sourceItems.removeAll(sourceItems);
        sourceItems.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return sourceItems.size();
    }

    public void addData(String name, String id, String logoURL, String type, String description) {
        FindSearchModel newItem = new FindSearchModel(name, id,logoURL, type, description);
        sourceItems.add(newItem);
        notifyDataSetChanged();
    }

    public void clearAll(){
        sourceItems.clear();
        notifyDataSetChanged();
    }

    public static class VH extends RecyclerView.ViewHolder{
        final TextView textViewName;
        final TextView textViewType;
        final TextView textViewDescription;
        public final ImageView imageViewLogo;

        public VH(View v) {
            super(v);
            textViewName = (TextView) v.findViewById(R.id.textView_name_find);
            textViewType = (TextView) v.findViewById(R.id.textView_type_find);
            textViewDescription = (TextView) v.findViewById(R.id.textView_description_find);
            imageViewLogo = (ImageView) v.findViewById(R.id.imageView_logo_find);
        }
    }
}
