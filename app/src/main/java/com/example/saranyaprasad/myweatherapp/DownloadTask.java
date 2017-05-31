package com.example.saranyaprasad.myweatherapp;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import java.net.MalformedURLException;

import javax.net.ssl.HttpsURLConnection;


/**
 * Created by SaranyaPrasad on 7/9/2016.
 */

public class DownloadTask extends AsyncTask<String,Void,String> {


    @Override
    protected String doInBackground(String... urls) {

        String result="";
        URL url;
        HttpURLConnection urlConnection=null;
      try {
          url = new URL(urls[0]);
          urlConnection = (HttpURLConnection) url.openConnection();
          InputStream in = urlConnection.getInputStream();
          InputStreamReader reader = new InputStreamReader(in);
          int data = reader.read();
          while (data != -1) {
          char current=(char)data;
              result +=current;
              data=reader.read();
          }
          return result;
      }
          catch(Exception e)
          {
              e.printStackTrace();
          }


          return null;
          }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        try {
            JSONObject jsonObject = new JSONObject(result);
            String weatherInfo=jsonObject.getString("weather");
            JSONObject weatherData =new JSONObject(jsonObject.getString("main"));
            double temperature=Double.parseDouble(weatherData.getString("temp"));
            System.out.println(temperature);
            int temperatureInteger=(int)(temperature*1.8-459.67);
            System.out.println(temperatureInteger);
            String placeName=jsonObject.getString("name");
            MainActivity.temperatureTextView.setText(String.valueOf(temperatureInteger)+"F");
            MainActivity.placeTextView.setText(placeName);
        }
         catch (Exception e)
         {
             e.printStackTrace();
         }









    }
}
