package com.example.mymap.presenter;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;

public class MapFagmentPresenter {

    public static ArrayList<MarkerOptions> getMarkerRandom(){
        ArrayList<MarkerOptions> markers = new ArrayList<>();
        for (int i = 0; i < 15; i++){
            Double latitude = (Math.floor(((Math.random()* (90-(-90)))+(-90))*100000))/100000;
            Double longitude = (Math.floor(((Math.random()* (180-(-180)))+(-180))*100000))/100000;
            LatLng point = new LatLng(latitude, longitude);
            markers.add(new MarkerOptions().position(point));
        }
        return markers;
    }


}
