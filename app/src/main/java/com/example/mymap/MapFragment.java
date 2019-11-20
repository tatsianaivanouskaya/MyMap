package com.example.mymap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.mymap.presenter.MapFagmentPresenter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;

public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener {

    private static MapFragment mfInstance;
    private GoogleMap mMap;
    private Button btnLoadFile;
    private Button btnRandom;

    public MapFragment() { }

    static MapFragment newInstance(){
        if (mfInstance == null){
            mfInstance = new MapFragment();
        }
        return mfInstance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewInflater = inflater.inflate(R.layout.fragment_map, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        btnLoadFile = viewInflater.findViewById(R.id.btn_loadfile);
        btnRandom = viewInflater.findViewById(R.id.btn_random);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_loadfile:
                        //
                        break;
                    case R.id.btn_random:
                        ArrayList<MarkerOptions> markerOptionsArrayList = MapFagmentPresenter.getMarkerRandom();
                        mMap.clear();
                        for (int i = 0; i < markerOptionsArrayList.size(); i++){
                            mMap.addMarker(markerOptionsArrayList.get(i));
                        }
                        break;
                }
            }
        };
        btnLoadFile.setOnClickListener(onClickListener);
        btnRandom.setOnClickListener(onClickListener);

        return viewInflater;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMarkerDragListener(this);
        LatLng minsk = new LatLng(53.902742, 27.561491);
        mMap.addMarker(new MarkerOptions().position(minsk).draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(minsk));


    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        Context context = getActivity();
        Double markerLatitude = marker.getPosition().latitude;
        Double markerLongitude = marker.getPosition().longitude;
        Intent intent = new Intent(context,MarkerInfoActivity.class);
        intent.putExtra(MarkerInfoActivity.MARKER_LATITUDE, markerLatitude);
        intent.putExtra(MarkerInfoActivity.MARKER_LONGITUDE, markerLongitude);
        startActivity(intent);

        return true;
    }


    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }
}
