package com.example.enliven.ui.sleep.meditacije;

import static com.example.enliven.ui.UtilsKt.interpolateColor;
import static com.example.enliven.ui.UtilsKt.manipulateColor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enliven.R;
import com.google.protobuf.Value;

import java.io.IOException;
import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class vjezba_disanja1 extends AppCompatActivity {

    String vjezbaIme;
    int vrijemeUzdisanja, vrijemeIzdisanja;
    TextView Title, text;
    MediaPlayer mediaPlayer;
    Button buttonStart;
    ProgressBar progressBar;
    private Handler handler = new Handler();
    ImageView imageView;
    ValueAnimator animator, animatorCircle, animator1, animatorCircle1;
    RelativeLayout background;
    int SoundData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vjezba_disanja1);
        vjezbaIme = getIntent().getStringExtra("vjezbaIme");
        SoundData = getIntent().getIntExtra("SoundData", R.raw.piano_custom_sounds);
        vrijemeIzdisanja = getIntent().getIntExtra("vrijemeIzdisanja", 6);
        vrijemeUzdisanja = getIntent().getIntExtra("vrijemeUzdisanja", 4);
        Title = findViewById(R.id.vjezbaIme);
        text = findViewById(R.id.text);
        buttonStart = findViewById(R.id.buttonStart);
        imageView = findViewById(R.id.vjezbaSlika);
        progressBar = findViewById(R.id.progressBarMain);
        background = findViewById(R.id.backgroundSound);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(vjezbaIme);
        Title.setText(vjezbaIme);

        mediaPlayer = MediaPlayer.create(this, SoundData);
        mediaPlayer.setLooping(true);
        AudioManager audioManager = (AudioManager) this.getSystemService(AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );


        animator = ValueAnimator.ofInt(0, progressBar.getMax() + 1);
        animator1 = ValueAnimator.ofInt(progressBar.getMax() + 1, 0);
        animator1.setDuration(vrijemeIzdisanja * 1000L);
        animator.setDuration(vrijemeUzdisanja * 1000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progressBar.setProgress((Integer) animation.getAnimatedValue());
            }
        });
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progressBar.setProgress((Integer) animation.getAnimatedValue());
            }
        });
        float start = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                150,
                getResources().getDisplayMetrics()
        );
        float end = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                280,
                getResources().getDisplayMetrics()
        );
        animatorCircle = ValueAnimator.ofInt(Math.round(start), Math.round(end));
        animatorCircle1 = ValueAnimator.ofInt(Math.round(end), Math.round(start));
        animatorCircle.setDuration(vrijemeUzdisanja * 1000L);
        animatorCircle1.setDuration(vrijemeIzdisanja * 1000L);
        animatorCircle.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                imageView.requestLayout();
                imageView.getLayoutParams().width = (int) animation.getAnimatedValue();
                imageView.getLayoutParams().height = (int) animation.getAnimatedValue();
            }
        });
        animatorCircle1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                imageView.requestLayout();
                imageView.getLayoutParams().width = (int) animation.getAnimatedValue();
                imageView.getLayoutParams().height = (int) animation.getAnimatedValue();
            }
        });


        Animation fadein = AnimationUtils.loadAnimation(this, R.anim.fade_in_slow);

        final boolean[] pressed = {false};
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!pressed[0]) {
                    mediaPlayer.start();
                    buttonStart.setText("CANCEL");
                    animator.start();
                    animatorCircle.start();
                    text.startAnimation(fadein);
                    text.setText("Udahni");

                } else {
                    mediaPlayer.seekTo(0);
                    mediaPlayer.pause();
                    buttonStart.setText("START");
                    animator.pause();
                    animatorCircle.pause();
                    animator1.pause();
                    animatorCircle1.pause();
                    text.startAnimation(fadein);
                    text.setText("Pokreni");
                }
                pressed[0] = !pressed[0];
            }
        });

        animator1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(@NonNull Animator animation, boolean isReverse) {
                super.onAnimationEnd(animation, isReverse);
                animator1.cancel();
                animatorCircle1.cancel();
                animator.start();
                animatorCircle.start();
                text.startAnimation(fadein);
                text.setText("Udahni");
            }
        });

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(@NonNull Animator animation, boolean isReverse) {
                super.onAnimationEnd(animation, isReverse);
                animator.cancel();
                animatorCircle.cancel();
                animator1.start();
                animatorCircle1.start();
                text.startAnimation(fadein);
                text.setText("Izdahni");
            }
        });


        int color = (new ColorDiagram()).getColor(vrijemeIzdisanja);
        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{0xFF130c20, interpolateColor(0xFF130c20, interpolateColor(0xFF130c20, manipulateColor(color, 0.4f), 0.4f), 0.4f), interpolateColor(0xFF130c20, manipulateColor(color, 0.4f), 0.4f), manipulateColor(color, 0.4f)});
        gd.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        gd.setGradientRadius(1.0f);
        background.setBackground(gd);
    }

    public class ColorDiagram {
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

        public int getColor(int number) {
            String color = "";


            color = mColors[number];
            int colorAsInt = Color.parseColor(color);

            return colorAsInt;
        }

    }

    public void onBackPressed() {
        mediaPlayer.stop();
        mediaPlayer.release();
        this.finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        mediaPlayer.stop();
        mediaPlayer.release();
        finish();
        return true;
    }

}

