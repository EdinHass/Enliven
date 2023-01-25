package com.example.enliven.ui.habits;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.enliven.ChangeNumberItemsListener;
import com.example.enliven.R;

import java.util.ArrayList;

public class FinalHabitsListA extends RecyclerView.Adapter<FinalHabitsListA.ViewHolder> {

    private ArrayList<HabitsDomain> habitsDomains;
    private HabitsList habitsList;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public FinalHabitsListA(ArrayList<HabitsDomain> habitsDomains, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.habitsDomains = habitsDomains;
        this.habitsList = new HabitsList(context) ;
        this.changeNumberItemsListener = changeNumberItemsListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.final_habitslist_holder,parent, false);




        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(habitsDomains.get(position).getTitle());
        holder.num.setText(String.valueOf(habitsDomains.get(position).getGoalnumber()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(habitsDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.piclist);


        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HabitsList.minusNumber(habitsDomains, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });
    }


    @Override
    public int getItemCount() {

        return habitsDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView piclist, minusItem;
        public TextView num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titlelist);
            piclist = itemView.findViewById(R.id.piclist);
            num = itemView.findViewById(R.id.numberItemTxt);
            minusItem = itemView.findViewById(R.id.minuslistbtn);

        }
    }
}
