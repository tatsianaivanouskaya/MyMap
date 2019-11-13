package com.example.mymap.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.mymap.presenter.MarkerInfo;

@Database(entities = {MarkerInfo.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MarkerInfoDao markerInfoDao();

}
