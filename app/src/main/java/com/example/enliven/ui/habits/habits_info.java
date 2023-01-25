package com.example.enliven.ui.habits;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.enliven.R;

public class habits_info extends AppCompatActivity {

    private RelativeLayout infouvod;
    private RelativeLayout onavikama;
    private RelativeLayout funkcija;
    private RelativeLayout prepoznavanje;
    private RelativeLayout fokus;
    private RelativeLayout napredak;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habits_info);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Zašto navike?");

        infouvod = (RelativeLayout) findViewById(R.id.infouvod);
        infouvod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity1();
            }
        });

        onavikama = (RelativeLayout) findViewById(R.id.onavikama);
        onavikama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });

        funkcija = (RelativeLayout) findViewById(R.id.funkcija);
        funkcija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity3();
            }
        });

        prepoznavanje = (RelativeLayout) findViewById(R.id.prepoznavanje);
        prepoznavanje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity4();
            }
        });

        fokus = (RelativeLayout) findViewById(R.id.fokus);
        fokus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity5();
            }
        });

        napredak = (RelativeLayout) findViewById(R.id.napredak);
        napredak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity6();
            }
        });


    }

    public void openActivity1() {
        Intent intent = new Intent(this, info_uvod.class);
        startActivity(intent);
    }

    public void openActivity2() {
        Intent intent = new Intent(this, info_onavikama.class);
        startActivity(intent);
    }

    public void openActivity3() {
        Intent intent = new Intent(this, info_funkcija.class);
        startActivity(intent);
    }

    public void openActivity4() {
        Intent intent = new Intent(this, info_prepoznavanje.class);
        startActivity(intent);
    }

    public void openActivity5() {
        Intent intent = new Intent(this, info_fokus.class);
        startActivity(intent);
    }

    public void openActivity6() {
        Intent intent = new Intent(this, info_napredak.class);
        startActivity(intent);
    }




    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}