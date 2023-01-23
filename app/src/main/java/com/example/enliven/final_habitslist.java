package com.example.enliven;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class final_habitslist extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private HabitsList habitsList;
    private ScrollView scrollView;
    TextView emptyTxt;
    ItemTouchHelper itemTouchHelper;
    private ArrayList<HabitsDomain> habitsDomains;
    private FinalHabitsListA adapterr;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_habitslist);
        habitsList = new HabitsList(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Lista navika");

        initView();
        initList();

    }

    public void openActivity4() {
        Intent intent = new Intent(this, habits.class);
        startActivity(intent);
    }



    private void initView() {
        recyclerViewList = findViewById(R.id.recycleviewfinall);
        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView2);
    }

    private void initList () {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new FinalHabitsListA(HabitsList.getHabitList(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {

            }
        });

        recyclerViewList.setAdapter(adapter);
        if (HabitsList.getHabitList().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }
        else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


}