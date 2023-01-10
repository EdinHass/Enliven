package com.example.enliven;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Delayed;

import kotlinx.coroutines.Delay;
import pl.droidsonroids.gif.InputSource;

public class citati_activity extends AppCompatActivity {
    TextView text;
    private String[] citati;

    private static int TIME_OUT = 1000;

    int brojac=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citati);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Citati");

        text=findViewById(R.id.textViewcitat);

        citati=getResources().getStringArray(R.array.citati1);

        String data = getIntent().getExtras().getString("keyName","defaultKey");
        int brojstorija = getIntent().getExtras().getInt("Broj citata");
        text.setText(citati[brojstorija]);

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                text.setText(citati[(getIntent().getExtras().getInt("Broj citata")+1)]);
            }
        };
        timer.schedule(task,5000,5000);















    }


    public void random(TextView textView){
        int randomIndex = new Random().nextInt(citati.length);
        String randomCitat = citati[randomIndex];
        text.setText(randomCitat);
    }
}