package com.example.enliven;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Picture;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;

import pl.droidsonroids.gif.GifImageView;

public class SoundsPlayerActivity extends AppCompatActivity {

    String SoundName;
    int SoundData, PictureData;
    TextView Title;
    MediaPlayer mediaPlayer;
    ImageView startButton, soundImage;
    GifImageView gif;
    RelativeLayout back;

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
        }




        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(SoundName);


        startButton = findViewById(R.id.startButton);
        Title.setText(SoundName);

        soundImage = (ImageView)findViewById(R.id.soundImage);
        soundImage.setImageResource(PictureData);


        mediaPlayer = MyMediaPlayer.getInstance(this, SoundData);
        mediaPlayer.setLooping(true);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
                Animation animFadeout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    startButton.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                    startButton.startAnimation(animFadein);
                    gif.startAnimation(animFadeout);
                    gif.setVisibility(View.GONE);

                }else{
                    mediaPlayer.start();
                    startButton.setImageResource(R.drawable.ic_baseline_pause_24);
                    startButton.startAnimation(animFadein);
                    gif.startAnimation(animFadein);
                    gif.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        MyMediaPlayer.freeMediaPlayer();
        finish();
        return true;
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
}