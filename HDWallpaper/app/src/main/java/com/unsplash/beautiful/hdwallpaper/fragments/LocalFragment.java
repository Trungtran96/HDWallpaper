package com.unsplash.beautiful.hdwallpaper.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;

import com.unsplash.beautiful.hdwallpaper.adapters.LocalAdapter;
import com.unsplash.beautiful.hdwallpaper.databinding.FragmentLocalBinding;
import android.view.View;
import android.view.ViewGroup;
import com.unsplash.beautiful.hdwallpaper.R;
import com.unsplash.beautiful.hdwallpaper.ultils.Ultils;


public class LocalFragment extends Fragment {
    private Context context;
    private FragmentLocalBinding binding;
    private BroadcastReceiver receiver;
    private IntentFilter filter;
    private LocalAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_local, container, false);

        initViews();

        return binding.getRoot();
    }

    private void initViews() {
        adapter = new LocalAdapter(context, Ultils.getPhotos());

        binding.rvLocal.setLayoutManager(new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false));
        binding.rvLocal.setAdapter(adapter);

        initReceiver();
    }

    private void initReceiver() {
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {

                    default:
                        break;
                }
            }
        };

        filter = new IntentFilter();

        context.registerReceiver(receiver, filter);
    }

    @Override
    public void onDetach() {
        try {
            context.unregisterReceiver(receiver);
        } catch (Exception e){

        }
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
