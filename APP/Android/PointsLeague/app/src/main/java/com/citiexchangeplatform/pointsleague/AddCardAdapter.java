package com.citiexchangeplatform.pointsleague;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.support.annotation.NonNull;
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
import com.citiexchangeplatform.pointsleague.models.AddCardItemModel;
import com.citiexchangeplatform.pointsleague.models.AllCardItemModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class AddCardAdapter extends RecyclerView.Adapter<AddCardAdapter.VH> implements Filterable {

    public static class VH extends RecyclerView.ViewHolder{
        public final TextView textViewName;
        public final TextView textViewDesc;
        public final ImageView imageViewLogo;

        public VH(View v) {
            super(v);
            textViewName = (TextView) v.findViewById(R.id.textView_merchant_name_item);
            textViewDesc = (TextView) v.findViewById(R.id.textView_merchant_description_item);
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
        holder.textViewDesc.setText(filteredItems.get(position).getDescription());
        Glide.with(context)
                .load(filteredItems.get(position).getLogoURL())
                .placeholder(R.drawable.ic_points_black_24dp)
                .error(R.drawable.ic_mall_black_24dp)
                .into(holder.imageViewLogo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), filteredItems.get(position).getName(), Toast.LENGTH_SHORT).show();
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