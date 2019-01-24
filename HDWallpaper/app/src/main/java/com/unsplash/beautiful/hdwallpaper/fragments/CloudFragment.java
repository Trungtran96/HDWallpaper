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
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.unsplash.beautiful.hdwallpaper.R;
import com.unsplash.beautiful.hdwallpaper.adapters.PhotoRecyclerAdapter;
import com.unsplash.beautiful.hdwallpaper.databinding.FragmentCloudBinding;
import com.unsplash.beautiful.hdwallpaper.dialogs.LoadingDialog;
import com.unsplash.beautiful.hdwallpaper.sample.Unsplash;
import com.unsplash.beautiful.hdwallpaper.sample.api.Order;
import com.unsplash.beautiful.hdwallpaper.sample.models.Photo;
import com.unsplash.beautiful.hdwallpaper.sample.models.SearchResults;

import java.util.List;


public class CloudFragment extends Fragment {
    private Context context;
    private FragmentCloudBinding binding;
    private BroadcastReceiver receiver;
    private IntentFilter filter;

    private final String CLIENT_ID = "db95919f3ae25fa7428cf5ecf6b28bd056cc0ac0e3dda76a8fdc6d196fe91d65";
    private Unsplash unsplash;
    private PhotoRecyclerAdapter adapter;

    public static final String TAG = "CloudFragment";

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cloud, container, false);

        initViews();

        return binding.getRoot();
    }

    private void initViews() {
        unsplash = new Unsplash(CLIENT_ID);

        binding.rvCloud.setLayoutManager(new GridLayoutManager(context, 2));

        adapter = new PhotoRecyclerAdapter(context);
        binding.rvCloud.setAdapter(adapter);

        unsplash.getPhotos(1, 100, Order.POPULAR, new Unsplash.OnPhotosLoadedListener() {
            @Override
            public void onComplete(List<Photo> photos) {
                adapter.setPhotos(photos);
            }

            @Override
            public void onError(String error) {

            }
        });

        initReceiver();
    }

    public void search(String query){

        unsplash.searchPhotos(query, new Unsplash.OnSearchCompleteListener() {
            @Override
            public void onComplete(SearchResults results) {
                List<Photo> photos = results.getResults();
                Log.e(TAG, photos.size()+"");
                adapter.setPhotos(photos);
            }

            @Override
            public void onError(String error) {
            }
        });
    }

    private void initReceiver() {
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case "go_to_search":
                        String s = intent.getExtras().getString("what_keyword");
                        search(s);
                        break;
                    default:
                        break;
                }
            }
        };

        filter = new IntentFilter();
        filter.addAction("go_to_search");

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
