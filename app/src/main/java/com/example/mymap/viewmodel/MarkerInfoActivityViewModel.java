package com.example.mymap.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.mymap.data.MarkerInfo;
import com.example.mymap.model.MarkerInfoActivityRepository;


public class MarkerInfoActivityViewModel extends AndroidViewModel {

    private MutableLiveData<MarkerInfo> markerInfo;
    private MarkerInfoActivityRepository repository = MarkerInfoActivityRepository.getInstance();

    public MarkerInfoActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<MarkerInfo> getMarkerInfo(String coordinates) {
        markerInfo = (MutableLiveData<MarkerInfo>) repository.getMarkerInfoMutableLiveData(coordinates);
        return markerInfo;
    }

    public void setMarker(MarkerInfo markerInfo){
        repository.setMarker(markerInfo, getApplication().getApplicationContext());
    }
}
