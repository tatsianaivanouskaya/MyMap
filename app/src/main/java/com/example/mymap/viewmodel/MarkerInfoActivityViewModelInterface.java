package com.example.mymap.viewmodel;

import androidx.lifecycle.LiveData;

import com.example.mymap.data.MarkerInfo;

public interface MarkerInfoActivityViewModelInterface {
    public void getMarkerInfo(LiveData<MarkerInfo> markerInfoLiveData);
}
