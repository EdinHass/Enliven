package com.example.enliven;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;

public class showdetail_habit extends AppCompatActivity {

    private TextView addBtn;
    private TextView titleTxt, numbertxt, numberpom;
    private ImageView plusBtn, minusBtn, pic1;
    private HabitsDomain object;
    int numberputa=1;
    private HabitsList habitsList;
    private TextView add;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdetail_habit);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Dodaj naviku");

        habitsList = new HabitsList(this);
        initView();
        getBundle();


    }




    private void getBundle() {
        object = (HabitsDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());
        Glide.with(this)
            .load(drawableResourceId)
                .into(pic1);

        titleTxt.setText(object.getTitle());
        numbertxt.setText(String.valueOf(numberputa));


        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberputa = numberputa+1;
                numbertxt.setText(String.valueOf(numberputa));

            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberputa>1) {
                    numberputa=numberputa-1;

                }
                numbertxt.setText(String.valueOf(numberputa));
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setGoalnumber(numberputa);
                HabitsList.insertHabit(object);
            }
        });

    }

    private void initView() {
        addBtn = findViewById(R.id.add);
        titleTxt = findViewById(R.id.titleTxt);
        numbertxt = findViewById(R.id.number);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        pic1 = findViewById(R.id.pichabit);

    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}