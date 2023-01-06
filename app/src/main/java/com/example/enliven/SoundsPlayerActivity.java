package com.example.enliven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.DialogFragment;
import androidx.palette.graphics.Palette;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.Slider;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import pl.droidsonroids.gif.GifImageView;

public class SoundsPlayerActivity extends AppCompatActivity implements TimerDialogFragment.NoticeDialogListener{

    String SoundName;
    int SoundData, PictureData;
    TextView Title;
    MediaPlayer mediaPlayer;
    ImageView startButton, soundImage, volumeIcon;
    GifImageView gif;
    RelativeLayout back;
    Slider slider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sounds_player);
        SoundName = getIntent().getStringExtra("SoundName");
        SoundData = getIntent().getIntExtra("SoundData", R.raw.rain1);
        PictureData = getIntent().getIntExtra("ImageData", R.drawable.rain1);
        back = findViewById(R.id.backgroundSound);
        Title = findViewById(R.id.soundName);
        gif = findViewById(R.id.gif);
        slider = findViewById(R.id.slider);
        volumeIcon = findViewById(R.id.volumeIcon);
        startButton = findViewById(R.id.startButton);
        Drawable soundIcon = AppCompatResources.getDrawable(getApplicationContext(), R.drawable.ic_baseline_volume_up_24);
        Drawable playIcon = AppCompatResources.getDrawable(getApplicationContext(), R.drawable.ic_baseline_play_arrow_24);
        Drawable pauseIcon = AppCompatResources.getDrawable(getApplicationContext(), R.drawable.ic_baseline_pause_24);

        slider.setValue(0.5f);
        slider.setLabelFormatter(new LabelFormatter() {
            @NonNull
            @Override
            public String getFormattedValue(float value) {
                return Math.round(value*100) + "%";
            }
        });

        Drawable soundIconWrapped = DrawableCompat.wrap(soundIcon);
        Drawable pauseIconWrapped = DrawableCompat.wrap(pauseIcon);
        Drawable playIconWrapped = DrawableCompat.wrap(playIcon);
        Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), PictureData);
        Palette palette = Palette.from(bitmap).generate();
        Palette.Swatch vibrant = palette.getDarkVibrantSwatch();
        if (vibrant != null) {
            GradientDrawable gd = new GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    new int[] {0xFF130c20, interpolateColor(0xFF130c20, interpolateColor(0xFF130c20, manipulateColor(vibrant.getRgb(), 0.4f), 0.4f), 0.4f), interpolateColor(0xFF130c20, manipulateColor(vibrant.getRgb(), 0.4f), 0.4f), manipulateColor(vibrant.getRgb(), 0.4f)});
            gd.setGradientType(GradientDrawable.LINEAR_GRADIENT);
            gd.setGradientRadius(1.0f);
            back.setBackground(gd);
            slider.setTrackActiveTintList(ColorStateList.valueOf(palette.getVibrantSwatch().getRgb()));
            slider.setTrackInactiveTintList(ColorStateList.valueOf(vibrant.getRgb()));
            slider.setThumbTintList(ColorStateList.valueOf(palette.getVibrantSwatch().getRgb()));
            DrawableCompat.setTint(soundIconWrapped, palette.getVibrantSwatch().getRgb());
            volumeIcon.setImageDrawable(soundIconWrapped);
            DrawableCompat.setTint(pauseIconWrapped, vibrant.getRgb());
            DrawableCompat.setTint(playIconWrapped, vibrant.getRgb());
            startButton.setImageDrawable(playIconWrapped);



        }

        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.setVolume(slider.getValue(), slider.getValue());
                }
            }
        });




        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(SoundName);



        Title.setText(SoundName);

        soundImage = (ImageView)findViewById(R.id.soundImage);
        soundImage.setImageResource(PictureData);


        mediaPlayer = MyMediaPlayer.getInstance(this, SoundData);
        mediaPlayer.setLooping(true);
        AudioManager audioManager = (AudioManager) this.getSystemService(getApplicationContext().AUDIO_SERVICE);
        audioManager.setStreamVolume (AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC),0);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
                Animation animFadeout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    startButton.setImageDrawable(playIconWrapped);
                    startButton.startAnimation(animFadein);
                    gif.startAnimation(animFadeout);
                    gif.setVisibility(View.GONE);

                }else{
                    mediaPlayer.seekTo(0);
                    mediaPlayer.setVolume(slider.getValue()*10, slider.getValue()*10);
                    mediaPlayer.start();
                    startButton.setImageDrawable(pauseIconWrapped);
                    startButton.startAnimation(animFadein);
                    gif.startAnimation(animFadein);
                    gif.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        MyMediaPlayer.freeMediaPlayer();
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        MyMediaPlayer.freeMediaPlayer();
        finish();
        return true;
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



    private float interpolate(float a, float b, float proportion) {
        return (a + ((b - a) * proportion));
    }

    private int interpolateColor(int a, int b, float proportion) {

        if (proportion > 1 || proportion < 0) {
            throw new IllegalArgumentException("proportion must be [0 - 1]");
        }
        float[] hsva = new float[3];
        float[] hsvb = new float[3];
        float[] hsv_output = new float[3];

        Color.colorToHSV(a, hsva);
        Color.colorToHSV(b, hsvb);
        for (int i = 0; i < 3; i++) {
            hsv_output[i] = interpolate(hsva[i], hsvb[i], proportion);
        }

        int alpha_a = Color.alpha(a);
        int alpha_b = Color.alpha(b);
        float alpha_output = interpolate(alpha_a, alpha_b, proportion);

        return Color.HSVToColor((int) alpha_output, hsv_output);
    }

    public static int manipulateColor(int color, float factor) {
        int a = Color.alpha(color);
        int r = Math.round(Color.red(color) * factor);
        int g = Math.round(Color.green(color) * factor);
        int b = Math.round(Color.blue(color) * factor);
        return Color.argb(a,
                Math.min(r,255),
                Math.min(g,255),
                Math.min(b,255));
    }

    @Override
    public void onDialogPositiveClick(TimerDialogFragment dialog) {
        Timer myTimer = new Timer();
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

    private Runnable Timer_Tick = new Runnable() {
        public void run() {
            if(mediaPlayer.isPlaying()) {
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
}