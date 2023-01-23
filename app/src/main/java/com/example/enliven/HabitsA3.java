package com.example.enliven;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HabitsA3 extends RecyclerView.Adapter<HabitsA.ViewHolder> {
    ArrayList<HabitsDomain> habitsDomains;

    public HabitsA3(ArrayList<HabitsDomain> habitsDomains) {
        this.habitsDomains = habitsDomains;
    }

    @Override
    public HabitsA.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_habit,parent, false);
        return new HabitsA.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitsA.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.habitName.setText(habitsDomains.get(position).getTitle());
        String picUrl = "";
        switch (position) {
            case 0: {
                picUrl = "no_fast_food";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.addhabit_background));
                break;
            }
            case 1: {
                picUrl = "sugar_free";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.addhabit_background));
                break;
            }
            case 2: {
                picUrl = "no_drink";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.addhabit_background));
                break;
            }
            case 3: {
                picUrl = "no_smoking";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.addhabit_background));
                break;
            }
            case 4: {
                picUrl = "vitamins";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.addhabit_background));
                break;
            }
            case 5: {
                picUrl = "water";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.addhabit_background));
                break;
            }
            case 6: {
                picUrl = "fruits";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.addhabit_background));
                break;
            }

        }


        int drawbleResourceId=holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawbleResourceId)
                .into(holder.habitPic);

        holder.addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), showdetail_habit.class);
                intent.putExtra("object", habitsDomains.get(position));
                holder.itemView.getContext().startActivity(intent);

            }
        });

    }



    @Override
    public int getItemCount() {
        return habitsDomains.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView habitName, addbtn, postavicilj;
        ImageView habitPic;
        ConstraintLayout mainLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            habitName = itemView.findViewById(R.id.habitName);
            habitPic = itemView.findViewById(R.id.habitPic);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            addbtn = itemView.findViewById(R.id.addbtn);

        }
    }
}
