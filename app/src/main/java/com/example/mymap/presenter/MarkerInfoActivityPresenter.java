package com.example.mymap.presenter;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;
import androidx.annotation.NonNull;

import com.example.mymap.data.network.NetworkServiceLocation;
import com.example.mymap.data.network.NetworkServiceWeather;
import com.example.mymap.data.MarkerInfo;
import com.example.mymap.model.MarkerInfoModel;
import com.example.mymap.data.network.weather.DailyForecast;
import com.example.mymap.data.network.weather.Headline;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarkerInfoActivityPresenter {

    private static final String API_KEY = "48sdKWGJxkOk3LmcLM652cuyLolAZAf6";

    private MarkerInfo markerInfo;
    private UserMarkerActivity view;
    private MarkerInfoModel markerInfoModel;
    private Context context;

    public MarkerInfoActivityPresenter(Context context) {
        this.context = context;
    }


    public interface UserMarkerActivity{
        void getMarker(MarkerInfo markerInfo);
    }
    public void attachView(UserMarkerActivity userMarkerActivity){
        view = userMarkerActivity;
    }
    public void detachView(){
        view = null;
    }

    public void getMarkerInfo(final Double latitude, final Double longitude) {

         final String latlong = latitude + "," + longitude;
         markerInfoModel = new MarkerInfoModel();

                NetworkServiceLocation.getInstance()
                        .getJSONApi()
                        .getKey(API_KEY,latlong, "ru-ru", true)
                        .enqueue(new Callback<com.example.mymap.data.network.location.Example>() {
                            @Override
                            public void onResponse(Call<com.example.mymap.data.network.location.Example> call, Response<com.example.mymap.data.network.location.Example> response) {
                                try{
                                    com.example.mymap.data.network.location.Example example = response.body();
                                    markerInfo = new MarkerInfo();
                                    markerInfo.setLatitude(latitude.toString());
                                    markerInfo.setLongitude(longitude.toString());
                                    markerInfo.setKey(example != null ? example.getKey() : null);
                                    markerInfo.setCountry(example != null ? example.getCountry().getLocalizedName() : null);
                                    markerInfo.setAdministrativeArea(example != null ? example.getAdministrativeArea().getLocalizedName() : null);
                                }catch (Exception e){
                                    Toast.makeText(context, "Нет данных, попробуйте другую точку ;)", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<com.example.mymap.data.network.location.Example> call, @NonNull Throwable t) {
                                t.printStackTrace();
                            }
                        });



        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                NetworkServiceWeather.getInstance()
                        .getJSONApi()
                        .getHeadline(markerInfo.getKey(),API_KEY,"ru-ru", true)
                        .enqueue(new Callback<com.example.mymap.data.network.weather.Example>() {
                            @Override
                            public void onResponse(Call<com.example.mymap.data.network.weather.Example> call, Response<com.example.mymap.data.network.weather.Example> response) {

                                com.example.mymap.data.network.weather.Example example = response.body();
                                Headline headline = example != null ? example.getHeadline() : null;
                                List<DailyForecast> dailyForecasts = example != null ? example.getDailyForecasts() : null;
                                DailyForecast dailyForecast = dailyForecasts != null ? dailyForecasts.get(0) : null;
                                markerInfo.setDateWeather(headline != null ? headline.getEffectiveDate() : null);
                                markerInfo.setMaxTemp(dailyForecast != null ? dailyForecast.getTemperature().getMaximum().getValue().toString() : null);
                                markerInfo.setMinTemp(dailyForecast != null ? dailyForecast.getTemperature().getMinimum().getValue().toString() : null);
                                markerInfo.setDayIconPhrase(dailyForecast != null ? dailyForecast.getDay().getIconPhrase() : null);
                                markerInfo.setNightIconPhrase(dailyForecast != null ? dailyForecast.getNight().getIconPhrase() : null);
                                MarkerInfoModel markerInfoModel = new MarkerInfoModel();
                                markerInfoModel.setMarker(markerInfo, context);
                                view.getMarker(markerInfo);
                            }

                            @Override
                            public void onFailure(@NonNull Call<com.example.mymap.data.network.weather.Example> call, @NonNull Throwable t) {

                                t.printStackTrace();
                            }

                });
            }
        }, 2000);


     }
}
