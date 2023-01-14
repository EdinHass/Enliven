package com.example.enliven;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class card1activity extends AppCompatActivity {

    RelativeLayout Rain1, soundsinfo, customSoundCard;
    LinearLayout recommended, recommended2;
    LinearLayout cats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card1activity);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Ambijentalni Zvukovi");
        soundsinfo = findViewById(R.id.soundsinfo);
        cats = findViewById(R.id.cats);
        customSoundCard = findViewById(R.id.custom_sound_card);
        recommended2 = findViewById(R.id.recommended2);

        setupCustomSounds(customSoundCard, new float[]{0.5f, 0.5f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f});
        setupCustomSounds(recommended2.getChildAt(0), new float[]{0.0f, 0.05f, 0.0f, 0.0f, 0.15f, 0.5f, 0.0f, 0.0f});
        setupCustomSounds(recommended2.getChildAt(1), new float[]{0.0f, 0.0f, 0.0f, 0.1f, 0.0f, 0.0f, 0.5f, 0.8f});
        setupCustomSounds(recommended2.getChildAt(2), new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.05f, 0.3f, 0.5f, 0.0f});





        cats.getChildAt(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), sounds_category_nature.class));
            }
        });

        cats.getChildAt(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), sounds_category_ambijent.class));
            }
        });
        cats.getChildAt(4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), sounds_category_space.class));
            }
        });

        cats.getChildAt(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),sounds_category_music.class));
            }
        });

        cats.getChildAt(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),sounds_category_rain.class));
            }
        });

        soundsinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundsDialogFragment soundsDialogFragment = new SoundsDialogFragment();
                soundsDialogFragment.show(getSupportFragmentManager(), "Informacije");
            }
        });

        Rain1 = findViewById(R.id.cardRain1);
        recommended = findViewById(R.id.recommended);
        setupSound(recommended.getChildAt(0), new SoundItem("Ljetna Kiša", "https://cdn.pixabay.com/download/audio/2022/05/13/audio_257112ce99.mp3?filename=soft-rain-ambient-111154.mp3https://cdn.pixabay.com/download/audio/2022/05/13/audio_257112ce99.mp3?filename=soft-rain-ambient-111154.mp3", R.drawable.rain1));
        setupSound(recommended.getChildAt(1), new SoundItem("Planinski Potok", "https://cdn.pixabay.com/download/audio/2022/03/09/audio_9a9ca75996.mp3?filename=river-stream-moderate-flow-2-24370.mp3", R.drawable.river1));
        setupSound(recommended.getChildAt(2), new SoundItem("Tihi Klavir", "https://cdn.pixabay.com/download/audio/2022/03/09/audio_7691ecb51a.mp3?filename=sadness-in-roads-to-nowhere-23407.mp3", R.drawable.piano1));


    }

    public void setupSound(View view, SoundItem soundItem){
        view.setOnClickListener(new View.OnClickListener() {
            private long lastClickTime = 0;
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                view.setClickable(false);
                Intent i = new Intent(getApplicationContext(), SoundsPlayerActivity.class);
                i.putExtra("SoundName", soundItem.getName());
                i.putExtra("SoundData", soundItem.getSoundData());
                i.putExtra("ImageData", soundItem.getPictureData());
                view.setClickable(true);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void setupCustomSounds(View view, float[] vols){
        view.setOnClickListener(new View.OnClickListener() {
            private long lastClickTime = 0;
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                view.setClickable(false);
                Intent i = new Intent(getApplicationContext(), CustomSoundActivity.class);
                i.putExtra("vol1", vols[0]);
                i.putExtra("vol2", vols[1]);
                i.putExtra("vol3", vols[2]);
                i.putExtra("vol4", vols[3]);
                i.putExtra("vol5", vols[4]);
                i.putExtra("vol6", vols[5]);
                i.putExtra("vol7", vols[6]);
                i.putExtra("vol8", vols[7]);
                view.setClickable(true);
                startActivity(i);
            }
        });
    }

}