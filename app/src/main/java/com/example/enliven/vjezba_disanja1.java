package com.example.enliven;

import static com.example.enliven.ui.UtilsKt.interpolateColor;
import static com.example.enliven.ui.UtilsKt.manipulateColor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;

public class vjezba_disanja1 extends AppCompatActivity {

    String vjezbaIme, SoundData;
    int vrijemeUzdisanja, vrijemeIzdisanja;
    TextView Title, text;
    MediaPlayer mediaPlayer;
    Button buttonStart;
    ProgressBar progressBar;
    int progressStatus = 0;
    private Handler handler = new Handler();
    ImageView imageView;
    ValueAnimator animator;
    RelativeLayout background;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vjezba_disanja1);
        vjezbaIme = getIntent().getStringExtra("vjezbaIme");
        SoundData = getIntent().getStringExtra("SoundData");
        vrijemeIzdisanja = getIntent().getIntExtra("vrijemeIzdisanja", 6);
        vrijemeUzdisanja = getIntent().getIntExtra("vrijemeUzdisanja", 4);
        Title = findViewById(R.id.vjezbaIme);
        text = findViewById(R.id.text);
        buttonStart = findViewById(R.id.buttonStart);
        imageView = findViewById(R.id.vjezbaSlika);
        progressBar=findViewById(R.id.progressBar2);
        background=findViewById(R.id.backgroundSound);

        mediaPlayer = MyMediaPlayer.getInstance(this);
        mediaPlayer.setLooping(true);
        AudioManager audioManager = (AudioManager) this.getSystemService(getApplicationContext().AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );


        try {
            mediaPlayer.setDataSource(SoundData);
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(vjezbaIme);


        Title.setText(vjezbaIme);

        int color = (new ColorDiagram()).getColor(vrijemeIzdisanja);
        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[] {0xFF130c20, interpolateColor(0xFF130c20, interpolateColor(0xFF130c20, manipulateColor(color, 0.4f), 0.4f), 0.4f), interpolateColor(0xFF130c20, manipulateColor(color, 0.4f), 0.4f), manipulateColor(color, 0.4f)});
        gd.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        gd.setGradientRadius(1.0f);
        background.setBackground(gd);



        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressStatus=0;

                 new Thread(new Runnable() {
                     public void run() {
                         mediaPlayer.seekTo(0);
                         mediaPlayer.setVolume(1, 7);
                         mediaPlayer.start();
                         for (int i = 0; i <= 7; i++) {
                             progressStatus = 0;
                             while (progressStatus < (vrijemeIzdisanja + vrijemeUzdisanja)) {

                                 handler.post(new Runnable() {
                                     public void run() {
                                         progressBar.setProgress(progressStatus);
                                         progressBar.setMax((vrijemeIzdisanja + vrijemeUzdisanja));
                                         if (progressStatus <= vrijemeUzdisanja) {
                                             text.setText("Udahni");

                                             int dimensionInDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 160, getResources().getDisplayMetrics());
                                             imageView.getLayoutParams().height = dimensionInDp;
                                             imageView.getLayoutParams().width = dimensionInDp;
                                             imageView.requestLayout();

                                         }


                                         else if (progressStatus > vrijemeUzdisanja && progressStatus <= vrijemeIzdisanja) {
                                             text.setText("Izdahni");
                                             int dimensionInDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 130, getResources().getDisplayMetrics());
                                             imageView.getLayoutParams().height = dimensionInDp;
                                             imageView.getLayoutParams().width = dimensionInDp;
                                             imageView.requestLayout();

                                         }
                                     }
                                 });
                                 progressStatus += 1;
                                 try {
                                     Thread.sleep(1000);
                                 } catch (InterruptedException e) {
                                     e.printStackTrace();
                                 }

                             }

                         }





                     }
                 }).start();




               }


        });


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
        public int getColor(int number) {
            String color = "";



            color = mColors[number];
            int colorAsInt = Color.parseColor(color);

            return colorAsInt;
        }

    }
    public void onBackPressed(){


        this.finish();
        }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    }

