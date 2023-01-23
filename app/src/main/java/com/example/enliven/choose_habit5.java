package com.example.enliven;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class choose_habit5 extends AppCompatActivity {

    private RecyclerView.Adapter adapter2, adapter3;
    private RecyclerView recyclerViewhabitslist5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_habit5);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Izaberi naviku");

        recyclerViewhabits();
    }

    private void recyclerViewhabits() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewhabitslist5 = findViewById(R.id.recyclerView5);
        recyclerViewhabitslist5.setLayoutManager(linearLayoutManager);

        ArrayList<HabitsDomain> habits = new ArrayList<>();
        habits.add(new HabitsDomain("Slušaj podcast", "headphone"));
        habits.add(new HabitsDomain("Napiši nešto novo", "creative_writing"));
        habits.add(new HabitsDomain("Volontiraj", "volunteer"));
        habits.add(new HabitsDomain("Probaj nešto novo", "newskill"));
        habits.add(new HabitsDomain("Crtaj", "color_palette"));
        habits.add(new HabitsDomain("Uči novi jezik", "global"));


        adapter3  = new HabitsA5(habits);
        recyclerViewhabitslist5.setAdapter(adapter3);

    }



    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}