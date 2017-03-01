package com.sdacademy.otto.joachim.serviceapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;


import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

        @BindView(R.id.button_pobierz3)
        Button button3;
        @BindView(R.id.button_pobierz1)
        Button button1;
        @BindView(R.id.button_pobierz2)
        Button button2;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.pressure)
        TextView pressure;
        @BindView(R.id.temp)
        TextView temperature;
        @BindView(R.id.main)
        TextView main;
        @BindView(R.id.icona)
        ImageView icon;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onReceive(Context context, Intent intent) {
            double temp =intent.getDoubleExtra("TEMPERATURA",0);
            int pressure1 = intent.getIntExtra("CISNIENIE" , 0);
            String main1 = intent.getStringExtra("MAIN");
            Long date1 = intent.getLongExtra("DATA",0);



            temperature.setText("Temperatura " + temp + " C");
            pressure.setText("Ciśnienie " + pressure1);
            date.setText("Data: " + convertDate(date1));
            main.setText("Main : " + main1);


        }
    };
@RequiresApi(api = Build.VERSION_CODES.N)
private  String convertDate(long date){
    SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    return format.format(new Date(date));
}




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            ButterKnife.bind(this);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent  = new Intent(MainActivity.this,WeatherIntentService.class);
                    intent.setAction("GET_WEATHER");
                    intent.putExtra("CITY", "London");
                    startService(intent);
                }
            });
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent  = new Intent(MainActivity.this,WeatherIntentService.class);
                    intent.setAction("GET_WEATHER");// check with log
                    intent.putExtra("CITY", "Warsaw");
                    startService(intent);
                }
            });
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent  = new Intent(MainActivity.this,WeatherIntentService.class);
                    intent.setAction("GET_WEATHER");
                    intent.putExtra("CITY", "Tokio");
                    startService(intent);
                }
            });
            IntentFilter filter = new IntentFilter();
            filter.addAction("WEATHER_RESPONSE");

            LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);//regegister our broadcastreciver
            broadcastManager.registerReceiver(broadcastReceiver,filter);

        }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        broadcastManager.unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }
}
