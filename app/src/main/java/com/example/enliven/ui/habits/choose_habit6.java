package com.example.enliven.ui.habits;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.enliven.R;

import java.util.ArrayList;

public class choose_habit6 extends AppCompatActivity {

    private RecyclerView.Adapter adapter2, adapter3;
    private RecyclerView recyclerViewhabitslist6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_habit6);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Izaberi naviku");

        recyclerViewhabits();
    }

    private void recyclerViewhabits() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewhabitslist6 = findViewById(R.id.recyclerView6);
        recyclerViewhabitslist6.setLayoutManager(linearLayoutManager);

        ArrayList<HabitsDomain> habits = new ArrayList<>();
        habits.add(new HabitsDomain("Vježbe istezanja", "stretching"));
        habits.add(new HabitsDomain("Joga", "yoga"));
        habits.add(new HabitsDomain("Plivanje", "swimming"));
        habits.add(new HabitsDomain("Vožnja bicikla", "bicycle"));
        habits.add(new HabitsDomain("Teretana", "gym"));
        habits.add(new HabitsDomain("Trčanje", "runner"));


        adapter3  = new HabitsA6(habits);
        recyclerViewhabitslist6.setAdapter(adapter3);

    }



    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}