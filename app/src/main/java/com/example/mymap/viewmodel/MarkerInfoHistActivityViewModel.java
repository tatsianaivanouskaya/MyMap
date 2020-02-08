package com.example.mymap.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.mymap.data.MarkerInfo;
import com.example.mymap.model.MarkerInfoHistActivityRepository;

public class MarkerInfoHistActivityViewModel extends AndroidViewModel {

    private MutableLiveData<MarkerInfo> markerInfoMutableLiveData;

    public MarkerInfoHistActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<MarkerInfo> getMarkerInfoMutableLiveData(int position) {
        if (markerInfoMutableLiveData == null){
            markerInfoMutableLiveData = new MutableLiveData<>();
            markerInfoMutableLiveData.setValue(MarkerInfoHistActivityRepository
                    .getInstance()
                    .getMarker(getApplication().getApplicationContext(),position));

        }
        return markerInfoMutableLiveData;
    }
}
