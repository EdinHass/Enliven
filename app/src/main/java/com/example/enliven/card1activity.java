package com.example.enliven;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class card1activity extends AppCompatActivity {

    RelativeLayout Rain1, soundsinfo;
    LinearLayout recommended;
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

        cats.getChildAt(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), sounds_category_nature.class));
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
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SoundsPlayerActivity.class);
                i.putExtra("SoundName", soundItem.getName());
                i.putExtra("SoundData", soundItem.getSoundData());
                i.putExtra("ImageData", soundItem.getPictureData());
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}