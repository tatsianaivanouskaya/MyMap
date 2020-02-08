package com.example.mymap.model;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.mymap.data.MarkerInfo;
import com.example.mymap.data.db.MarkerInfoGetAsyncTask;
import com.example.mymap.data.db.MarkerListInfoGetAsyncTask;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HistoryFragmentRepository {

    private static HistoryFragmentRepository instance;

    public static HistoryFragmentRepository getInstance(){
        if(instance == null){
            instance = new HistoryFragmentRepository();
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

    public LiveData<List<MarkerInfo>> getMarkerInfoList(Context context){
        MutableLiveData<List<MarkerInfo>> markerInfoList = new MutableLiveData<>();
        try {
            markerInfoList.setValue(new MarkerListInfoGetAsyncTask(context).execute().get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return markerInfoList;
    }
}
