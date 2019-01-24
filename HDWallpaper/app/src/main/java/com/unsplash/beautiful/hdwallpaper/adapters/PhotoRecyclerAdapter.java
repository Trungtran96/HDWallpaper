package com.unsplash.beautiful.hdwallpaper.adapters;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.unsplash.beautiful.hdwallpaper.R;
import com.unsplash.beautiful.hdwallpaper.dialogs.LoadingDialog;
import com.unsplash.beautiful.hdwallpaper.sample.models.Photo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PhotoRecyclerAdapter extends RecyclerView.Adapter<PhotoRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<Photo> photos;

    public PhotoRecyclerAdapter(Context context) {
        this.context = context;
        photos = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Photo photo = photos.get(position);

        Picasso.get().load(photo.getUrls().getSmall()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View view) {
                final LoadingDialog dialog = new LoadingDialog(context);

                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        dialog.show();
                    }

                    @Override
                    protected Void doInBackground(Void... voids) {
                        try {
                            URL url = new URL(photo.getLinks().getDownload());
                            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                            WallpaperManager myWallpaperManager = WallpaperManager.getInstance(context);
                            myWallpaperManager.setBitmap(bitmap);

                        } catch(Exception e) {

                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        dialog.dismiss();
                    }
                }.execute();

            }
        });
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageView);
        }

    }
}
