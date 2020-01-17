package com.example.mymap.data.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mymap.data.local.MarkerOptionsRandom;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapFragmentRepository {

    public LiveData<ArrayList<MarkerOptions>> getArrayMarkerOptions(){
        MutableLiveData<ArrayList<MarkerOptions>> arrayMarkerOptions = new MutableLiveData<>();
        arrayMarkerOptions.setValue(MarkerOptionsRandom.getMarkerOptionsRandom());
        return  arrayMarkerOptions;

    }
}
