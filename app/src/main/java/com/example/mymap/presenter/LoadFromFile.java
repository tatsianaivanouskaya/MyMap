package com.example.mymap.presenter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

public class LoadFromFile extends AppCompatActivity {

    private static UserMarkerFragment fragment;
    private static final int FILE_SELECT_CODE = 1;
    private String path = null;

    public interface UserMarkerFragment {
        void getMarkerFromFile(MarkerOptions markerOptions);
    }

    public static void attachFragment(UserMarkerFragment userMarkerFragment) {
        fragment = userMarkerFragment;
    }

    public static void detachFragment() {
        fragment = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        Intent intentChooser = Intent.createChooser(intent, "Select a File to Upload");
        startActivityForResult(intentChooser, FILE_SELECT_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {

                    Uri uri = data.getData();

                    try {
                        path = FileUtils.getPath(this, uri);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    MarkerOptions markerOptions = getMarkerFromFile();
                    fragment.getMarkerFromFile(markerOptions);
                } else{
                    Toast.makeText(this, "No selected files", Toast.LENGTH_SHORT).show();
                }
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
        finish();
    }

    public MarkerOptions getMarkerFromFile() {

        File myFile = new File(path);

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(myFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        while (true) {
            try {
                if (!((line = bufferedReader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            stringBuilder.append(line);
        }

        Gson gson = new Gson();
        LatLong latLong = gson.fromJson(stringBuilder.toString(), LatLong.class);
        LatLng newMarker = new LatLng(latLong.getLatitude(), latLong.getLongitude());

        return new MarkerOptions().position(newMarker);


    }
}
