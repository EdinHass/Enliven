package com.example.enliven.ui.habits;

import static com.example.enliven.ui.UtilsKt.addXP;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enliven.ChangeNumberItemsListener;
import com.example.enliven.ui.addXP;

import java.util.ArrayList;


public class HabitsList<numbertxt> {

    private static ArrayList<HabitsDomain> habitsDomains;
    private static Context context;
    private static HabitHelperDB habitDB;

    private static TextView num;

    public HabitsList(Context context) {
        this.context = context;
        this.habitDB = new HabitHelperDB(context);
    }


    public static void insertHabit(HabitsDomain item) {
        ArrayList<HabitsDomain> listHabit = getHabitList();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listHabit.size(); i++) {
            if (listHabit.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                n = i;
                break;
            }
        }

        if (existAlready) {
            listHabit.get(n).setGoalnumber(item.getGoalnumber());
        } else {
            listHabit.add(item);
        }
        habitDB.putListObject("HabitList", listHabit);
        Toast.makeText(context, "Navika je dodana", Toast.LENGTH_SHORT).show();
    }

    public static ArrayList<HabitsDomain> getHabitList() {
        return habitDB.getListObject("HabitList");
    }


    public static void plusNumber(ArrayList<HabitsDomain> listHabit, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        listHabit.get(position).setGoalnumber(listHabit.get(position).getGoalnumber()+1);
        habitDB.putListObject("HabitList", listHabit);
        changeNumberItemsListener.changed();
    }


    public static void minusNumber(ArrayList<HabitsDomain> listHabit, int position, ChangeNumberItemsListener changeNumberItemsListener) {

        if (listHabit.get(position).getGoalnumber()==1) {
            listHabit.remove(position);
            addXP(10, context, ((Activity)context).getWindow().getDecorView().findViewById(android.R.id.content), addXP.HABITS);
        }
        else {
            listHabit.get(position).setGoalnumber(listHabit.get(position).getGoalnumber()-1);
        }

        habitDB.putListObject("HabitList", listHabit);
        changeNumberItemsListener.changed();
    }

}
