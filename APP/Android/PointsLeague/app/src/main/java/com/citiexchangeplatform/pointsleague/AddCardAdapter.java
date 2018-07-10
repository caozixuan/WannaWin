package com.citiexchangeplatform.pointsleague;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.List;

public class AddCardAdapter extends RecyclerView.Adapter<AddCardAdapter.VH>{

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

    private List<String> names;
    private List<String> descriptions;
    private List<String> logos;
    public AddCardAdapter(List<String> names, List<String> descriptions, List<String> logos) {
        this.names = names;
        this.descriptions = descriptions;
        this.logos = logos;
    }

    //③ 在Adapter中实现3个方法
    @Override
    public void onBindViewHolder(VH holder, final int position) {
        holder.textViewName.setText(names.get(position));
        holder.textViewDesc.setText(descriptions.get(position));
        //Bitmap bimage = getBitmapFromURL(logos.get(position));
        //holder.imageViewLogo.setImageBitmap(bimage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), names.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_card, parent, false);
        return new VH(v);
    }

    private Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addData(String name, String description, String logo) {
        names.add(name);
        descriptions.add(description);
        logos.add(logo);
        notifyItemInserted(names.size());
    }
}