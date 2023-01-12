package com.example.enliven;

import static com.example.enliven.ui.UtilsKt.interpolateColor;
import static com.example.enliven.ui.UtilsKt.manipulateColor;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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
    ProgressBar progressBar;
    ValueAnimator animator;
    public boolean onDown = false;
    ConstraintLayout backgroundLayout;
    RelativeLayout leftClickZone, rightClickZone;
    SharedPreferences prefs;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citati);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        SharedPreferences prefs = getSharedPreferences("com.example.enliven", Context.MODE_PRIVATE);
        text=findViewById(R.id.textViewcitat);
        progressBar = findViewById(R.id.ProgressBar);
        citati=getResources().getStringArray(R.array.citati1);
        leftClickZone = findViewById(R.id.clickZoneLeft);
        rightClickZone = findViewById(R.id.clickZoneRight);
        backgroundLayout = findViewById(R.id.backgroundLayout);


        Intent intent=getIntent();
        final int[] brojstorija = {intent.getIntExtra("Broj storija", 0)};
        prefs.edit().putBoolean("storyCitat"+brojstorija[0], true).apply();

        animator = ValueAnimator.ofInt(0, progressBar.getMax());
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation){
                progressBar.setProgress((Integer)animation.getAnimatedValue());
            }
        });
        animator.start();

        int color = (new ColorDiagram()).getColor();
        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[] {0xFF130c20, interpolateColor(0xFF130c20, interpolateColor(0xFF130c20, manipulateColor(color, 0.4f), 0.4f), 0.4f), interpolateColor(0xFF130c20, manipulateColor(color, 0.4f), 0.4f), manipulateColor(color, 0.4f)});
        gd.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        gd.setGradientRadius(1.0f);
        backgroundLayout.setBackground(gd);


        GestureDetector mDetector = new GestureDetector(this, new MyGestureListener());

        rightClickZone.setOnTouchListener(new View.OnTouchListener() {
            boolean holding = false;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(mDetector.onTouchEvent(event) && !onDown){
                    return true;
                }
                switch (event.getAction()){
                    case MotionEvent.ACTION_UP:
                        if(!holding) {
                            if(animator!=null) {
                                animator.cancel();
                            }
                        }else {
                            holding = false;
                            if (animator.isPaused()) {
                                animator.resume();
                            }
                        }
                        return true;

                    case MotionEvent.ACTION_DOWN:
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(event.getAction()==MotionEvent.ACTION_DOWN) {
                                    animator.pause();
                                    holding = true;
                                }
                            }
                        }, 100);
                        return true;
                }
                return false;
            }
        });

        leftClickZone.setOnTouchListener(new View.OnTouchListener() {
            boolean holding = false;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(mDetector.onTouchEvent(event) && !onDown){
                    return true;
                }
                switch (event.getAction()){
                    case MotionEvent.ACTION_UP:
                        if(!holding) {
                            if(brojstorija[0]>=1) {
                                brojstorija[0] = brojstorija[0] - 2;
                                if(animator!=null) {
                                    animator.cancel();
                                }
                            }
                        }else {
                            holding = false;
                            if (animator.isPaused()) {
                                animator.resume();
                            }
                        }
                        return true;

                    case MotionEvent.ACTION_DOWN:
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(event.getAction()==MotionEvent.ACTION_DOWN) {
                                    if(animator!=null) {
                                        animator.pause();
                                    }
                                    holding = true;
                                }
                            }
                        }, 100);
                        return true;
                }
                return false;
            }
        });
        prefs = getSharedPreferences("com.example.enliven", Context.MODE_PRIVATE);

        SharedPreferences finalPrefs = prefs;
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if(getPos(brojstorija[0]) + 1>19){
                    if(animator!=null) {
                        animator.removeAllListeners();
                        animator.cancel();
                    }
                    animator=null;
                    finish();
                }else{
                    if(animator!=null) {
                        animator.removeAllListeners();
                        animator.cancel();
                    }
                    animator=null;
                    int a = getPos(brojstorija[0]) + 1;
                    intent.putExtra("Broj storija", finalPrefs.getInt("storyRedoslijed"+a,-1));
                    startActivity(intent);
                }
            }
        });
        text.setText(citati[brojstorija[0]]);
       }

    public void onBackPressed(){
        if(animator!=null) {
            animator.removeAllListeners();
            animator.cancel();
        }
        animator=null;
        finish();
        super.onBackPressed();
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent event) {
            onDown = true;
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            onBackPressed();
            onDown=false;
            return true;
        }
    }
    public class ColorDiagram {
        // Member variables (properties about the object)
        public String[] mColors = {
                "#39add1", // light blue
                "#3079ab", // dark blue
                "#c25975", // mauve
                "#e15258", // red
                "#f9845b", // orange
                "#838cc7", // lavender
                "#7d669e", // purple
                "#53bbb4", // aqua
                "#51b46d", // green
                "#e0ab18", // mustard
                "#637a91", // dark gray
                "#f092b0", // pink
                "#b7c0c7"  // light gray
        };

        // Method (abilities: things the object can do)
        public int getColor() {
            String color = "";

            // Randomly select a fact
            Random randomGenerator = new Random(); // Construct a new Random number generator
            int randomNumber = randomGenerator.nextInt(mColors.length);

            color = mColors[randomNumber];
            int colorAsInt = Color.parseColor(color);

            return colorAsInt;
        }

    }
    public int getPos(int code){
        SharedPreferences prefs = getSharedPreferences("com.example.enliven", Context.MODE_PRIVATE);
        for(int i = 0; i<20; i++){
            if(prefs.getInt("storyRedoslijed"+i, -1)==code){
                return i;
            }
        }
        return -1;
    }
}
