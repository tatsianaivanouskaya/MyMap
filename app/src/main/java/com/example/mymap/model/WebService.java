package com.example.mymap.model;

import android.util.Log;
import com.example.mymap.data.MarkerInfo;
import com.example.mymap.data.network.NetworkServiceLocation;
import com.example.mymap.data.network.NetworkServiceWeather;
import com.example.mymap.data.network.location.Example;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebService {

    private static final String API_KEY = "48sdKWGJxkOk3LmcLM652cuyLolAZAf6";
    private static WebService instance;
    private MarkerInfo markerInfo = new MarkerInfo();
    private MarkerInfoActivityRepositoryInterface listener;

    public void setListener(MarkerInfoActivityRepositoryInterface listener){
        this.listener = listener;
    }

    public static WebService getInstance(){
        if (instance == null){
            instance = new WebService();
        }
        return instance;
    }
    public void getMarker(String coordinates){
        NetworkServiceLocation.getInstance()
                .getJSONApi()
                .getKey(API_KEY, coordinates, "ru-ru", false)
                .enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call,
                                           Response<Example> response) {

                        com.example.mymap.data.network.location.Example example = response.body();
                        markerInfo.setLatitude(example.getGeoPosition().getLatitude().toString());
                        markerInfo.setLongitude(example.getGeoPosition().getLongitude().toString());
                        markerInfo.setKey(example.getKey());
                        markerInfo.setCountry(example.getCountry().getLocalizedName());
                        markerInfo.setAdministrativeArea(example.getAdministrativeArea().getLocalizedName());

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
