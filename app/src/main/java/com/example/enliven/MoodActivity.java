package com.example.enliven;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.enliven.databinding.ActivityMoodBinding;
import com.example.enliven.databinding.FragmentDashboardBinding;

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

        sreca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), sreca_tips.class));
            }
        });

        tuga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), tugaa_tips.class));
            }
        });

        ljutnja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ljutnja_tips.class));
            }
        });

        strah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), strah_tips.class));
            }
        });

        stres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), stres_tips.class));
            }
        });

        anksioznost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), anksioznost_tips.class));
            }
        });



    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}