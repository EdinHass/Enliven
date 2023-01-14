package com.example.enliven;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class sounds_category_ambijent extends AppCompatActivity {

    RelativeLayout kaficiamb, fireplace;
    LinearLayout recomm, list1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sounds_category_ambijent);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Kategorija: Ambijent");

        kaficiamb=findViewById(R.id.kaficamb);
        fireplace=findViewById(R.id.kamin);
        recomm=findViewById(R.id.recommended);
        list1=findViewById(R.id.list1);

        setupSound(kaficiamb, new SoundItem("Kafic Amb.", "https://cdn.pixabay.com/download/audio/2022/03/09/audio_ac6b883ce7.mp3?filename=coffee-shop-ambience-27829.mp3", R.drawable.coffee_shop_sound1));
        setupSound(fireplace, new SoundItem("Kamin", "https://cdn.pixabay.com/download/audio/2021/09/06/audio_8c5b16b7b6.mp3?filename=crackling-fireplace-nature-sounds-8012.mp3", R.drawable.fireplace_sound1));
        setupSound(recomm.getChildAt(0), new SoundItem("Biblioteka", "https://cdn.pixabay.com/download/audio/2022/03/13/audio_b2677456da.mp3?filename=warsaw-university-library-58740.mp3", R.drawable.biblioteka_sound1));
        setupSound(recomm.getChildAt(1), new SoundItem("Grad", "https://cdn.pixabay.com/download/audio/2022/03/14/audio_1cf9a56d0d.mp3?filename=car-street-noise-68104.mp3", R.drawable.grad_sound));
        setupSound(recomm.getChildAt(2), new SoundItem("Tržni centar", "https://cdn.pixabay.com/download/audio/2022/03/14/audio_f0eae07bc2.mp3?filename=in-the-mall-by-the-fountain-59502.mp3", R.drawable.trzni_centar_sound));
        setupSound(recomm.getChildAt(3), new SoundItem("Škola", "https://cdn.pixabay.com/download/audio/2022/03/09/audio_7a1bd36a56.mp3?filename=pencil-29272.mp3", R.drawable.skola_sound));


        setupSound(list1.getChildAt(0), new SoundItem("Mjehurići", "https://cdn.pixabay.com/download/audio/2022/01/18/audio_4bf02d9d82.mp3?filename=bubbles-14830.mp3", R.drawable.mjehurici_sound));
        setupSound(list1.getChildAt(1), new SoundItem("Otkucavanje sata","https://cdn.pixabay.com/download/audio/2022/03/09/audio_4b7bb9d4c1.mp3?filename=ticking-clock_1-27477.mp3", R.drawable.otkucavanje_sata_sound1));
        setupSound(list1.getChildAt(2), new SoundItem("Pranje ruku","https://cdn.pixabay.com/download/audio/2022/03/10/audio_2b5ec30ccd.mp3?filename=washing-hands-in-kitchen-sink-30759.mp3",R.drawable.pranje_ruku_sound1));
        setupSound(list1.getChildAt(3), new SoundItem("Sječenje povrća", "https://cdn.pixabay.com/download/audio/2022/03/13/audio_0614063894.mp3?filename=vegetables-peeling-55056.mp3",R.drawable.sjecenje_povrca_sound));
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