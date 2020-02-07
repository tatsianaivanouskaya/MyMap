package com.example.mymap.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mymap.R;
import com.example.mymap.data.local.LoadFromFile;
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
        GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener, MapFragmentViewModelInterface,LoadFromFile.LoaderFromFile {

    private static MapFragment mfInstance;
    private GoogleMap mMap;
    private MapFragmentViewModel mapFragmentViewModel;
    private static final String KEY_MARKER = "key_marker";

    private boolean hasArrayList = false;
    private ArrayList<MarkerOptions> arrayList;
    private LatLng marker = new LatLng(53.902742, 27.561491);



    public MapFragment() {
    }

    static MapFragment newInstance() {
        if (mfInstance == null) {
            mfInstance = new MapFragment();
        }
        return mfInstance;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LoadFromFile.attachToLoader(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            if(savedInstanceState.getBoolean("hasArrayList")){
                arrayList = (ArrayList)savedInstanceState.getSerializable(KEY_MARKER);
            } else {
                marker = new LatLng(savedInstanceState.getDouble("latitude"), savedInstanceState.getDouble("longitude"));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentMapBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false);
        mapFragmentViewModel = ViewModelProviders.of(this).get(MapFragmentViewModel.class);
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
        if (hasArrayList) {
            mMap.clear();
            for (int i = 0; i < arrayList.size(); i++) {
                mMap.addMarker(arrayList.get(i));
            }
        } else {
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(marker).draggable(true));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
            hasArrayList = false;
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        String coordinates = marker.getPosition().latitude + "," + marker.getPosition().longitude;
        Intent intent = new Intent(getActivity(), MarkerInfoActivity.class);
        intent.putExtra(MarkerInfoActivity.MARKER_LATLONG, coordinates);
        Log.d("GSON", coordinates);
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
        this.marker = marker.getPosition();

    }

    @Override
    public void loadRandomMarkers(final LiveData<ArrayList<MarkerOptions>> arrayListMarkerOptions) {
        arrayListMarkerOptions.observe(this, new Observer<ArrayList<MarkerOptions>>() {
            @Override
            public void onChanged(ArrayList<MarkerOptions> markerOptions) {
                arrayList = markerOptions;
                mMap.clear();
                for (int i = 0; i < arrayList.size(); i++) {
                    mMap.addMarker(arrayList.get(i));
                }
                hasArrayList = true;
            }
        });
    }

    @Override
    public void loadFromFile(String coordinates) {
        mapFragmentViewModel.getCoordinates(coordinates);
    }

    @Override
    public void loadMarkerFromFile() {
        Context context = getActivity();
        Intent intent = new Intent(context, LoadFromFile.class);
        startActivity(intent);
    }

    @Override
    public void loadMarkerFromFile(LiveData<MarkerOptions> markerOptionsLiveData) {
        markerOptionsLiveData.observe(this, new Observer<MarkerOptions>() {
            @Override
            public void onChanged(MarkerOptions markerOptions) {
                mMap.clear();
                marker = new LatLng(markerOptions.getPosition().latitude, markerOptions.getPosition().longitude);
                mMap.addMarker(markerOptions.draggable(true));

                hasArrayList = false;
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(hasArrayList){
            outState.putBoolean("hasArrayList", hasArrayList);
            outState.putSerializable(KEY_MARKER, arrayList);
        } else{
            outState.putDouble("latitude", marker.latitude);
            outState.putDouble("longitude", marker.longitude);
        }
    }


}
