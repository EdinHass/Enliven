package com.example.enliven.ui.habits;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enliven.R;

import java.util.List;

public class ToDoA extends RecyclerView.Adapter<ToDoA.MyvViewHolder>{

    private List<ToDoModel> mList;
    private MainActivity2 activity;
    private DataBaseHelper myDB;

    public ToDoA(DataBaseHelper myDB , MainActivity2 activity) {
        this.activity = activity;
        this.myDB = myDB;
    }

    @NonNull
    @Override
    public MyvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task, parent, false);
        return new MyvViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyvViewHolder holder, int position) {
        final ToDoModel item=mList.get(position);
        holder.mCheckBox.setText(item.getTask());
        holder.mCheckBox.setChecked(toBoolean(item.getStatus()));
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()

        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    myDB.updateStatus(item.getId(), 1);

                }

            }

        });
    }


    public boolean toBoolean (int num) {
        return num!=0;
    }

    public Context getContex() {
        return activity;
    }

    public void setTasks(List<ToDoModel> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public void deleteTask(int position) {
        ToDoModel item = mList.get(position);
        myDB.deleteTask(item.getId());
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void editItem(int position) {
        ToDoModel item = mList.get(position);

        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTask());

        addhabit task = new addhabit();
        task.setArguments(bundle);
        task.show(activity.getSupportFragmentManager(), task.getTag());


    }

    @Override
    public int getItemCount() {
       return  mList.size();
    }

    public static class MyvViewHolder extends RecyclerView.ViewHolder {
        CheckBox mCheckBox;
        public MyvViewHolder(@NonNull View itemView) {
            super(itemView);
            mCheckBox = itemView.findViewById(R.id.mcheckbox);
        }
    }
}
