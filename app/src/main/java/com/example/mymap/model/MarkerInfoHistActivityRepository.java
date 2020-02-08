package com.example.mymap.model;

import android.content.Context;
import com.example.mymap.data.MarkerInfo;
import com.example.mymap.data.db.MarkerInfoGetAsyncTask;
import java.util.concurrent.ExecutionException;

public class MarkerInfoHistActivityRepository {

    private static MarkerInfoHistActivityRepository instance;

    public static MarkerInfoHistActivityRepository getInstance(){
        if(instance == null){
            instance = new MarkerInfoHistActivityRepository();
        }
        return instance;
    }

    public MarkerInfo getMarker(Context context, int id){
        MarkerInfo markerInfo = null;
        try {
            markerInfo = new MarkerInfoGetAsyncTask(context, id).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return markerInfo;
    }
}
