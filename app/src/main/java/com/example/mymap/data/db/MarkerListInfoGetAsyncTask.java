package com.example.mymap.data.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import com.example.mymap.data.MarkerInfo;

import java.util.List;

public class MarkerListInfoGetAsyncTask extends AsyncTask<Void, Void, List<MarkerInfo>> {

    private Context context;

    public MarkerListInfoGetAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected List<MarkerInfo> doInBackground(Void... params) {

        MarkerInfoDao markerInfoDao = Room.databaseBuilder(context, AppDatabase.class, "database").build().markerInfoDao();
        List<MarkerInfo> markerInfoList = markerInfoDao.getAll();

        return markerInfoList;
    }
}
