package com.example.enliven;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class vjezba_disanja extends AppCompatActivity {

    Button button;
    TextView textView;
    ProgressBar progressBar;
    int progressStatus=0;
    int i=0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vjezba_disanja);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Vježba disanja");

        button=findViewById(R.id.buttonstart);
        textView=findViewById(R.id.text);
        progressBar=findViewById(R.id.progressBar3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        while (progressStatus < 50) {
                            progressStatus += 1;
                           i+=1;
                            handler.post(new Runnable() {
                                public void run() {
                                    progressBar.setProgress(progressStatus);
                                    if(i==5 || i==15 || i==25 || i==35 || i==45){
                                        textView.setText("Izdahni");
                                    }
                                    else if(i==10 || i==20 || i==30 || i==40){
                                        textView.setText("Udahni");
                                    }
                                    else if(i==50){
                                        Toast.makeText(getApplicationContext(),"Bravo! Vježba je završena.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        progressStatus=0;
                        i=0;
                        textView.setText("Udahni");

                    }
                }).start();

            }
        });



    }
}