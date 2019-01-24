package com.unsplash.beautiful.hdwallpaper.ultils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;

import com.unsplash.beautiful.hdwallpaper.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Ultils {

    public static final String TAG = "Ultils";

    public static int checkPermission(String[] permissions, Context context) {
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (String permission : permissions) {
            permissionCheck += ContextCompat.checkSelfPermission(context, permission);
        }
        return permissionCheck;
    }

//    public static ArrayList<Drawable> getPhotosLocal(Context context){
//        ArrayList<Drawable> arr = new ArrayList<>();
//
//        ArrayList<String> arrName = getListPhotosName();
//
//        for (String s : arrName){
//            try {
//                InputStream ims = context.getAssets().open(s);
//                Drawable d = Drawable.createFromStream(ims, null);
//                arr.add(d);
//            }
//            catch(IOException ex) {
//
//            }
//        }
//
//        return arr;
//    }

    public static ArrayList<Integer> getPhotos(){
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);
        arr.add(R.drawable.wall_4);

        return arr;
    }

    public static void rateApp(Context context) {
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }
}
