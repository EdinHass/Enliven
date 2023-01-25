package com.example.enliven.ui.habits;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enliven.R;

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
        habits.add(new HabitsDomain("Ustani ranije", "brightness"));
        habits.add(new HabitsDomain("Meditiraj", "meditation"));
        habits.add(new HabitsDomain("Napravi krevet", "bed__1_"));
        habits.add(new HabitsDomain("Jutarnje vježbe", "exercise"));
        habits.add(new HabitsDomain("Operi zube", "brush_teeth"));


        adapter3  = new HabitsA1(habits);
        recyclerViewhabitslist1.setAdapter(adapter3);

    }



    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}