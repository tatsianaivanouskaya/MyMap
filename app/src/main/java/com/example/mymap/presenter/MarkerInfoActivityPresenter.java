package com.example.mymap.presenter;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.example.mymap.model.MarkerInfoModel;
import com.example.mymap.weather.DailyForecast;
import com.example.mymap.weather.Headline;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarkerInfoActivityPresenter {

    static final String API_KEY = "FsX0WaMXND5ohbnjGGcwCe6zsm0iPYHH";

    private MarkerInfo markerInfo;
    private UserMarkerActivity view;
    private MarkerInfoModel markerInfoModel;
    Context context;

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
                        .enqueue(new Callback<com.example.mymap.location.Example>() {
                            @Override
                            public void onResponse(Call<com.example.mymap.location.Example> call, Response<com.example.mymap.location.Example> response) {
                                try{
                                    com.example.mymap.location.Example example = response.body();
                                    markerInfo = new MarkerInfo();
                                    markerInfo.setLatitude(latitude);
                                    markerInfo.setLongitude(longitude);
                                    markerInfo.setKey(example.getKey());
                                    markerInfo.setCountry(example.getCountry().getLocalizedName());
                                    markerInfo.setAdministrativeArea(example.getAdministrativeArea().getLocalizedName());
                                }catch (Exception e){
                                    Toast.makeText(context, "Нет данных, попробуйте другую точку ;)", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<com.example.mymap.location.Example> call, @NonNull Throwable t) {
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
                        .enqueue(new Callback<com.example.mymap.weather.Example>() {
                            @Override
                            public void onResponse(Call<com.example.mymap.weather.Example> call, Response<com.example.mymap.weather.Example> response) {

                                com.example.mymap.weather.Example example = response.body();
                                Headline headline = example.getHeadline();
                                List<DailyForecast> dailyForecasts = example.getDailyForecasts();
                                DailyForecast dailyForecast = dailyForecasts.get(0);
                                markerInfo.setDateWeather(headline.getEffectiveDate());
                                markerInfo.setMaxTemp(dailyForecast.getTemperature().getMaximum().getValue());
                                markerInfo.setMinTemp(dailyForecast.getTemperature().getMinimum().getValue());
                                markerInfo.setDayIconPhrase(dailyForecast.getDay().getIconPhrase());
                                markerInfo.setNightIconPhrase(dailyForecast.getNight().getIconPhrase());

                                view.getMarker(markerInfo);
                            }

                            @Override
                            public void onFailure(@NonNull Call<com.example.mymap.weather.Example> call, @NonNull Throwable t) {

                                t.printStackTrace();
                            }

                });
            }
        }, 2000);

     }
}
