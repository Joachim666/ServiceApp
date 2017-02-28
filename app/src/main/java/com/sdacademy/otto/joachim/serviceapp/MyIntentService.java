package com.sdacademy.otto.joachim.serviceapp;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.Intent;

/**
 * Created by RENT on 2017-02-28.
 */

public class MyIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super("My Intent Service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

    Intent intent = new Intent(this, WeatherIntentService.class).
            setAction("com.example.action.GET_CURRENT_WEATHER").
            putExtra("com.example.extra.CITY", "Warsaw");


}
