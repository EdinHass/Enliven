package com.example.enliven;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class sounds_category_music extends AppCompatActivity {

    RelativeLayout akusticnagitara, softpiano;
    LinearLayout recomm, list1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sounds_category_music);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Kategorija: Muzika");
        akusticnagitara = findViewById(R.id.akusticnagitara);
        softpiano = findViewById(R.id.softpiano);
        recomm = findViewById(R.id.recommended);
        list1 = findViewById(R.id.list1);
        setupSound(akusticnagitara, new SoundItem("Akusticna gitara", "https://cdn.pixabay.com/download/audio/2022/12/07/audio_b511e7db7a.mp3?filename=relaxing-guitar-music-volume-2-128532.mp3", R.drawable.akusticna_gitara_sound));
        setupSound(softpiano, new SoundItem("Nježni klavir", "https://cdn.pixabay.com/download/audio/2022/03/15/audio_2f32d1e983.mp3?filename=soft-piano-72454.mp3", R.drawable.piano_sound1));
        setupSound(recomm.getChildAt(0), new SoundItem("Gitara", "https://cdn.pixabay.com/download/audio/2022/03/14/audio_72761cbb1a.mp3?filename=simple-relaxing-guitar-loop-60828.mp3", R.drawable.gitara_sound));
        setupSound(recomm.getChildAt(1), new SoundItem("Violina", "https://cdn.pixabay.com/download/audio/2022/05/16/audio_ad37bb9464.mp3?filename=eilegeiya-111391.mp3", R.drawable.violina_sound));
        setupSound(recomm.getChildAt(2), new SoundItem("Žalosna violina", "https://cdn.pixabay.com/download/audio/2022/03/15/audio_a14e4323c7.mp3?filename=mournful-violin2-77967.mp3", R.drawable.zalosna_violina_sound));
        setupSound(recomm.getChildAt(3), new SoundItem("Džez", "https://cdn.pixabay.com/download/audio/2022/03/14/audio_ec774acb0e.mp3?filename=late-jazz-piano-pure-62449.mp3", R.drawable.jazz_sound));
        setupSound(recomm.getChildAt(4), new SoundItem("Violina i klavir", "https://cdn.pixabay.com/download/audio/2022/03/09/audio_9d10759f65.mp3?filename=piano_violin-freesound-26340.mp3", R.drawable.violin_piano_sound));

        setupSound(list1.getChildAt(0), new SoundItem("Umirujući klavir", "https://cdn.pixabay.com/download/audio/2021/08/09/audio_ad0a86b2e0.mp3?filename=peaceful-piano-loop-6903.mp3", R.drawable.piano_sound2));
        setupSound(list1.getChildAt(1), new SoundItem("Lagani klavir", "https://cdn.pixabay.com/download/audio/2022/08/01/audio_443d7e42ea.mp3?filename=warm-piano-logo-116098.mp3", R.drawable.piano_sound3));
        setupSound(list1.getChildAt(2), new SoundItem("Hronologija ljubavi", "https://cdn.pixabay.com/download/audio/2021/08/04/audio_b7c7a225e4.mp3?filename=chonology-of-love-6094.mp3", R.drawable.piano_sound4));
        setupSound(list1.getChildAt(3),new SoundItem("Ljetna noć","https://cdn.pixabay.com/download/audio/2021/08/09/audio_c4d98b122c.mp3?filename=summer-night-piano-solo-6885.mp3",R.drawable.piano_sound5));

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