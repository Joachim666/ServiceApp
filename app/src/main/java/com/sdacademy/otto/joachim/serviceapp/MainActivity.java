package com.sdacademy.otto.joachim.serviceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
                    startService(intent);
                }
            });



        }


}
