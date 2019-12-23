package com.example.mymap;


import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import com.example.mymap.databinding.ActivityMarkerInfoBinding;
import com.example.mymap.presenter.MarkerInfo;
import com.example.mymap.presenter.MarkerInfoActivityPresenter;


public class MarkerInfoActivity extends AppCompatActivity implements MarkerInfoActivityPresenter.UserMarkerActivity {

    public static final String MARKER_LATITUDE = "latitude";
    public static final String MARKER_LONGITUDE = "longitude";

    MarkerInfo markInfo;
    MarkerInfoActivityPresenter markerInfoActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_bar);

        final Double latitude = getIntent().getDoubleExtra(MARKER_LATITUDE, 0.0);
        final Double longitude = getIntent().getDoubleExtra(MARKER_LONGITUDE, 0.0);

        markerInfoActivityPresenter = new MarkerInfoActivityPresenter(this);
        markerInfoActivityPresenter.attachView(this);
        markerInfoActivityPresenter.getMarkerInfo(latitude, longitude);
    }

    @Override
    public void getMarker(MarkerInfo markerInfo) {
        markInfo = markerInfo;
        ActivityMarkerInfoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_marker_info);
        binding.setMarkerinfo(markInfo);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        markerInfoActivityPresenter.detachView();
    }
}
