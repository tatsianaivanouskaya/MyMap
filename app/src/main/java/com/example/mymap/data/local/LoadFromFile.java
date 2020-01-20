package com.example.mymap.data.local;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import com.example.mymap.presenter.FileUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoadFromFile extends AppCompatActivity {

    public static final int FILE_SELECT_CODE = 1;
    private String path = null;

    private static LoaderFromFile loader;

    public interface LoaderFromFile{
        void loadFromFile(String coordinates);
    }
    public static void attachToLoader(LoaderFromFile loaderFromFile){
        loader = loaderFromFile;
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
                    loader.loadFromFile(getMarkerFromFile());
                } else{
                    Toast.makeText(this, "No selected files", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
        finish();
    }

    public String getMarkerFromFile() {

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
        String coordinates = stringBuilder.toString();

        return coordinates;
    }
}
