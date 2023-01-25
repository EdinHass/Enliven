package com.example.enliven.ui.habits;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.enliven.R;

import java.util.ArrayList;

public class choose_habit8 extends AppCompatActivity {

    private RecyclerView.Adapter adapter2, adapter3;
    private RecyclerView recyclerViewhabitslist8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_habit8);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Izaberi naviku");

        recyclerViewhabits();
    }

    private void recyclerViewhabits() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewhabitslist8= findViewById(R.id.recyclerView8);
        recyclerViewhabitslist8.setLayoutManager(linearLayoutManager);

        ArrayList<HabitsDomain> habits = new ArrayList<>();
        habits.add(new HabitsDomain("Doniraj", "donation"));
        habits.add(new HabitsDomain("Plati račune", "bills"));
        habits.add(new HabitsDomain("Plan potrošnje", "plan"));


        adapter3  = new HabitsA8(habits);
        recyclerViewhabitslist8.setAdapter(adapter3);

    }



    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}