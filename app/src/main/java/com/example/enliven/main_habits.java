package com.example.enliven;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class main_habits extends AppCompatActivity {

    private RelativeLayout dodajnaviku;
    private RelativeLayout listanavika;
    private RelativeLayout habitsinfo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_habits);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Navike");

        dodajnaviku = (RelativeLayout) findViewById(R.id.dodajnaviku);
        dodajnaviku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });

        listanavika = (RelativeLayout) findViewById(R.id.listanavika);
        listanavika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity1();
            }
        });

        habitsinfo = (RelativeLayout) findViewById(R.id.habitsinfo);
        habitsinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity3();
            }
        });
    }


    public void openActivity2() {
        Intent intent = new Intent(this, habits.class);
        startActivity(intent);
    }

    public void openActivity1() {
        Intent intent = new Intent(this, final_habitslist.class);
        startActivity(intent);
    }

    public void openActivity3() {
        Intent intent = new Intent(this, habits_info.class);
        startActivity(intent);
    }




    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}