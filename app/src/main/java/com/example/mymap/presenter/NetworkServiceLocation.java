package com.example.mymap.presenter;

import com.example.mymap.data.network.PlaceHolderLocation;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NetworkServiceLocation {

    private static NetworkServiceLocation mInstance;
    private static final String BASE_URL ="http://dataservice.accuweather.com/locations/v1/cities/geoposition/";
    private Retrofit mRetrofit;

    private NetworkServiceLocation() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkServiceLocation getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkServiceLocation();
        }
        return mInstance;
    }

    public PlaceHolderLocation getJSONApi() {

        return mRetrofit.create(PlaceHolderLocation.class);
    }
}
