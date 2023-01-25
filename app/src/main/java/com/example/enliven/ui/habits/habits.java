package com.example.enliven.ui.habits;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.enliven.R;
import com.example.enliven.ui.habits.choose_habit;
import com.example.enliven.ui.habits.choose_habit1;
import com.example.enliven.ui.habits.choose_habit2;
import com.example.enliven.ui.habits.choose_habit3;
import com.example.enliven.ui.habits.choose_habit4;
import com.example.enliven.ui.habits.choose_habit5;
import com.example.enliven.ui.habits.choose_habit6;
import com.example.enliven.ui.habits.choose_habit7;
import com.example.enliven.ui.habits.choose_habit8;

public class habits extends AppCompatActivity {


    private ConstraintLayout boravakkodkuce;
    private ConstraintLayout jutarnjarutina;
    private ConstraintLayout nocnarutina;
    private ConstraintLayout zdravlje;
    private ConstraintLayout mentalnoz;
    private ConstraintLayout ucenje;
    private ConstraintLayout fa;
    private ConstraintLayout ljubav;
    private ConstraintLayout finansije;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habits);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Navike");

        boravakkodkuce = (ConstraintLayout) findViewById(R.id.boravakkodkuce);
        boravakkodkuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });

        jutarnjarutina = (ConstraintLayout) findViewById(R.id.jutarnjarutina);
        jutarnjarutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity1();
            }
        });

        nocnarutina = (ConstraintLayout) findViewById(R.id.nocnarutina);
        nocnarutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });

        mentalnoz = (ConstraintLayout) findViewById(R.id.mentalnoz);
        mentalnoz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity3();
            }
        });

        zdravlje = (ConstraintLayout) findViewById(R.id.zdravlje);
        zdravlje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity4();
            }
        });


        ucenje = (ConstraintLayout) findViewById(R.id.učenje);
        ucenje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity5();
            }
        });

        fa = (ConstraintLayout) findViewById(R.id.fizickaaktivnost);
        fa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity6();
            }
        });

        ljubav = (ConstraintLayout) findViewById(R.id.ljubav);
        ljubav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity7();
            }
        });

        finansije = (ConstraintLayout) findViewById(R.id.finansije);
        finansije.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity8();
            }
        });
    }

    public void openActivity() {
        Intent intent = new Intent(this, choose_habit.class);
        startActivity(intent);
    }

    public void openActivity1() {
        Intent intent = new Intent(this, choose_habit1.class);
        startActivity(intent);
    }

    public void openActivity2() {
        Intent intent = new Intent(this, choose_habit2.class);
        startActivity(intent);
    }

    public void openActivity3() {
        Intent intent = new Intent(this, choose_habit4.class);
        startActivity(intent);
    }

    public void openActivity4() {
        Intent intent = new Intent(this, choose_habit3.class);
        startActivity(intent);
    }

    public void openActivity5() {
        Intent intent = new Intent(this, choose_habit5.class);
        startActivity(intent);
    }

    public void openActivity6() {
        Intent intent = new Intent(this, choose_habit6.class);
        startActivity(intent);
    }

    public void openActivity7() {
        Intent intent = new Intent(this, choose_habit7.class);
        startActivity(intent);
    }

    public void openActivity8() {
        Intent intent = new Intent(this, choose_habit8.class);
        startActivity(intent);
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

