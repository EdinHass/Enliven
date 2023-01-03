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

    RelativeLayout Rain1;
    LinearLayout recommended;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card1activity);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Zvukovi");

        Rain1 = findViewById(R.id.cardRain1);
        recommended = findViewById(R.id.recommended);
        setupSound(recommended.getChildAt(0), new SoundItem("Summer Rain", R.raw.rain1, R.drawable.rain1));
        setupSound(recommended.getChildAt(1), new SoundItem("River", R.raw.river1, R.drawable.river1));


    }

    public void setupSound(View view, SoundItem soundItem){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SoundsPlayerActivity.class);
                i.putExtra("SoundName", soundItem.getName());
                i.putExtra("SoundData", (int)soundItem.getSoundData());
                i.putExtra("ImageData", (int)soundItem.getPictureData());
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