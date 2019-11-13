package com.example.mymap;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymap.model.MarkerInfoModel;
import com.example.mymap.presenter.MarkerInfo;
import com.example.mymap.presenter.MarkerInfoActivityPresenter;


public class MarkerInfoActivity extends AppCompatActivity implements MarkerInfoActivityPresenter.UserMarkerActivity {

    public static final String MARKER_LATITUDE = "latitude";
    public static final String MARKER_LONGITUDE = "longitude";

    TextView tvLatitude;
    TextView tvLongitude;
    TextView tvCountry;
    TextView tvCity;
    TextView tvDate;
    TextView tvMaxTemp;
    TextView tvMaxTempText;
    TextView tvMinTemp;
    TextView tvMinTempText;
    MarkerInfo markInfo;
    MarkerInfoModel markerInfoModel;
    MarkerInfoActivityPresenter markerInfoActivityPresenter;

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

        final Double latitude = getIntent().getDoubleExtra(MARKER_LATITUDE, 0.0);
        final Double longitude = getIntent().getDoubleExtra(MARKER_LONGITUDE, 0.0);

        markerInfoActivityPresenter = new MarkerInfoActivityPresenter(this);
        markerInfoActivityPresenter.attachView(this);
        markerInfoActivityPresenter.getMarkerInfo(latitude, longitude);





    }

    @Override
    public void getMarker(MarkerInfo markerInfo) {
        markInfo = markerInfo;
        markerInfoModel = new MarkerInfoModel();
        markerInfoModel.setMarker(markInfo, this);

        tvLatitude.setText(markInfo.getLatitude().toString());
        tvLongitude.setText(markInfo.getLongitude().toString());
        tvCountry.setText(markInfo.getCountry());
        tvCity.setText(markInfo.getAdministrativeArea());
        tvDate.setText("Погода на " + markerInfo.getDateWeather());
        tvMaxTemp.setText(markInfo.getMaxTemp().toString());
        tvMaxTempText.setText(markerInfo.getDayIconPhrase());
        tvMinTemp.setText(markInfo.getMinTemp().toString());
        tvMinTempText.setText(markInfo.getNightIconPhrase());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        markerInfoActivityPresenter.detachView();
    }
}
