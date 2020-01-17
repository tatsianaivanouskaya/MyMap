package com.example.mymap.model;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mymap.data.local.LoadFromFile;
import com.google.android.gms.maps.model.MarkerOptions;


public class TestLoader extends AppCompatActivity implements LoadFromFile.LoaderFromFile {

    private static MarkerOptions markerOptions;

    public MarkerOptions getMarkerOptions() {
        return markerOptions;
    }
    public void loadMarkerFromFile(){
        Intent intent = new Intent(TestLoader.this, LoadFromFile.class);
        LoadFromFile.attachToLoader(this);
        startActivity(intent);
    }

    @Override
    public void loadFromFile(MarkerOptions markerOptions) {
        this.markerOptions = markerOptions;

    }
}
