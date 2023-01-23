package com.example.enliven;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class choose_habit extends AppCompatActivity {

    private RecyclerView.Adapter adapter2, adapter3;
    private RecyclerView recyclerViewhabitslist;

    ConstraintLayout studyonline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_habit);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Izaberi naviku");

        recyclerViewhabits();
    }

    private void recyclerViewhabits() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewhabitslist = findViewById(R.id.recyclerView);
        recyclerViewhabitslist.setLayoutManager(linearLayoutManager);

        ArrayList<HabitsDomain> habits = new ArrayList<>();
        habits.add(new HabitsDomain("Online učenje", "icon_laptop"));
        habits.add(new HabitsDomain("Igraj društvene igre", "ic_boardgame"));
        habits.add(new HabitsDomain("Sklopi slagalicu", "ic_puzzle"));
        habits.add(new HabitsDomain("Trening", "ic_fit"));
        habits.add(new HabitsDomain("Sviraj instrument", "ic_instruments2"));
        habits.add(new HabitsDomain("Probaj novi recept", "ic_recipe"));
        habits.add(new HabitsDomain("Karaoke", "ic_karaoke"));
        habits.add(new HabitsDomain("Očisti kuću", "ic_cleanhouse2"));
        habits.add(new HabitsDomain("Zalij cvijeće", "ic_plants"));
        habits.add(new HabitsDomain("Operi veš", "ic_laundry2"));

        adapter2  =new HabitsA(habits);
        recyclerViewhabitslist.setAdapter(adapter2);

    }



    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}


