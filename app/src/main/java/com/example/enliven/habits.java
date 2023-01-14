package com.example.enliven;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class habits extends AppCompatActivity {


    private ConstraintLayout trendinghabits;
    private ConstraintLayout badhabits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habits);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Meditacije");

        trendinghabits = (ConstraintLayout) findViewById(R.id.trendinghabits);
        trendinghabits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });

        badhabits = (ConstraintLayout) findViewById(R.id.badhabits);
        badhabits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity3();
            }
        });
    }

    public void openActivity2() {
        Intent intent = new Intent(this, choose_habit.class);
        startActivity(intent);
    }

    public void openActivity3() {
        Intent intent = new Intent(this, choose_habit1.class);
        startActivity(intent);
    }



    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

