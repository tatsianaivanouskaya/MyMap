package com.example.mymap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import com.example.mymap.databinding.ActivityMarkerInfoBinding;
import com.example.mymap.presenter.MarkerInfo;

public class MarkerInfoHistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MarkerInfo markerInfo = (MarkerInfo)getIntent().getSerializableExtra("Editing");
        ActivityMarkerInfoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_marker_info);
        binding.setMarkerinfo(markerInfo);
    }
}
