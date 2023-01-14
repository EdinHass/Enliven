package com.example.enliven;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class choose_habit1 extends AppCompatActivity {


    private RecyclerView.Adapter adapter2, adapter3;
    private RecyclerView recyclerViewhabitslist1;

    ConstraintLayout studyonline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_habit1);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Izaberi naviku");

        recyclerViewhabits();
    }

    private void recyclerViewhabits() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewhabitslist1 = findViewById(R.id.recyclerView1);
        recyclerViewhabitslist1.setLayoutManager(linearLayoutManager);

        ArrayList<HabitsDomain> habits = new ArrayList<>();
        habits.add(new HabitsDomain("nesto drugo", "icon_goal"));
        habits.add(new HabitsDomain("Igraj društvene igre", "icon_calendar"));
        habits.add(new HabitsDomain("Sklopi slagalicu", "icon_puzzle"));
        habits.add(new HabitsDomain("Trening", "icon_workout"));
        habits.add(new HabitsDomain("Nauči svirati instrument", "icon_instruments"));
        habits.add(new HabitsDomain("Probaj novi recept", "icon_recipe"));
        habits.add(new HabitsDomain("Karaoke", "icon_karaoke"));
        habits.add(new HabitsDomain("Očisti kuću", "icon_tidy"));
        habits.add(new HabitsDomain("Zalij cvijeće", "icon_plants"));
        habits.add(new HabitsDomain("Operi veš", "icon_laundry"));

        adapter3  = new HabitsA1(habits);
        recyclerViewhabitslist1.setAdapter(adapter3);

    }



    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}