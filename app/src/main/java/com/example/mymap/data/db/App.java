package com.example.mymap.data.db;

import android.app.Application;
import androidx.room.Room;

public class App extends Application {

    public static App instance;

    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "markerinfodatabase")
                .allowMainThreadQueries()
                .build();
    }


}
