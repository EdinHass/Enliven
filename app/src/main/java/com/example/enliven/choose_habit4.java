package com.example.enliven;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class choose_habit4 extends AppCompatActivity {

    private RecyclerView.Adapter adapter2, adapter3;
    private RecyclerView recyclerViewhabitslist4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_habit4);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Izaberi naviku");

        recyclerViewhabits();
    }

    private void recyclerViewhabits() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewhabitslist4 = findViewById(R.id.recyclerView4);
        recyclerViewhabitslist4.setLayoutManager(linearLayoutManager);

        ArrayList<HabitsDomain> habits = new ArrayList<>();
        habits.add(new HabitsDomain("Vježbaj disanje", "heart"));
        habits.add(new HabitsDomain("Izađi napolje", "outdoors"));
        habits.add(new HabitsDomain("Prošetaj", "walk"));
        habits.add(new HabitsDomain("Meditiraj", "meditation"));


        adapter3  = new HabitsA4(habits);
        recyclerViewhabitslist4.setAdapter(adapter3);

    }



    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}