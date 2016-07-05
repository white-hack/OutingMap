package com.hack.white.outingmap.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MapHttpClient {
    public static void getJson(final String urlStr, final HttpClientInterface callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection con = null;
                BufferedInputStream inputStream = null;
                JSONObject json = null;

                try {
                    // URLの作成
                    URL url = new URL(urlStr);
                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.connect();
                    inputStream = new BufferedInputStream(con.getInputStream());
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = inputStream.read(buffer)) != -1){
                        if (length > 0){
                            outputStream.write(buffer, 0, length);
                        }
                    }

                    json = new JSONObject(new String(outputStream.toByteArray()));

                } catch (MalformedURLException e) {
                    new RuntimeException(e);
                } catch (IOException e) {
                } catch (JSONException e) {
                    new RuntimeException(e);
                } finally {
                    if (con != null) {
                        con.disconnect();
                    }
                    try {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    } catch (IOException e) {
                        new RuntimeException(e);
                    }
                }
                callback.onSuccess(json);
            }}).start();
    }

    public static void getImage(final String urlStr, final ImageView imageView) {
        (new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... param) {
                HttpURLConnection con = null;
                InputStream inputStream = null;
                Bitmap bitmap = null;

                try {
                    // URLの作成
                    URL url = new URL(urlStr);
                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.connect();
                    inputStream = con.getInputStream();

                    bitmap = BitmapFactory.decodeStream(inputStream);

                } catch (MalformedURLException e) {
                    new RuntimeException(e);
                } catch (IOException e) {
                } finally {
                    if (con != null) {
                        con.disconnect();
                    }
                    try {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    } catch (IOException e) {
                        new RuntimeException(e);
                    }
                }
                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap result) {
                imageView.setImageBitmap(result);
            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public interface HttpClientInterface {
        void onSuccess(Object param);
    }
}
