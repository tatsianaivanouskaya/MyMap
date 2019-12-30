package com.example.mymap.presenter;


import android.content.Context;

import java.io.IOException;

public class MarkerFromFile {

    private LoadFile loadFile;
    public void getMarkerFromFile(final Context context){

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    loadFile = new LoadFile();
                    loadFile.downloadFile(context);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();



    }

}



