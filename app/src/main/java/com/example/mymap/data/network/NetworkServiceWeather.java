package com.example.mymap.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkServiceWeather {


    private static NetworkServiceWeather mInstance;
    private static final String BASE_URL ="http://dataservice.accuweather.com/forecasts/v1/daily/1day/";
    private Retrofit mRetrofit;

    private NetworkServiceWeather() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static NetworkServiceWeather getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkServiceWeather();
        }
        return mInstance;
    }
    public PlaceHolderWeather getJSONApi() {

        return mRetrofit.create(PlaceHolderWeather.class);
    }
}