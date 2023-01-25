package com.example.enliven.ui.sleep.sounds;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.enliven.R;

public class sounds_category_nature extends AppCompatActivity {

    RelativeLayout planinskiamb, zima;
    LinearLayout recomm, list1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sounds_category_nature);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Kategorija: Priroda");
        planinskiamb = findViewById(R.id.planinskiamb);
        zima = findViewById(R.id.zima);
        recomm = findViewById(R.id.recommended);
        list1 = findViewById(R.id.list1);
        setupSound(planinskiamb, new SoundItem("Planinski Amb.", "https://cdn.pixabay.com/download/audio/2021/10/30/audio_6ad89ce378.mp3?filename=birds-in-the-forest-wind-noise-of-leaves-10088.mp3", R.drawable.mountain_ambient));
        setupSound(zima, new SoundItem("Zima", "https://cdn.pixabay.com/download/audio/2022/10/24/audio_eea51edd92.mp3?filename=fireplace-fire-crackling-loop-123930.mp3", R.drawable.zima_sound));
        setupSound(recomm.getChildAt(0), new SoundItem("Okean", "https://cdn.pixabay.com/download/audio/2021/09/06/audio_37aad22374.mp3?filename=sandy-beach-calm-waves-water-nature-sounds-8052.mp3", R.drawable.ocean_sound));
        setupSound(recomm.getChildAt(1), new SoundItem("Bujna Šuma", "https://cdn.pixabay.com/download/audio/2022/02/07/audio_c8aee41cb8.mp3?filename=soft-woods-crickets-plus-plane-d100-may-9th-2020-16703.mp3", R.drawable.forest_sound));
        setupSound(recomm.getChildAt(2), new SoundItem("Mistična Šuma", "https://cdn.pixabay.com/download/audio/2022/03/09/audio_83741f0f8c.mp3?filename=mystic-forest-ambient-23812.mp3", R.drawable.mythforest_sound));
        setupSound(recomm.getChildAt(3), new SoundItem("Lagani Povjetarac", "https://cdn.pixabay.com/download/audio/2022/01/18/audio_ec17691f64.mp3?filename=a-gentle-breeze-wind-4-14681.mp3", R.drawable.wind_sound));
        setupSound(recomm.getChildAt(4), new SoundItem("Topla Noć", "https://cdn.pixabay.com/download/audio/2021/08/09/audio_9a2f521fc5.mp3?filename=ambience-night-field-cricket-01-7015.mp3", R.drawable.night_sound));

        setupSound(list1.getChildAt(0), new SoundItem("Ljeto", "https://cdn.pixabay.com/download/audio/2021/08/09/audio_165a149ae7.mp3?filename=gentle-ocean-waves-birdsong-and-gull-7109.mp3", R.drawable.summer_sound));
        setupSound(list1.getChildAt(1), new SoundItem("Jesen", "https://cdn.pixabay.com/download/audio/2022/03/11/audio_cf520d2e95.mp3?filename=mountain-forest-winter-amb-trees-creak-light-wind-banff-190107-49062.mp3", R.drawable.fall_sound));
        setupSound(list1.getChildAt(2), new SoundItem("Proljeće", "https://cdn.pixabay.com/download/audio/2021/08/09/audio_6b294070f5.mp3?filename=forest-with-small-river-birds-and-nature-field-recording-6735.mp3", R.drawable.spring_sound));

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
}

