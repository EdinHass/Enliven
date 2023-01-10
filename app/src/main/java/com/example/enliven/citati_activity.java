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
    Timer timer = new Timer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citati);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Citati");

        text=findViewById(R.id.textViewcitat);

        citati=getResources().getStringArray(R.array.citati1);

        Intent intent=getIntent();

        int i1=intent.getIntExtra("Broj storija", 0);


       int brojstorija=i1;
       text.setText(citati[brojstorija]);

       if(brojstorija+1==19){
           timer.cancel();
       }


        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                if(brojstorija+1==19){
                    Intent i=new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    timer.cancel();
                }else{
                intent.putExtra("Broj storija", brojstorija+1);
                startActivity(intent);}
            }
        };
        timer.schedule(task,5000,5000);















    }

    public void onBackPressed(){
        this.finish();
        timer.cancel();
        Intent i=new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);


    }
}