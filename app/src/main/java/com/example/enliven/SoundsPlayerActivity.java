package com.example.enliven;

import static com.example.enliven.ui.UtilsKt.interpolateColor;
import static com.example.enliven.ui.UtilsKt.manipulateColor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.DialogFragment;
import androidx.palette.graphics.Palette;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.Slider;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import pl.droidsonroids.gif.GifImageView;

public class SoundsPlayerActivity extends AppCompatActivity implements TimerDialogFragment.NoticeDialogListener{

    String SoundName, SoundData;
    int PictureData;
    TextView Title;
    MediaPlayer mediaPlayer;
    ImageView startButton, soundImage, volumeIcon;
    GifImageView gif, loading;
    RelativeLayout back;
    Slider slider;
    Timer myTimer;
    private ImageView favoriteIcon;
    Boolean favorited = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sounds_player);
        SoundName = getIntent().getStringExtra("SoundName");
        SoundData = getIntent().getStringExtra("SoundData");
        PictureData = getIntent().getIntExtra("ImageData", R.drawable.rain1);
        back = findViewById(R.id.backgroundSound);
        Title = findViewById(R.id.soundName);
        gif = findViewById(R.id.gif);
        loading = findViewById(R.id.loading);
        slider = findViewById(R.id.slider);
        volumeIcon = findViewById(R.id.volumeIcon);
        startButton = findViewById(R.id.startButton);
        favoriteIcon = findViewById(R.id.favoriteIcon);
        SharedPreferences prefs = getSharedPreferences("com.example.enliven", Context.MODE_PRIVATE);

        Drawable soundIcon = AppCompatResources.getDrawable(getApplicationContext(), R.drawable.ic_baseline_volume_up_24);
        Drawable playIcon = AppCompatResources.getDrawable(getApplicationContext(), R.drawable.ic_baseline_play_arrow_24);
        Drawable pauseIcon = AppCompatResources.getDrawable(getApplicationContext(), R.drawable.ic_baseline_pause_24);
        Drawable heartEmpty = AppCompatResources.getDrawable(getApplicationContext(), R.drawable.heart_empty);
        Drawable heartFilled = AppCompatResources.getDrawable(getApplicationContext(), R.drawable.heart_filled);


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
        Drawable heartEmptyWrapped = DrawableCompat.wrap(heartEmpty);
        Drawable heartFilledWrapped = DrawableCompat.wrap(heartFilled);
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
            DrawableCompat.setTint(heartEmpty, vibrant.getRgb());
            DrawableCompat.setTint(heartFilled, palette.getVibrantSwatch().getRgb());
            startButton.setImageDrawable(playIconWrapped);

            if(prefs.getStringSet("favorites", null)!=null) {
                if (prefs.getStringSet("favorites", null).contains(SoundName + "," + SoundData + "," + PictureData)) {
                    favoriteIcon.setImageDrawable(heartFilledWrapped);
                    favorited = true;
                } else {
                    favoriteIcon.setImageDrawable(heartEmptyWrapped);
                    favorited = false;
                }
            } else {
                favoriteIcon.setImageDrawable(heartEmptyWrapped);
                favorited = false;
            }


        }

        mediaPlayer = MyMediaPlayer.getInstance(this);
        mediaPlayer.setLooping(true);
        AudioManager audioManager = (AudioManager) this.getSystemService(getApplicationContext().AUDIO_SERVICE);
        audioManager.setStreamVolume (AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC),0);
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

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                startButton.setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);
                startButton.setClickable(true);
            }
        });

        favoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!favorited) {
                    favorited = true;
                    favoriteIcon.setImageDrawable(heartFilledWrapped);
                    animateHeart(favoriteIcon);
                    Snackbar.make(favoriteIcon.getRootView(), "Dodano u favorite", Snackbar.LENGTH_SHORT).show();

                    Set<String> set = new HashSet<>();
                    Set<String> oldFavs = (HashSet<String>) prefs.getStringSet("favorites", set);
                    Set<String> currentFavs = new HashSet<>();
                    currentFavs.add(SoundName + "," + SoundData + "," + PictureData);
                    currentFavs.addAll(oldFavs);
                    prefs.edit().putStringSet("favorites", currentFavs)
                                .apply();

                }else{
                    favorited = false;
                    favoriteIcon.setImageDrawable(heartEmptyWrapped);
                    animateHeart(favoriteIcon);

                    Snackbar.make(favoriteIcon.getRootView(), "Izbačeno iz favorita", Snackbar.LENGTH_SHORT).show();
                    Set<String> set = new HashSet<>();
                    Set<String> oldFavs = (HashSet<String>) prefs.getStringSet("favorites", set);

                    Set<String> currentFavs = new HashSet<>(oldFavs);
                    currentFavs.remove(SoundName + "," + SoundData + "," + PictureData);

                    prefs.edit().putStringSet("favorites", currentFavs)
                                .apply();
                }
            }
        });

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

        soundImage = findViewById(R.id.soundImage);
        soundImage.setImageResource(PictureData);



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
        if(myTimer!=null) {
            myTimer.cancel();
            myTimer.purge();
            Toast.makeText(getApplicationContext(), "Timer: Prekinut!", Toast.LENGTH_SHORT).show();
        }
        super.onBackPressed();
        MyMediaPlayer.freeMediaPlayer();
    }

    @Override
    public boolean onSupportNavigateUp() {
        MyMediaPlayer.freeMediaPlayer();
        if(myTimer!=null) {
            myTimer.cancel();
            myTimer.purge();
            Toast.makeText(getApplicationContext(), "Timer: Prekinut!", Toast.LENGTH_SHORT).show();
        }
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

    public void animateHeart(final ImageView view) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);

        AnimationSet animation = new AnimationSet(true);
        animation.addAnimation(alphaAnimation);
        animation.addAnimation(scaleAnimation);
        animation.setDuration(300);

        view.startAnimation(animation);

    }


}