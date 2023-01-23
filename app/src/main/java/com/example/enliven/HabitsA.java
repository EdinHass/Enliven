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

public class HabitsA extends RecyclerView.Adapter<HabitsA.ViewHolder> {

    ArrayList<HabitsDomain>habitsDomains;

    public HabitsA(ArrayList<HabitsDomain> habitsDomains) {
        this.habitsDomains = habitsDomains;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_habit,parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.habitName.setText(habitsDomains.get(position).getTitle());
        String picUrl = "";
        switch (position) {
            case 0: {
                picUrl = "icon_laptop";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.addhabit_background));
                break;
            }
            case 1: {
                picUrl = "ic_boardgame";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.addhabit_background));
                break;
            }
            case 2: {
                picUrl = "ic_puzzle";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.addhabit_background));
                break;
            }
            case 3: {
                picUrl = "ic_fit";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.addhabit_background));
                break;
            }
            case 4: {
                picUrl = "ic_instruments2";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.addhabit_background));
                break;
            }
            case 5: {
                picUrl = "ic_recipe";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.addhabit_background));
                break;
            }
            case 6: {
                picUrl = "ic_karaoke";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.addhabit_background));
                break;
            }
            case 7: {
                picUrl = "ic_cleanhouse2";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.addhabit_background));
                break;
            }
            case 8: {
                picUrl = "ic_plants";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.addhabit_background));
                break;
            }
            case 9: {
                picUrl = "ic_laundry2";
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
