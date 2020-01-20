package com.example.mymap.viewmodel;

import androidx.lifecycle.LiveData;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
public interface MapFragmentViewModelInterface {

    void loadRandomMarkers(LiveData<ArrayList<MarkerOptions>> arrayListMarkerOptions);
    void loadMarkerFromFile();
    void loadMarkerFromFile(LiveData<MarkerOptions> markerOptionsLiveData);
}
