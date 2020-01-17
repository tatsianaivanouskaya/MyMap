package com.example.mymap.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymap.MarkerInfoActivity;
import com.example.mymap.R;
import com.example.mymap.databinding.FragmentMapBinding;
import com.example.mymap.viewmodel.MapFragmentViewModel;
import com.example.mymap.viewmodel.MapFragmentViewModelInterface;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapFragment extends Fragment implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener, MapFragmentViewModelInterface {

    private static MapFragment mfInstance;
    private GoogleMap mMap;

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


        FragmentMapBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false);
        MapFragmentViewModel mapFragmentViewModel = ViewModelProviders.of(this).get(MapFragmentViewModel.class);
        mapFragmentViewModel.mapFragmentViewModelListener = this;
        binding.setMapfragmentviewmodel(mapFragmentViewModel);

        final SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        return binding.getRoot();

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
        Intent intent = new Intent(context, MarkerInfoActivity.class);
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

    @Override
    public void loadRandomMarkers(final LiveData<ArrayList<MarkerOptions>> arrayListMarkerOptions) {
        arrayListMarkerOptions.observe(this, new Observer<ArrayList<MarkerOptions>>() {
            @Override
            public void onChanged(ArrayList<MarkerOptions> markerOptions) {
                ArrayList<MarkerOptions> arrayList = markerOptions;
                mMap.clear();
                for(int i = 0; i < arrayList.size(); i++){
                    mMap.addMarker(arrayList.get(i));
            }
        }


    });
    }
}
