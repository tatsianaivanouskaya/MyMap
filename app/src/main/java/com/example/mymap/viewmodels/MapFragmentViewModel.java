package com.example.mymap.viewmodels;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.mymap.presenter.LoadFromFile;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapFragmentViewModel extends AndroidViewModel {

    private MarkerOptions markerOptions;
    private ArrayList<MarkerOptions> markerOptionsArrayList;
    private Application mApplication;

    public MapFragmentViewModel(@NonNull Application application) {
        super(application);
        mApplication = application;
    }


    public void getMarkerFromFile(){
        Intent intent = new Intent(mApplication.getBaseContext(), LoadFromFile.class);
        mApplication.getBaseContext().startActivity(intent);





    }





}
