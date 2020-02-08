package com.example.mymap.data.db;

import android.content.Context;
import android.os.AsyncTask;
import androidx.room.Room;
import com.example.mymap.data.MarkerInfo;

public class MarkerLoadAsyncTask extends AsyncTask<Void, Void, Integer> {

    private Context context;
    MarkerInfo markerInfo;

    public MarkerLoadAsyncTask(Context context, MarkerInfo markerInfo) {
        this.context = context;
        this.markerInfo = markerInfo;
    }

    @Override
    protected Integer doInBackground(Void... params) {
        MarkerInfoDao markerInfoDao = Room.databaseBuilder(context,
                AppDatabase.class, "database").build().markerInfoDao();
        markerInfoDao.insert(markerInfo);
        return null;
    }
}
