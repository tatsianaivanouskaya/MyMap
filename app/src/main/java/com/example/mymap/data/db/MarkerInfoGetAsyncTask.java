package com.example.mymap.data.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import com.example.mymap.data.MarkerInfo;

public class MarkerInfoGetAsyncTask extends AsyncTask<Void, Void, MarkerInfo> {

    private Context context;
    int id;
    MarkerInfo markerInfo;

    public MarkerInfoGetAsyncTask(Context context, int id) {
        this.context = context;
        this.id = id;
    }

    @Override
    protected MarkerInfo doInBackground(Void... params) {
        MarkerInfoDao markerInfoDao = Room.databaseBuilder(context,
                AppDatabase.class, "database").build().markerInfoDao();
        markerInfo = markerInfoDao.getById(id);

        return markerInfo;
    }
}
