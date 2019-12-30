package com.example.mymap.presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class LoadFile {

    public void downloadFile(Context context) throws IOException {


        try {
            URL url = new URL("https://www.dropbox.com/s/ge593nhe49ttw7q/Munich.txt?dl=0");
            BufferedReader read = new BufferedReader(
                    new InputStreamReader(url.openStream()));
            String i;
            while ((i = read.readLine()) != null)
                Log.d("GSON", "log0 " + i);
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


/*
        String url = "https://www.dropbox.com/s/ge593nhe49ttw7q/Munich.txt?dl=0";


        try {
            BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
            Log.d("GSON", "log1");

            FileOutputStream fileOutputStream = new FileOutputStream(new File(context.getFilesDir(), "Munich.txt"));
            Log.d("GSON", "log2");

            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            Log.d("GSON", "log3");

            in.close();
            fileOutputStream.close();

            InputStream is = new FileInputStream(new File(context.getFilesDir(),"Munich.txt"));
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
            String line = buf.readLine();
            Log.d("GSON", "load4" + line);

            StringBuilder sb = new StringBuilder();
            while(line != null){
                sb.append(line).append("\n");
                line = buf.readLine();
            }

            String fileAsString = sb.toString();
            Log.d("GSON", "load5 " + fileAsString);


            Gson gson = new Gson();
            LatLong latLong = gson.fromJson(fileAsString, LatLong.class);
            Log.d("GSON", "load6 "  + latLong.toString());

        } catch (IOException e) {
            // handle exception

        }*/
    }
}

/*
        String url = "http://www.dropbox.com/s/fvmuwzweq34p56n/Munich.txt?dl=0";

        try {

            downloadUsingNIO(url, "Munich.txt", context);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void downloadUsingNIO(String urlStr, String file, Context context) throws IOException {

        URL download=new URL(urlStr);
        Log.d("GSON", "load1");
        ReadableByteChannel rbc=Channels.newChannel(download.openStream());
        Log.d("GSON", "load2");
        FileOutputStream fileOut = new FileOutputStream(new File(context.getFilesDir(),file));
        Log.d("GSON", "load3");
        fileOut.getChannel().transferFrom(rbc, 0, 1 << 24);
        Log.d("GSON", "load4");
        fileOut.flush();
        fileOut.close();
        rbc.close();

        /*Log.d("GSON", "load1");
        URL url = new URL(urlStr);
        Log.d("GSON", "load11 "  + url.toString());
        BufferedInputStream bis = new BufferedInputStream(url.openStream());

        Log.d("GSON", "load2");

        FileOutputStream fos = new FileOutputStream(new File(context.getFilesDir(),file));

        Log.d("GSON", "load3");

        byte[] buffer = new byte[1024];
        Log.d("GSON", "load4");
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fos.write(buffer, 0, count);
        }
        Log.d("GSON", "load5");
        fos.close();
        bis.close();*/

       /* InputStream is = new FileInputStream(new File(context.getFilesDir(),file));
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));

        String line = buf.readLine();

        Log.d("GSON", "load55" + line);

        StringBuilder sb = new StringBuilder();

        while(line != null){
            sb.append(line).append("\n");
            line = buf.readLine();
        }

        String fileAsString = sb.toString();

        Log.d("GSON", "load6 " + fileAsString);


        Gson gson = new Gson();
        LatLong latLong = gson.fromJson(fileAsString, LatLong.class);

        Log.d("GSON", "load7 "  + latLong.toString());
        

    }
}*/


