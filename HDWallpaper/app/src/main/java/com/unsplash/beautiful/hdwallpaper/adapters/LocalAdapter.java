package com.unsplash.beautiful.hdwallpaper.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.unsplash.beautiful.hdwallpaper.R;
import com.unsplash.beautiful.hdwallpaper.databinding.ItemViewBinding;

import java.util.ArrayList;

public class LocalAdapter extends RecyclerView.Adapter<LocalAdapter.RecyclerViewHolder> {
    private ArrayList<Integer> data = new ArrayList<>();
    private Context context;
    public static final String TAG = "LocalAdapter";

    public LocalAdapter(Context context, ArrayList<Integer> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemViewBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_view, parent, false);

        return new RecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        final int item = data.get(position);

        Glide.with(context).load(item).into(holder.binding.ivWallpaper);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private final ItemViewBinding binding;

        public RecyclerViewHolder(final ItemViewBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}
