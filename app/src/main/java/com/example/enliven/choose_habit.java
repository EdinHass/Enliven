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
        habits.add(new HabitsDomain("Igraj društvene igre", "icon_boardgame"));
        habits.add(new HabitsDomain("Sklopi slagalicu", "icon_puzzle"));
        habits.add(new HabitsDomain("Trening", "icon_workout"));
        habits.add(new HabitsDomain("Nauči svirati instrument", "icon_instruments"));
        habits.add(new HabitsDomain("Probaj novi recept", "icon_recipe"));
        habits.add(new HabitsDomain("Karaoke", "icon_karaoke"));
        habits.add(new HabitsDomain("Očisti kuću", "icon_tidy"));
        habits.add(new HabitsDomain("Zalije cvijeće", "icon_plants"));
        habits.add(new HabitsDomain("Operi veš", "icon_laundry"));

        adapter2  =new HabitsA(habits);
        recyclerViewhabitslist.setAdapter(adapter2);

    }



    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}


