package com.example.enliven;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class sounds_category_space extends AppCompatActivity {

    RelativeLayout meduzvjezdaniamb, nlo;
    LinearLayout recomm, list1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sounds_category_space);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Kategorija: Svemir");

        meduzvjezdaniamb=findViewById(R.id.meduzvjezdaniamb);
        nlo=findViewById(R.id.let);
        recomm=findViewById(R.id.recommended);
        list1=findViewById(R.id.list1);


        setupSound(meduzvjezdaniamb, new SoundItem("Međuzvjezdani Amb.", "https://cdn.pixabay.com/download/audio/2021/09/11/audio_d40f2dfa1d.mp3?filename=interstellar-science-fiction-sounds-8239.mp3", R.drawable.interstellar_sound1));
        setupSound(nlo, new SoundItem("NLO let", "https://cdn.pixabay.com/download/audio/2022/03/14/audio_bf6c2ef2e5.mp3?filename=epp-ufo-flight-65106.mp3", R.drawable.nlo1_sound1));
        setupSound(recomm.getChildAt(0), new SoundItem("Andromeda", "https://cdn.pixabay.com/download/audio/2021/10/21/audio_d891ea7488.mp3?filename=andromeda-voices-from-another-galaxy-9690.mp3", R.drawable.andromeda_sound1));
        setupSound(recomm.getChildAt(1), new SoundItem("U svemiru", "https://cdn.pixabay.com/download/audio/2022/02/07/audio_f008bec1aa.mp3?filename=astronaut-child-in-space-17141.mp3", R.drawable.space1_sound1));
        setupSound(recomm.getChildAt(2), new SoundItem("Zvukovi svemira", "https://cdn.pixabay.com/download/audio/2022/07/07/audio_4145d6737e.mp3?filename=voyageur-galactic-02-114568.mp3", R.drawable.space2_sound1));
        setupSound(recomm.getChildAt(3), new SoundItem("Galaksija", "https://cdn.pixabay.com/download/audio/2022/03/13/audio_a975ed008e.mp3?filename=interstellar-57135.mp3", R.drawable.galaxy_sound1));


        setupSound(list1.getChildAt(0), new SoundItem("NLO slijatenje", "https://cdn.pixabay.com/download/audio/2022/03/15/audio_fd460aa5a4.mp3?filename=ufo-landing-93632.mp3", R.drawable.nlo2_sound1));
        setupSound(list1.getChildAt(1), new SoundItem("NLO kruženje","https://cdn.pixabay.com/download/audio/2021/08/09/audio_78ea2964c1.mp3?filename=spaceship-cruising-ufo-7176.mp3", R.drawable.nlo3_sound1));
        setupSound(list1.getChildAt(2), new SoundItem("Krstarenje svemirom","https://cdn.pixabay.com/download/audio/2022/03/15/audio_54f0e46f75.mp3?filename=ufos-over-antarctica-77442.mp3",R.drawable.nlo4_sound1));

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