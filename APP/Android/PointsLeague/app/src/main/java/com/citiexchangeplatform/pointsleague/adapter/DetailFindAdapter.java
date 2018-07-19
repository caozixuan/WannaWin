package com.citiexchangeplatform.pointsleague.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.citiexchangeplatform.pointsleague.DetailFindPayActivity;
import com.citiexchangeplatform.pointsleague.R;
import com.citiexchangeplatform.pointsleague.models.DetailFindItemModel;

import java.util.ArrayList;

public class DetailFindAdapter extends BaseAdapter {

    private ArrayList<DetailFindItemModel> items;
    private Context context;

    public DetailFindAdapter(Context context){
        this.context=context;
        items = new ArrayList<DetailFindItemModel>();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_detail_find, parent, false);
        }

        final TextView textViewName = (TextView) convertView.findViewById(R.id.textView_name_detail_find_item);
        textViewName.setText(items.get(position).getName());
        final TextView textViewTime = (TextView) convertView.findViewById(R.id.textView_time_detail_find_item);
        textViewTime.setText("有效日期至" + items.get(position).getTime());
        final TextView textViewDescription = (TextView) convertView.findViewById(R.id.textView_description_detail_find_item);
        textViewDescription.setText(items.get(position).getDescription());
        final TextView textViewPoints = (TextView) convertView.findViewById(R.id.textView_points_detail_find_item);
        textViewPoints.setText("消耗花旗点：" + items.get(position).getPoints());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToPay = new Intent(context, DetailFindPayActivity.class);
                String[] info = {items.get(position).getItemID(), items.get(position).getLogoURL(), items.get(position).getName(),
                        items.get(position).getDescription(), items.get(position).getTime(), String.valueOf(items.get(position).getPoints())};
                intentToPay.putExtra("itemInfo", info);
                context.startActivity(intentToPay);
            }
        });

        return convertView;
    }

    public void addData(String itemID, String name, String time, String description, String logoURL, int points){
        DetailFindItemModel newItem = new DetailFindItemModel(itemID, name, time, description, logoURL, points);
        items.add(newItem);
        notifyDataSetChanged();
    }



}