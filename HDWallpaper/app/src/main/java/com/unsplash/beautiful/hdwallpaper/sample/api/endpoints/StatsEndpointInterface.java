package com.unsplash.beautiful.hdwallpaper.sample.api.endpoints;

import com.unsplash.beautiful.hdwallpaper.sample.models.Stats;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StatsEndpointInterface {

    @GET("stats/total")
    Call<Stats> getStats();

}
