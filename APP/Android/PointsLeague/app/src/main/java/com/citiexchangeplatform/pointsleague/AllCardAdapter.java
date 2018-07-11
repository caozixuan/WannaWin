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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

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

public class AllCardAdapter extends RecyclerView.Adapter<AllCardAdapter.VH>{

    public static class VH extends RecyclerView.ViewHolder{
        public final TextView textViewName;
        public final ImageView imageViewLogo;

        public VH(View v) {
            super(v);
            textViewName = (TextView) v.findViewById(R.id.textView_name_all_card_item);
            imageViewLogo = (ImageView) v.findViewById(R.id.imageView_logo_all_card_item);
        }
    }

    private Context context;
    private List<String> names;
    private List<String> logos;

    public AllCardAdapter(Context context) {
        names = new ArrayList<String>();
        logos = new ArrayList<String>();
        this.context = context;
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        holder.textViewName.setText(names.get(position));
        //image

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), names.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return logos.size();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_card, parent, false);
        return new VH(v);
    }


    public void addData(String name, String logoURL) {
        names.add(name);
        logos.add(logoURL);
        notifyDataSetChanged();
    }

}