package com.example.enliven.ui.sleep.meditacije;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enliven.R;

public class vjezba_disanja extends AppCompatActivity {

    Button button;
    TextView textView;
    ProgressBar progressBar;
    ValueAnimator animator;
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
        animator = ValueAnimator.ofInt(0, progressBar.getMax());
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation){
                progressBar.setProgress((Integer)animation.getAnimatedValue());
            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Udahni");
                animator.start();
            }
        });



    }
}