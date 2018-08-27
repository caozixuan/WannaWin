package com.citiexchangeplatform.pointsleague.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.citiexchangeplatform.pointsleague.CardInfoActivity;
import com.citiexchangeplatform.pointsleague.R;
import com.citiexchangeplatform.pointsleague.models.AllCardItemModel;

import java.util.ArrayList;
import java.util.List;

public class AllCardAdapter extends RecyclerView.Adapter<AllCardAdapter.VH> implements Filterable {

    public static class VH extends RecyclerView.ViewHolder{
        public final TextView textViewName;
        public final ImageView imageViewLogo;
        public final TextView textViewPoints;
        public final TextView textViewExchangePoints;
        public final RelativeLayout cardView;

        public VH(View v) {
            super(v);
            textViewName = (TextView) v.findViewById(R.id.textView_name_all_card_item);
            imageViewLogo = (ImageView) v.findViewById(R.id.imageView_logo_all_card_item);
            textViewPoints = (TextView) v.findViewById(R.id.textView_all_points_all_card_item);
            textViewExchangePoints = (TextView) v.findViewById(R.id.textView_exchange_points_all_card_item);
            cardView = (RelativeLayout) v.findViewById(R.id.relative_all_card_item);
        }
    }

    private Context context;
    private List<AllCardItemModel> sourceItems;
    private List<AllCardItemModel> filteredItems;

    public AllCardAdapter(Context context) {
        sourceItems = new ArrayList<AllCardItemModel>();
        filteredItems = sourceItems;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        Glide.with(context)
                .load(filteredItems.get(position).getLogoURL())
                .placeholder(R.drawable.ic_points_black_24dp)
                .error(R.drawable.ic_mall_black_24dp)
                .into(holder.imageViewLogo);
        holder.textViewName.setText(filteredItems.get(position).getName());
        holder.textViewPoints.setText("积分：" + filteredItems.get(position).getPoint());
        holder.textViewExchangePoints.setText("可兑通用点" + String.format("%.1f",filteredItems.get(position).getPoint()*filteredItems.get(position).getProportion()));

        switch (filteredItems.get(position).getCardStyle()){
            case 0:
                holder.cardView.setBackgroundResource(R.drawable.bg2_1);
                break;
            case 1:
                holder.cardView.setBackgroundResource(R.drawable.bg2_2);
                break;
            case 2:
                holder.cardView.setBackgroundResource(R.drawable.bg2_3);
                break;
            case 3:
                holder.cardView.setBackgroundResource(R.drawable.bg2_4);
                break;
            case 4:
                holder.cardView.setBackgroundResource(R.drawable.bg2_5);
                break;
        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), filteredItems.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent intentToCardInfo = new Intent(context, CardInfoActivity.class);
                intentToCardInfo.putExtra("merchantID",filteredItems.get(position).getMerchantID());
                context.startActivity(intentToCardInfo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredItems.size();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_card, parent, false);
        return new VH(v);
    }


    public void addData(String merchantID, String name, String logoURL, int point, double proportion, int cardStyle) {
        AllCardItemModel newItem = new AllCardItemModel(merchantID, name, logoURL, point, proportion, cardStyle);
        sourceItems.add(newItem);
        notifyDataSetChanged();
    }

    public void clearAll(){
        sourceItems.clear();
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
                    List<AllCardItemModel> newFilterCards = new ArrayList<AllCardItemModel>();
                    for (AllCardItemModel item:sourceItems) {
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
                filteredItems = (ArrayList<AllCardItemModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}