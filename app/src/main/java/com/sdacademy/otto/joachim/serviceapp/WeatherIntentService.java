package com.sdacademy.otto.joachim.serviceapp;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import org.json.JSONObject;

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

        Intent intent = new Intent();
        intent.setAction("WEATHER_RESPONSE");
        intent.putExtra("TEMPERATURA",26.1);
        intent.putExtra("CISNIENIE",1018);
        intent.putExtra("MAIN","Cloudly");
        intent.putExtra("DATA",System.currentTimeMillis());
        intent.putExtra("CITY",city);


        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        broadcastManager.sendBroadcast(intent);


//        Log.i("TEST", "getWeather " + city);

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

