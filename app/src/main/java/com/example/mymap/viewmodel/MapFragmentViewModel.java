package com.example.mymap.viewmodel;

import android.view.View;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.mymap.R;
import com.example.mymap.model.MapFragmentRepository;
import com.example.mymap.data.local.LatLong;
import com.example.mymap.model.MapFragmentRepositoryInterface;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import java.util.ArrayList;

public class MapFragmentViewModel extends ViewModel {

    public MapFragmentRepositoryInterface mapFragmentViewModelListener;
    public MutableLiveData<MarkerOptions> markerOptionsLiveData = new MutableLiveData();

    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.btn_loadfile:
                mapFragmentViewModelListener.loadMarkerFromFile();
                break;
            case R.id.btn_random:
                LiveData<ArrayList<MarkerOptions>> arrayListLiveData = new MapFragmentRepository().getArrayMarkerOptions();
                mapFragmentViewModelListener.loadRandomMarkers(arrayListLiveData);
                break;
        }
    }

    public MarkerOptions getMarkerFromCoordinates(String coordinatesForMarker) {
        Gson gson = new Gson();
        LatLong latLong = gson.fromJson(coordinatesForMarker, LatLong.class);
        LatLng newMarker = new LatLng(latLong.getLatitude(), latLong.getLongitude());

        return new MarkerOptions().position(newMarker);
    }
    public void getCoordinates(String coordinates){
        markerOptionsLiveData.setValue(getMarkerFromCoordinates(coordinates));
        mapFragmentViewModelListener.loadMarkerFromFile(markerOptionsLiveData);
    }
}
