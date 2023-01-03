package com.example.enliven;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import pl.droidsonroids.gif.GifImageView;

public class SoundsPlayerActivity extends AppCompatActivity {

    String SoundName;
    int SoundData;
    TextView Title;
    MediaPlayer mediaPlayer;
    ImageView startButton;
    GifImageView gif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sounds_player);
        SoundName = getIntent().getStringExtra("SoundName");
        SoundData = getIntent().getIntExtra("SoundData", 0);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(SoundName);

        Title = findViewById(R.id.soundName);
        startButton = findViewById(R.id.startButton);
        gif = findViewById(R.id.gif);
        Title.setText(SoundName);


        mediaPlayer = MyMediaPlayer.getInstance(this, SoundData);
        mediaPlayer.setLooping(true);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
                Animation animFadeout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    startButton.setImageResource(R.drawable.ic_baseline_play_circle_outline_24);
                    startButton.startAnimation(animFadein);
                    gif.startAnimation(animFadeout);
                    gif.setVisibility(View.GONE);

                }else{
                    mediaPlayer.start();
                    startButton.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24);
                    startButton.startAnimation(animFadein);
                    gif.startAnimation(animFadein);
                    gif.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        mediaPlayer.pause();
        finish();
        return true;
    }
}