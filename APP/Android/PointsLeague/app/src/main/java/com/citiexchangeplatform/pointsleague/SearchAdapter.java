package com.citiexchangeplatform.pointsleague;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.citiexchangeplatform.pointsleague.models.ExchangeModel;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchHolder> {


    private List<ExchangeModel> mList;

    public SearchAdapter(List<ExchangeModel> list) {
        this.mList = list;
    }

    @Override
    public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paying, parent, false);
        return new SearchHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchHolder holder, int position) {
        holder.bind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setFilter(List<ExchangeModel> data) {
        mList = new ArrayList<>();
        mList.addAll(data);
        notifyDataSetChanged();
    }

    public void animateTo(List<ExchangeModel> data) {
        applyAndAnimateRemovals(data);
        applyAndAnimateAdditions(data);
        applyAndAnimateMovedItems(data);
    }

    private void applyAndAnimateRemovals(List<ExchangeModel> data) {
        for (int i = mList.size() - 1; i >= 0; i--) {
            final ExchangeModel people = mList.get(i);
            if (!data.contains(people)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<ExchangeModel> data) {
        for (int i = 0, count = data.size(); i < count; i++) {
            final ExchangeModel people = mList.get(i);
            if (!mList.contains(people)) {
                addItem(i, people);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<ExchangeModel> data) {
        for (int toPosition = data.size() - 1; toPosition >= 0; toPosition--) {
            final ExchangeModel people = data.get(toPosition);
            final int fromPosition = mList.indexOf(people);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }


    public ExchangeModel removeItem(int position) {
        final ExchangeModel data = mList.remove(position);
        notifyItemRemoved(position);
        return data;
    }


    public void addItem(int position, ExchangeModel data) {
        mList.add(position, data);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final ExchangeModel data = mList.remove(fromPosition);
        mList.add(toPosition, data);
        notifyItemMoved(fromPosition, toPosition);
    }

}