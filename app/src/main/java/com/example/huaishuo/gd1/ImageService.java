package com.example.huaishuo.gd1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by huaishuo on 7/22/17.
 */

public class ImageService {
    public static Bitmap getImage(String path) throws Exception{
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setConnectTimeout(5000);
        connection.setRequestMethod("GET");
        if(connection.getResponseCode() == 200){
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }
        return null;
    }
}
