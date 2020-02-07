package com.example.mymap.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.mymap.data.MarkerInfo;
import com.example.mymap.model.MarkerInfoActivityRepository;


public class MarkerInfoActivityViewModel extends ViewModel {

    private MutableLiveData<MarkerInfo> markerInfo;

    public LiveData<MarkerInfo> getMarkerInfo(String coordinates) {
        MarkerInfoActivityRepository repository = MarkerInfoActivityRepository.getInstance();
        markerInfo = (MutableLiveData<MarkerInfo>) repository.getMarkerInfoMutableLiveData(coordinates);

        return markerInfo;
    }
}
