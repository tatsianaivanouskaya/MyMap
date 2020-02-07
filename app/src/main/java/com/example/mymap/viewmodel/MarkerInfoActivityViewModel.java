package com.example.mymap.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mymap.data.MarkerInfo;
import com.example.mymap.model.MarkerInfoActivityRepository;
import com.example.mymap.model.MarkerInfoActivityRepositoryInterface;

public class MarkerInfoActivityViewModel extends ViewModel implements MarkerInfoActivityRepositoryInterface {

    private MutableLiveData<MarkerInfo> markerInfoMutableLiveData;

    public LiveData<MarkerInfo> getData(String coordinates) {
        if(markerInfoMutableLiveData == null){
            MarkerInfoActivityRepository repository = MarkerInfoActivityRepository.getInstance();
            repository.setListener(this);
            repository.getMarkerInfo(coordinates);
        }

        //Log.d("GSON", markerInfoMutableLiveData.getValue().getCountry());

        return markerInfoMutableLiveData;
    }

    @Override
    public void loadMarkerInfo(MarkerInfo markerInfo) {
        markerInfoMutableLiveData = new MutableLiveData<>();
        markerInfoMutableLiveData.setValue(markerInfo);

        Log.d("GSON", markerInfoMutableLiveData.getValue().getCountry());


    }
}
