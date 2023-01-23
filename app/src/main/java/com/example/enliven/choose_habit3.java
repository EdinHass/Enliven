package com.example.enliven;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class choose_habit3 extends AppCompatActivity {

    private RecyclerView.Adapter adapter2, adapter3;
    private RecyclerView recyclerViewhabitslist3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_habit3);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Izaberi naviku");

        recyclerViewhabits();
    }

    private void recyclerViewhabits() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewhabitslist3 = findViewById(R.id.recyclerView3);
        recyclerViewhabitslist3.setLayoutManager(linearLayoutManager);

        ArrayList<HabitsDomain> habits = new ArrayList<>();
        habits.add(new HabitsDomain("Ograniči nezdravu hranu", "no_fast_food"));
        habits.add(new HabitsDomain("Ograniči šećer", "sugar_free"));
        habits.add(new HabitsDomain("Ograniči kofein", "no_drink"));
        habits.add(new HabitsDomain("Ograniči cigarete", "no_smoking"));
        habits.add(new HabitsDomain("Uzmi vitamine", "vitamins"));
        habits.add(new HabitsDomain("Pij vodu", "water"));
        habits.add(new HabitsDomain("Jedi voće i povrće", "fruits"));

        adapter3  = new HabitsA3(habits);
        recyclerViewhabitslist3.setAdapter(adapter3);

    }



    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}