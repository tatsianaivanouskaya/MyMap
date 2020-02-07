package com.example.mymap.data.network;

import com.example.mymap.data.network.weather.Example;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PlaceHolderWeather {

        @GET("{url}")
        Call<Example> getHeadline(@Path("url") String url, @Query("apikey") String apikey, @Query("language") String language, @Query("metric") boolean metric);

    }
