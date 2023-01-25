package com.example.enliven.ui.emotions;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.enliven.R;


public class MoodActivity extends AppCompatActivity {
      RelativeLayout sreca;
      RelativeLayout tuga;
      RelativeLayout ljutnja;
      RelativeLayout strah;
      RelativeLayout stres;
      RelativeLayout anksioznost;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Osjećanje");



        sreca=findViewById(R.id.textsreca1);
        tuga=findViewById(R.id.texttuga1);
        ljutnja=findViewById(R.id.textljutnja1);
        strah=findViewById(R.id.textstrah1);
        stres=findViewById(R.id.textstres1);
        anksioznost=findViewById(R.id.textanksioznost1);
        tuga=findViewById(R.id.texttuga1);




        tuga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), sreca_tips.class);
                startActivity(i);

            }
        });

        sreca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), sreca_tips.class);
                startActivity(i);


            }
        });



        ljutnja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ljutnja_tips.class);
                startActivity(i);

            }
        });

        strah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), strah_tips.class);
                startActivity(i);

            }
        });

        stres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), stres_tips.class);
                startActivity(i);

            }
        });

        anksioznost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), anksioznost_tips.class);
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