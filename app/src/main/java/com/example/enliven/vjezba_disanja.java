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
        progressBar=findViewById(R.id.progressBar2);

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
                                    if(i%5==0){
                                        textView.setText("Izdahni");
                                    }
                                    else{
                                        textView.setText("Udahni");
                                    }
                                }
                            });
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        Toast.makeText(getApplicationContext(),"Bravo! Vježba je završena.", Toast.LENGTH_SHORT).show();
                    }
                }).start();

            }
        });



    }
}