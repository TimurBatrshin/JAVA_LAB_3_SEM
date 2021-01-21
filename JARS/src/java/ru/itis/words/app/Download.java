package ru.itis.javalab;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Download {
    private static int count;
    private String imageUrl;
    private String path;

    public void DownloadImages(String imageUrl, String path) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();


            OutputStream outputStream = new FileOutputStream(path + "\\" +counter() + ".jpg");

            byte[] b = new byte[256];

            int length;

            while ((length = inputStream.read(b)) != -1) {
                outputStream.write(b, 0, length);
            }

            inputStream.close();
            outputStream.close();
            connection.disconnect();

        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public int counter() {
        count++;
        return count;
    }
}
