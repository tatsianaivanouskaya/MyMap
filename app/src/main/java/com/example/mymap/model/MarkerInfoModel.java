package com.example.mymap.model;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import androidx.room.Room;
import com.example.mymap.presenter.MarkerInfo;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MarkerInfoModel extends Application{

    public void setMarker(MarkerInfo markerInfo, Context context){
        new MarkerLoadAsyncTask(context, markerInfo).execute();
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

    public List<MarkerInfo> getMarkerInfoList(Context context){
        List<MarkerInfo> markerInfoList = null;
        try {
            markerInfoList = new MarkerListInfoGetAsyncTask(context).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return markerInfoList;
    }

    private static class MarkerLoadAsyncTask extends AsyncTask<Void, Void, Integer> {

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

    private static class MarkerInfoGetAsyncTask extends AsyncTask<Void, Void, MarkerInfo> {

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

    private static class MarkerListInfoGetAsyncTask extends AsyncTask<Void, Void, List<MarkerInfo>> {

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

}
