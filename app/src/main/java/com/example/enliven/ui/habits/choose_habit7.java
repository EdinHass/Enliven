package com.example.enliven.ui.habits;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.enliven.R;

import java.util.ArrayList;

public class choose_habit7 extends AppCompatActivity {

    private RecyclerView.Adapter adapter2, adapter3;
    private RecyclerView recyclerViewhabitslist7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_habit7);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Izaberi naviku");

        recyclerViewhabits();
    }

    private void recyclerViewhabits() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewhabitslist7= findViewById(R.id.recyclerView7);
        recyclerViewhabitslist7.setLayoutManager(linearLayoutManager);

        ArrayList<HabitsDomain> habits = new ArrayList<>();
        habits.add(new HabitsDomain("Zagrli svoje najdraže", "self_love"));
        habits.add(new HabitsDomain("Poklon", "gift"));
        habits.add(new HabitsDomain("Nazovi svoje nadraže", "phone"));
        habits.add(new HabitsDomain("Izađi sa društvom", "meeting"));
        habits.add(new HabitsDomain("Druženje sa porodicom", "family"));


        adapter3  = new HabitsA7(habits);
        recyclerViewhabitslist7.setAdapter(adapter3);

    }



    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}