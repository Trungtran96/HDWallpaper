package com.unsplash.beautiful.hdwallpaper.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.unsplash.beautiful.hdwallpaper.R;
import com.unsplash.beautiful.hdwallpaper.customs.SSTextView;
import com.unsplash.beautiful.hdwallpaper.databinding.ActivityMainBinding;
import com.unsplash.beautiful.hdwallpaper.dialogs.RateDialog;
import com.unsplash.beautiful.hdwallpaper.fragments.CloudFragment;
import com.unsplash.beautiful.hdwallpaper.fragments.LocalFragment;
import com.unsplash.beautiful.hdwallpaper.listeners.RateListener;
import com.unsplash.beautiful.hdwallpaper.ultils.SharedPreferencesManager;
import com.unsplash.beautiful.hdwallpaper.ultils.Ultils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ViewPagerAdapter viewPagerAdapter;

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor("#008577"));
        }

        initViews();
    }

    private void initViews() {
        setupViewpager();
        initTabLayout();

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (binding.vp.getCurrentItem()==0){
                    binding.vp.setCurrentItem(1, true);
                }
                Intent intent = new Intent("go_to_search");
                intent.putExtra("what_keyword", s);
                sendBroadcast(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        binding.searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.title.setVisibility(View.GONE);
            }
        });

        binding.searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                binding.title.setVisibility(View.VISIBLE);
                return false;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (!SharedPreferencesManager.getRate(MainActivity.this) && !SharedPreferencesManager.getAgain(MainActivity.this)) {
            RateDialog rateDialog = new RateDialog(MainActivity.this, new RateListener() {
                @Override
                public void onLaterClicked() {
                    finish();
                }

                @Override
                public void onRateClicked() {
                    finish();
                    Ultils.rateApp(MainActivity.this);
                }
            });
            rateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            rateDialog.getWindow().setWindowAnimations(R.style.zoom_2);
            rateDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            rateDialog.show();
        } else {
            finish();
        }
    }

    private void initTabLayout() {
        binding.tab.setTabMode(TabLayout.MODE_FIXED);
        binding.tab.setupWithViewPager(binding.vp);
        for (int i = 0; i < binding.tab.getTabCount(); i++) {
            binding.tab.getTabAt(i).setCustomView(viewPagerAdapter.getTabView(i));
        }
    }

    private void setupViewpager(){
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new LocalFragment(), "0");
        viewPagerAdapter.addFragment(new CloudFragment(), "1");

        binding.vp.setAdapter(viewPagerAdapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        public View getTabView(int position){
            ArrayList<String> arrTitle = new ArrayList<>();
            arrTitle.add("Local");
            arrTitle.add("Cloud");
            View tab = LayoutInflater.from(getApplicationContext()).inflate(R.layout.tab_layout_title, null);
            SSTextView tvTitle = tab.findViewById(R.id.tv_title);
            tvTitle.setText(arrTitle.get(position));

            return tab;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
