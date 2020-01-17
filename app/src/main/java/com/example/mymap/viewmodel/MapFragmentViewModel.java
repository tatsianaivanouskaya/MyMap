package com.example.mymap.viewmodel;


import android.view.View;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.mymap.R;
import com.example.mymap.data.repositories.MapFragmentRepository;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;


public class MapFragmentViewModel extends ViewModel {

    public MapFragmentViewModelInterface mapFragmentViewModelListener;


    public void onButtonClick(View view){
        switch (view.getId()) {
            case R.id.btn_loadfile:
                //
                break;
            case R.id.btn_random:
                LiveData<ArrayList<MarkerOptions>> arrayListLiveData =  new MapFragmentRepository().getArrayMarkerOptions();
                mapFragmentViewModelListener.loadRandomMarkers(arrayListLiveData);
                //
                break;
        }
    }



}
