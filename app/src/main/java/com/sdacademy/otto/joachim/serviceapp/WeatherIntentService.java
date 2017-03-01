package com.sdacademy.otto.joachim.serviceapp;

import android.app.DownloadManager;
import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by RENT on 2017-02-28.
 */
public class WeatherIntentService extends IntentService {
    public WeatherIntentService() {
        super("WeatherIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();


            if ("GET_WEATHER".equals(action)){
                String city = intent.getStringExtra("CITY");
                getWeather(city);
            }

//
        }
    }
    private void getWeather(String city){
        JSONObject jsonObject = sendRequest(city); // odbieram w mainie na koncu . najpierw senndrequest
        Intent intent = new Intent();
        intent.setAction("WEATHER_RESPONSE");
        JSONObject mainJsonObject = jsonObject.optJSONObject("main"); //zeby dobrac sie do maina bo tam jest temp and press
        if (mainJsonObject !=null){
            intent.putExtra("TEMERATURA",mainJsonObject.optDouble("temp"));
            intent.putExtra("CISNIENIE",mainJsonObject.optInt("pressure"));

        }
        JSONArray weatherJsonArray = jsonObject.optJSONArray("weather");// teraz to objectu weather bo tam jest main
        if (weatherJsonArray !=null && weatherJsonArray.length()>0){
            JSONObject weatherElement = weatherJsonArray.optJSONObject(0); // pierwszy elemen z tablicy , opt bo get moze dac wyjatek
            if (weatherElement !=null){
                intent.putExtra("MAIN",weatherElement.optString("main"));
                intent.putExtra("CITY",weatherElement.optString("name"));
            }
        }
        intent.putExtra("DATE",jsonObject.optLong("dt") * 1000L);

//        intent.putExtra("TEMPERATURA",26.1);
//        intent.putExtra("CISNIENIE",1018); tutaj bez neta samo przekazanie

//        intent.putExtra("MAIN","Cloudly");
//        intent.putExtra("DATA",System.currentTimeMillis());
//        intent.putExtra("CITY",city);


        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        broadcastManager.sendBroadcast(intent);


//        Log.i("TEST", "getWeather " + city);
    }
    private  JSONObject sendRequest(String city){
        Request.Builder builder = new Request.Builder();
        builder.url("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=49b31c92acee9b6e8d8f21d4bf420c6f");
        builder.get();

        Request request = builder.build();

        OkHttpClient client = new OkHttpClient(); //second day
        try {
            Response response = client.newCall(request).execute();
            String stringResponse = response.body().string(); // add this to trycatch
            Log.i("ELo","odp: " + stringResponse);
            JSONObject jsonObject = new JSONObject(stringResponse);
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
            return new JSONObject();
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONObject();
        }

    }
}



//private void getCurrentWeather(String city){
//        JSONObject jsonObject=...
//        LocalBroadcastManager broadcastManager=LocalBroadcastManager.getInstance(this);
//        Intent intent=new Intent();
//        intent.setAction("com.example.action.CURRENT_WEATHER_RESPONSE");
//        intent.putExtra("com.example.extra.SUCCESS",true);
//        intent.putExtra("com.example.extra.TEMPERATURE",jsonObject.optDouble("temp"));...
//        broadcastManager.sendBroadcast(intent)
//        }

