package com.example.mymap.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.mymap.data.MarkerInfo;

@Database(entities = {MarkerInfo.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MarkerInfoDao markerInfoDao();

}
