package com.example.mymap.presenter;

import com.dropbox.core.DbxException;
import com.example.mymap.model.JavaDropbox;
import java.io.IOException;

public class LoadFile {

    private static final String DROP_BOX_APP_KEY = "4bb5k37cxapx8ok";
    private static final String DROP_BOX_APP_SECRET = "xsf6gu7fuinuyt7";



    public void downloadFile() throws IOException {











/*JavaDropbox javaDropbox = new JavaDropbox();
        try {
            javaDropbox.authDropbox(DROP_BOX_APP_KEY, DROP_BOX_APP_SECRET);
            javaDropbox.downloadFromDropbox("Munich.txt");
        } catch (DbxException e) {
            e.printStackTrace();
        }*/
/*
        try {
            URL url = new URL("https://www.dropbox.com/s/ge593nhe49ttw7q/Munich.txt");
            BufferedReader read = new BufferedReader(
                    new InputStreamReader(url.openStream()));
            String i;
            while ((i = read.readLine()) != null)
                Log.d("GSON", "log0 " + i);
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
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
    }
}





