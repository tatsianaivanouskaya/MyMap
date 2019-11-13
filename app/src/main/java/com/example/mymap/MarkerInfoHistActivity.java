package com.example.mymap;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.mymap.presenter.MarkerInfo;

public class MarkerInfoHistActivity extends AppCompatActivity {

    TextView tvLatitude;
    TextView tvLongitude;
    TextView tvCountry;
    TextView tvCity;
    TextView tvDate;
    TextView tvMaxTemp;
    TextView tvMaxTempText;
    TextView tvMinTemp;
    TextView tvMinTempText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker_info);
        tvLatitude = findViewById(R.id.tv_latitude);
        tvLongitude = findViewById(R.id.tv_longitude);
        tvCountry = findViewById(R.id.tv_country);
        tvCity = findViewById(R.id.tv_city);
        tvDate = findViewById(R.id.tv_date);
        tvMaxTemp = findViewById(R.id.tv_maxTemp);
        tvMaxTempText = findViewById(R.id.tv_maxTempText);
        tvMinTemp = findViewById(R.id.tv_minTemp);
        tvMinTempText = findViewById(R.id.tv_minTempText);

        MarkerInfo markerInfo = (MarkerInfo)getIntent().getSerializableExtra("Editing");
        tvLatitude.setText(markerInfo.getLatitude().toString());
        tvLongitude.setText(markerInfo.getLongitude().toString());
        tvCountry.setText(markerInfo.getCountry());
        tvCity.setText(markerInfo.getAdministrativeArea());
        tvDate.setText("Погода на " + markerInfo.getDateWeather());
        tvMaxTemp.setText(markerInfo.getMaxTemp().toString());
        tvMaxTempText.setText(markerInfo.getDayIconPhrase());
        tvMinTemp.setText(markerInfo.getMinTemp().toString());
        tvMinTempText.setText(markerInfo.getNightIconPhrase());
    }
}
