package com.project.event.UtilityFunctions;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.AndroidHttpClient;

import com.project.event.Activity.MainActivity;
import com.project.event.CustomWidgets.ConfirmationWindow;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

public class Utilities {

    public static final String SERVER_LOGS="ReqRes";

    public static Bitmap downloadBitmap(String url) {
        final AndroidHttpClient client = AndroidHttpClient.newInstance("Android");
        final HttpGet getRequest = new HttpGet(url);

        try {
            HttpResponse response = client.execute(getRequest);
            final int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {

                return null;
            }

            final HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream inputStream = null;
                try {
                    inputStream = entity.getContent();
                    final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    return bitmap;
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    entity.consumeContent();
                }
            }
        } catch (Exception e) {
            // Could provide a more explicit error message for IOException or IllegalStateException
            getRequest.abort();

        } finally {
            if (client != null) {
                client.close();
            }
        }
        return null;
    }

    public static String sendDataToServer(String url, int method,
                                          List<NameValuePair> params) {
        Logger.log("data"," --- IN SEND DATA TO SERVER -----");
        final int TIME_OUT_IN_SECONDS=10;

        InputStream is = null;
        String response = null;


        if (params != null) {

            Logger.log(SERVER_LOGS,"***********************************************");
            Logger.log(SERVER_LOGS, "Sending " + params.toString() + " to " + url);
            Logger.log(SERVER_LOGS,"***********************************************");
        }
        else
            Logger.log("beta", "Sending to " + url);
        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;

            // Checking http request method type
            if (method == MainActivity.POST) {
                HttpPost httpPost = new HttpPost(url);

                long requestStartTime = new Date().getTime();
                httpResponse = httpClient.execute(httpPost);
                long requestEndTime = new Date().getTime();


                long timeOfRequest = (requestEndTime - requestStartTime) / 1000;

                if (httpResponse == null && timeOfRequest > TIME_OUT_IN_SECONDS) {

                    return null;
                }
                // adding post params
                if (params != null) {
                    httpPost.setEntity(new UrlEncodedFormEntity(params));
                }

                httpResponse = httpClient.execute(httpPost);

            } else if (method == MainActivity.GET) {
                // appending params to url
                if (params != null) {
                    String paramString = URLEncodedUtils
                            .format(params, "utf-8");
                    url += "?" + paramString;
                }
                HttpGet httpGet = new HttpGet(url);

                httpResponse = httpClient.execute(httpGet);

            }
            httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        response = convertInputStreamToString(is);

        Logger.log(SERVER_LOGS,"***********************************************");
        Logger.log(SERVER_LOGS, "Response from server : " + response);
        Logger.log(SERVER_LOGS,"***********************************************");


        return response;

    }

    private static String convertInputStreamToString(InputStream inputStream) {
        String result = "";
        try {


            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream));
            String line = "";

            while ((line = bufferedReader.readLine()) != null)
                result += line;

            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }


    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null)
                    for (int i = 0; i < info.length; i++)
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }

            }
        }catch (Exception e)

        {

        }
        return false;
    }

    public static void showNetworkNotAvailableDialog(Context context) {

        new ConfirmationWindow(context, "Network unavailable", "Please check your network connection", "Ok", "");
    }


}
