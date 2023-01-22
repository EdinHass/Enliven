package com.example.enliven;

import static com.example.enliven.ui.UtilsKt.addXP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.DialogFragment;

import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.enliven.ui.addXP;
import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.Slider;

import java.util.Timer;
import java.util.TimerTask;

import pl.droidsonroids.gif.GifImageView;

public class CustomSoundActivity extends AppCompatActivity implements TimerDialogFragment.NoticeDialogListener {

    Slider slider1, slider2, slider3, slider4, slider5, slider6, slider7, slider8;
    ImageView startButton;
    GifImageView gif;
    LoopMediaPlayer m1, m2, m3, m4, m5, m6, m7, m8;
    Timer myTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_sound);
        slider1 = findViewById(R.id.slider1);
        slider2 = findViewById(R.id.slider2);
        slider3 = findViewById(R.id.slider3);
        slider4 = findViewById(R.id.slider4);
        slider5 = findViewById(R.id.slider5);
        slider6 = findViewById(R.id.slider6);
        slider7 = findViewById(R.id.slider7);
        slider8 = findViewById(R.id.slider8);
        startButton = findViewById(R.id.startButton);
        gif = findViewById(R.id.gif);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Vaš zvuk");

        addXP(20, this, startButton.getRootView(), addXP.SOUNDS);

        Drawable soundIcon = AppCompatResources.getDrawable(getApplicationContext(), R.drawable.ic_baseline_volume_up_24);
        Drawable playIcon = AppCompatResources.getDrawable(getApplicationContext(), R.drawable.ic_baseline_play_arrow_24);
        Drawable pauseIcon = AppCompatResources.getDrawable(getApplicationContext(), R.drawable.ic_baseline_pause_24);

        setupSliders();
        initPlayers();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
                Animation animFadeout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
                if(m1.isPlaying() && m2.isPlaying() && m3.isPlaying() && m4.isPlaying() && m5.isPlaying() && m6.isPlaying() && m7.isPlaying() && m8.isPlaying()){
                    pausePlayers();
                    startButton.setImageDrawable(playIcon);
                    startButton.startAnimation(animFadein);
                    gif.startAnimation(animFadeout);
                    gif.setVisibility(View.GONE);

                }else{
                    startPlayers();
                    startButton.setImageDrawable(pauseIcon);
                    startButton.startAnimation(animFadein);
                    gif.startAnimation(animFadein);
                    gif.setVisibility(View.VISIBLE);
                }
            }
        });

        Slider.OnChangeListener listener = new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                setVolumes();
            }
        };

        slider1.addOnChangeListener(listener);
        slider2.addOnChangeListener(listener);
        slider3.addOnChangeListener(listener);
        slider4.addOnChangeListener(listener);
        slider5.addOnChangeListener(listener);
        slider6.addOnChangeListener(listener);
        slider7.addOnChangeListener(listener);
        slider8.addOnChangeListener(listener);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sound_player_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.timer){
            DialogFragment newFragment = new TimerDialogFragment();
            newFragment.show(getSupportFragmentManager(), "Timer");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        freeMediaPlayers();
        if(myTimer!=null) {
            myTimer.cancel();
            myTimer.purge();
            Toast.makeText(getApplicationContext(), "Timer: Prekinut!", Toast.LENGTH_SHORT).show();
        }
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        freeMediaPlayers();
        if(myTimer!=null) {
            myTimer.cancel();
            myTimer.purge();
            Toast.makeText(getApplicationContext(), "Timer: Prekinut!", Toast.LENGTH_SHORT).show();
        }
        finish();
        return true;
    }

    @Override
    public void onDialogPositiveClick(TimerDialogFragment dialog) {
        myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
                myTimer.cancel();
                myTimer.purge();
            }

        }, Math.round(dialog.slider.getValue()*100)*2*60*1000, 3000);

        Toast.makeText(getApplicationContext(), "Timer postavljen za: " + getFormattedValue(dialog.slider.getValue()), Toast.LENGTH_SHORT).show();
    }

    private void TimerMethod(){
        this.runOnUiThread(Timer_Tick);
    }

    private final Runnable Timer_Tick = new Runnable() {
        public void run() {
            if(m1.isPlaying() && m2.isPlaying() && m3.isPlaying() && m4.isPlaying() && m5.isPlaying() && m6.isPlaying() && m7.isPlaying() && m8.isPlaying()) {
                startButton.callOnClick();
                Toast.makeText(getApplicationContext(), "Timer: Aktiviran!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "Timer: Zvuk je vec pauziran!", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onDialogNegativeClick(TimerDialogFragment dialog) {

    }

    public String getFormattedValue(float value) {
        int val = Math.round(value*100);
        int minutes = val*2;
        int hours = minutes/60;
        minutes = minutes % 60;
        String res = hours + "h " + minutes + "m";
        return res;
    }

    private void freeMediaPlayers(){
        if(m1!=null){
            m1.stop();
            m1.release();
        }
        if(m2!=null){
            m2.stop();
            m2.release();
        }
        if(m3!=null){
            m3.stop();
            m3.release();
        }
        if(m4!=null){
            m4.stop();
            m4.release();
        }
        if(m5!=null){
            m5.stop();
            m5.release();
        }
        if(m6!=null){
            m6.stop();
            m6.release();
        }
        if(m7!=null){
            m7.stop();
            m7.release();
        }
        if(m8!=null){
            m8.stop();
            m8.release();
        }
    }
    private void setVolumes() {
        m1.setVolume(slider1.getValue(), slider1.getValue());
        m2.setVolume(slider2.getValue(), slider2.getValue());
        m3.setVolume(slider3.getValue(), slider3.getValue());
        m4.setVolume(slider4.getValue(), slider4.getValue());
        m5.setVolume(slider5.getValue(), slider5.getValue());
        m6.setVolume(slider6.getValue(), slider6.getValue());
        m7.setVolume(slider7.getValue(), slider7.getValue());
        m8.setVolume(slider8.getValue(), slider8.getValue());
    }

    private void startPlayers(){
        m1.start();
        m2.start();
        m3.start();
        m4.start();
        m5.start();
        m6.start();
        m7.start();
        m8.start();
        m1.seekTo(0);
        m2.seekTo(0);
        m3.seekTo(0);
        m4.seekTo(0);
        m5.seekTo(0);
        m6.seekTo(0);
        m7.seekTo(0);
        m8.seekTo(0);
        setVolumes();

    }

    private void pausePlayers(){
        m1.pause();
        m2.pause();
        m3.pause();
        m4.pause();
        m5.pause();
        m6.pause();
        m7.pause();
        m8.pause();
    }

    private void initPlayers(){
        if(m1==null) {
            m1 = LoopMediaPlayer.create(this, R.raw.rain_custom_sounds);
            m1.setLooping(true);
        }
        if(m2==null) {
            m2 = LoopMediaPlayer.create(this, R.raw.wind_custom_sounds);
            m2.setLooping(true);
        }
        if(m3==null) {
            m3 = LoopMediaPlayer.create(this, R.raw.thunder_custom_sounds);
            m3.setLooping(true);
        }
        if(m4==null) {
            m4 = LoopMediaPlayer.create(this, R.raw.fire_custom_sounds);
            m4.setLooping(true);
        }
        if(m5==null) {
            m5 = LoopMediaPlayer.create(this, R.raw.cricket_custom_sounds);
            m5.setLooping(true);
        }
        if(m6==null) {
            m6 = LoopMediaPlayer.create(this, R.raw.waves_custom_sounds);
            m6.setLooping(true);
        }
        if(m7==null) {
            m7 = LoopMediaPlayer.create(this, R.raw.piano_custom_sounds);
            m7.setLooping(true);
        }
        if(m8==null) {
            m8 = LoopMediaPlayer.create(this, R.raw.cafe_custom_sounds);
            m8.setLooping(true);
        }
        AudioManager audioManager = (AudioManager) this.getSystemService(getApplicationContext().AUDIO_SERVICE);
        audioManager.setStreamVolume (AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC),0);
        m1.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        m2.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        m3.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        m4.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        m5.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        m6.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        m7.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        m8.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
    }


    private void setupSliders(){
        slider1.setValue(getIntent().getFloatExtra("vol1", 0.0f));
        LabelFormatter labelFormatter = new LabelFormatter() {
            @NonNull
            @Override
            public String getFormattedValue(float value) {
                return Math.round(value*100) + "%";
            }
        };
        slider1.setLabelFormatter(labelFormatter);
        slider2.setValue(getIntent().getFloatExtra("vol2", 0.0f));
        slider2.setLabelFormatter(labelFormatter);
        slider3.setValue(getIntent().getFloatExtra("vol3", 0.0f));
        slider3.setLabelFormatter(labelFormatter);
        slider4.setValue(getIntent().getFloatExtra("vol4", 0.0f));
        slider4.setLabelFormatter(labelFormatter);
        slider5.setValue(getIntent().getFloatExtra("vol5", 0.0f));
        slider5.setLabelFormatter(labelFormatter);
        slider6.setValue(getIntent().getFloatExtra("vol6", 0.0f));
        slider6.setLabelFormatter(labelFormatter);
        slider7.setValue(getIntent().getFloatExtra("vol7", 0.0f));
        slider7.setLabelFormatter(labelFormatter);
        slider8.setValue(getIntent().getFloatExtra("vol8", 0.0f));
        slider8.setLabelFormatter(labelFormatter);
    }
}