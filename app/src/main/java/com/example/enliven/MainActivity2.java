package com.example.enliven;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity2 extends AppCompatActivity implements OnDialogCloseListener{


    private RecyclerView mRecycleview;
    private TextView btn;
    private DataBaseHelper myDB;
    private List<ToDoModel> mList;
    private ToDoA adapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("To-Do List");



        mRecycleview = findViewById(R.id.rv);
        btn= findViewById(R.id.addbtn);
        myDB = new DataBaseHelper(MainActivity2.this);
        mList = new ArrayList<>();
        adapter = new ToDoA(myDB, MainActivity2.this);

        mRecycleview.setHasFixedSize(true);
        mRecycleview.setLayoutManager(new LinearLayoutManager(this ));
        mRecycleview.setAdapter(adapter);

        mList = myDB.getAllTasks();
        Collections.reverse(mList);
        adapter.setTasks(mList);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addhabit.newInstance().show(getSupportFragmentManager(), addhabit.TAG);
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerViewTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(mRecycleview);


      }



    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        mList = myDB.getAllTasks();
        Collections.reverse(mList);
        adapter.setTasks(mList);
        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}