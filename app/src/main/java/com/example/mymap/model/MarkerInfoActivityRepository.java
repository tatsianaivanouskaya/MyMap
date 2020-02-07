package com.example.mymap.model;

import android.os.Handler;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.mymap.data.MarkerInfo;
import com.example.mymap.data.network.NetworkServiceLocation;
import com.example.mymap.data.network.NetworkServiceWeather;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarkerInfoActivityRepository {

    private static final String API_KEY = "48sdKWGJxkOk3LmcLM652cuyLolAZAf6";
    private MarkerInfo markerInfo = new MarkerInfo();
    private MarkerInfoActivityRepositoryInterface listener;
    private static MarkerInfoActivityRepository instance;

    public void setListener(MarkerInfoActivityRepositoryInterface listener){
        this.listener = listener;
    }

    public static MarkerInfoActivityRepository getInstance(){
        if(instance == null){
            instance = new MarkerInfoActivityRepository();
        }
        return instance;
    }


    public void getMarkerInfo (String coordinates) {
        Log.d("GSON", "7");

            NetworkServiceLocation.getInstance()
                    .getJSONApi()
                    .getKey(API_KEY, coordinates, "ru-ru", false)
                    .enqueue(new Callback<com.example.mymap.data.network.location.Example>() {
                        @Override
                        public void onResponse(Call<com.example.mymap.data.network.location.Example> call,
                                               Response<com.example.mymap.data.network.location.Example> response) {

                            com.example.mymap.data.network.location.Example example = response.body();
                            markerInfo.setLatitude(example.getGeoPosition().getLatitude().toString());
                            markerInfo.setLongitude(example.getGeoPosition().getLongitude().toString());
                            markerInfo.setKey(example.getKey());
                            markerInfo.setCountry(example.getCountry().getLocalizedName());
                            markerInfo.setAdministrativeArea(example.getAdministrativeArea().getLocalizedName());
                            Log.d("GSON", "7-1");


                                NetworkServiceWeather.getInstance()
                                        .getJSONApi()
                                        .getHeadline(markerInfo.getKey(),API_KEY,"ru-ru", true)
                                        .enqueue(new Callback<com.example.mymap.data.network.weather.Example>() {
                                            @Override
                                            public void onResponse(Call<com.example.mymap.data.network.weather.Example> call,
                                                                   Response<com.example.mymap.data.network.weather.Example> response) {
                                                com.example.mymap.data.network.weather.Example example = response.body();
                                                markerInfo.setDateWeather(example.getHeadline().getEffectiveDate());
                                                markerInfo.setMaxTemp(example.getDailyForecasts().get(0)
                                                        .getTemperature().getMaximum().getValue().toString());
                                                markerInfo.setMinTemp(example.getDailyForecasts().get(0)
                                                        .getTemperature().getMinimum().getValue().toString());
                                                markerInfo.setDayIconPhrase(example.getDailyForecasts().get(0).getDay().getIconPhrase());
                                                markerInfo.setNightIconPhrase(example.getDailyForecasts().get(0).getNight().getIconPhrase());


                                                Log.d("GSON", "8 "  + markerInfo.toString() );
                                                listener.loadMarkerInfo(markerInfo);
                                            }

                                            @Override
                                            public void onFailure(Call<com.example.mymap.data.network.weather.Example> call, Throwable t) {

                                            }
                                        });
                            }
                        @Override
                        public void onFailure(Call<com.example.mymap.data.network.location.Example> call, Throwable t) {

                        }
                    });
            }
}
