package com.example.enliven;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class sounds_category_rain extends AppCompatActivity {

    RelativeLayout kisanakrovu, kisaigrmljavina;
    LinearLayout recomm, list1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sounds_category_rain);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Kategorija: Kiša");
        kisanakrovu = findViewById(R.id.kisanakrovu);
        kisaigrmljavina = findViewById(R.id.kisaigrmljavina);
        recomm = findViewById(R.id.recommended);
        list1 = findViewById(R.id.list1);
        setupSound(kisanakrovu, new SoundItem("Kiša na krovu", "https://cdn.pixabay.com/download/audio/2021/09/13/audio_0e194e2a98.mp3?filename=rain-on-roof-or-window-nature-sounds-8312.mp3", R.drawable.rain_on_roof_sound));
        setupSound(kisaigrmljavina, new SoundItem("Kiša i grmljavina", "https://cdn.pixabay.com/download/audio/2021/08/29/audio_00b71708cf.mp3?filename=rain-and-thunder-nature-sounds-7803.mp3", R.drawable.rain_and_storm_sound1));
        setupSound(recomm.getChildAt(0), new SoundItem("Pljusak", "https://cdn.pixabay.com/download/audio/2021/09/09/audio_7f28a3d36b.mp3?filename=heavy-rain-nature-sounds-8186.mp3", R.drawable.heavy_rain_sound));
        setupSound(recomm.getChildAt(1), new SoundItem("Blaga kiša", "https://cdn.pixabay.com/download/audio/2022/04/16/audio_520eb6a5cc.mp3?filename=light-rain-109591.mp3", R.drawable.light_rain_sound));
        setupSound(recomm.getChildAt(2), new SoundItem("Kišovit dan", "https://cdn.pixabay.com/download/audio/2022/09/30/audio_0103d3e0ee.mp3?filename=rainy-day_nature-sound-121434.mp3", R.drawable.rainy_day_sound));
        setupSound(recomm.getChildAt(3), new SoundItem("Zvuk kiše", "https://cdn.pixabay.com/download/audio/2022/02/22/audio_889da4cda1.mp3?filename=rain-noise-for-sleep-21436.mp3", R.drawable.zvuk_kise_sound));
        setupSound(recomm.getChildAt(4), new SoundItem("Opuštajuća kiša", "hhttps://cdn.pixabay.com/download/audio/2021/09/10/audio_9fc587e78d.mp3?filename=relaxing-rain-8228.mp3", R.drawable.opustajuca_kisa_sound));

        setupSound(list1.getChildAt(0), new SoundItem("Oluja", "https://cdn.pixabay.com/download/audio/2022/06/13/audio_99cdd8330a.mp3?filename=rain-and-thunder-113218.mp3", R.drawable.oluja_sound));
        setupSound(list1.getChildAt(1), new SoundItem("Epska oluja", "https://cdn.pixabay.com/download/audio/2022/03/25/audio_f3ed2e72d2.mp3?filename=epic-storm-thunder-rainwindwaves-no-loops-106800.mp3", R.drawable.epic_storm_sound));
        setupSound(list1.getChildAt(2), new SoundItem("Ponoćna oluja", "https://cdn.pixabay.com/download/audio/2021/10/02/audio_e81cd1f4a6.mp3?filename=midnight-storm-in-suburban-environment-8944.mp3", R.drawable.midnight_storm_sound));

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