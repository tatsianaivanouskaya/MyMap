package com.example.mymap.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.mymap.data.MarkerInfo;

public class MarkerInfoActivityRepository implements MarkerInfoActivityRepositoryInterface{

    private MutableLiveData<MarkerInfo> markerInfoMutableLiveData = new MutableLiveData<>();
    private static MarkerInfoActivityRepository instance;

    public LiveData<MarkerInfo> getMarkerInfoMutableLiveData(String coordinates) {
        WebService webService = WebService.getInstance();
        webService.setListener(this);
        webService.getMarker(coordinates);
        return markerInfoMutableLiveData;
    }

    public static MarkerInfoActivityRepository getInstance(){
        if(instance == null){
            instance = new MarkerInfoActivityRepository();
        }
        return instance;
    }

    @Override
    public void loadMarkerInfo(MarkerInfo markerInfo) {
        markerInfoMutableLiveData.setValue(markerInfo);
    }
}
