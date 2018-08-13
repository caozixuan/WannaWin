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
import com.citiexchangeplatform.pointsleague.R;
import com.citiexchangeplatform.pointsleague.models.AddCardItemModel;
import com.citiexchangeplatform.pointsleague.BindCardActivity;

import java.util.ArrayList;
import java.util.List;

public class AddCardAdapter extends RecyclerView.Adapter<AddCardAdapter.VH> implements Filterable {

    public static class VH extends RecyclerView.ViewHolder{
        public final TextView textViewName;
        //public final TextView textViewDesc;
        public final ImageView imageViewLogo;

        public VH(View v) {
            super(v);
            textViewName = (TextView) v.findViewById(R.id.textView_merchant_name_item);
            //textViewDesc = (TextView) v.findViewById(R.id.textView_merchant_description_item);
            imageViewLogo = (ImageView) v.findViewById(R.id.imageView_logo_item);
        }
    }

    private Context context;
    private List<AddCardItemModel> sourceItems;
    private List<AddCardItemModel> filteredItems;

    public AddCardAdapter(Context context) {
        sourceItems = new ArrayList<AddCardItemModel>();
        filteredItems = sourceItems;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        holder.textViewName.setText(filteredItems.get(position).getName());
        //holder.textViewDesc.setText(filteredItems.get(position).getDescription());
        Glide.with(context)
                .load(filteredItems.get(position).getLogoURL())
                .placeholder(R.drawable.ic_points_black_24dp)
                .error(R.drawable.ic_mall_black_24dp)
                .into(holder.imageViewLogo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToBindCard = new Intent(context, BindCardActivity.class);
                intentToBindCard.putExtra("merchantID",filteredItems.get(position).getMerchantID());
                intentToBindCard.putExtra("logoURL",filteredItems.get(position).getLogoURL());
                context.startActivity(intentToBindCard);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredItems.size();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_card, parent, false);
        return new VH(v);
    }

    public void addData(String merchantID, String name, String description, String logoURL) {
        AddCardItemModel newItem = new AddCardItemModel(merchantID, name, description, logoURL);
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
                    List<AddCardItemModel> newFilterCards = new ArrayList<AddCardItemModel>();
                    for (AddCardItemModel item:sourceItems) {
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
                filteredItems = (ArrayList<AddCardItemModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}