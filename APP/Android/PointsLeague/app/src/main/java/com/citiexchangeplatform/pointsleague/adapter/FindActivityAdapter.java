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
import com.citiexchangeplatform.pointsleague.R;
import com.citiexchangeplatform.pointsleague.models.FindItemModel;

import java.util.ArrayList;
import java.util.List;

public class FindActivityAdapter extends RecyclerView.Adapter<FindActivityAdapter.VH> implements Filterable {

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
    private List<FindItemModel> sourceItems;
    private List<FindItemModel> filteredItems;

    public FindActivityAdapter(Context context) {
        sourceItems = new ArrayList<FindItemModel>();
        filteredItems = sourceItems;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(FindActivityAdapter.VH holder, final int position) {
//        Glide.with(context)
//                .load(filteredItems.get(position).getLogoURL())
//                .placeholder(R.drawable.ic_points_black_24dp)
//                .error(R.drawable.ic_points_black_24dp)
//                .into(holder.imageViewLogo);
        holder.textView.setText(filteredItems.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, filteredItems.get(position).getName(), Toast.LENGTH_SHORT).show();
//                Intent intentToDetailFind = new Intent(context, DetailFindActivity.class);
//                intentToDetailFind.putExtra("merchantID",filteredItems.get(position).getMerchantID());
//                context.startActivity(intentToDetailFind);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredItems.size();
    }

    @Override
    public FindActivityAdapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_find, parent, false);
        return new FindActivityAdapter.VH(v);
    }


    public void addData(String name, String merchantID, String logoURL, String type, String description) {
        FindItemModel newItem = new FindItemModel(name, merchantID,logoURL, type, description);
        sourceItems.add(newItem);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String partName = constraint.toString();
                if(partName.isEmpty()){
                    filteredItems = sourceItems;
                }else {
                    List<FindItemModel> newFilterCards = new ArrayList<FindItemModel>();
                    for (FindItemModel item:sourceItems) {
                        System.out.println(item.getName().toLowerCase() + "    " + partName.toLowerCase());
                        if(item.getName().toLowerCase().contains(partName.toLowerCase()))
                            newFilterCards.add(item);
                    }
                    filteredItems = newFilterCards;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredItems;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredItems = (ArrayList<FindItemModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}
