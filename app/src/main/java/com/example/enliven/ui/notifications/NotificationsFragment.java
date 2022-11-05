package com.example.enliven.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.enliven.MainActivity;
import com.example.enliven.R;
import com.example.enliven.card1activity;
import com.example.enliven.databinding.FragmentNotificationsBinding;

import java.sql.CallableStatement;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private RelativeLayout card1Sleep, card2Sleep;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        card1Sleep = (RelativeLayout) root.findViewById(R.id.card1Sleep);
        card2Sleep = (RelativeLayout) root.findViewById(R.id.card2Sleep);
        card1Sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main).navigate(R.id.action_navigation_notifications_to_card1activity);
            }
        });
        TextView zapocniText = (TextView) root.findViewById(R.id.textViewZapocni);

        long compare = GregorianCalendar.getInstance().get(Calendar.HOUR_OF_DAY)*3600 + GregorianCalendar.getInstance().get(Calendar.MINUTE)*60;
        SharedPreferences prefs = getActivity().getSharedPreferences("com.example.enliven", Context.MODE_PRIVATE);
        int sleepHours = prefs.getInt("sleepHours", 8);
        if((prefs.getLong("SleepTime", 0)+sleepHours*3600)/86400!=0) {
            if ((prefs.getLong("SleepTime", 0L) < compare) || (compare < (prefs.getLong("SleepTime", 0) + 8 * 3600) % 86400)) {
                zapocniText.setText("Laku noć, " + prefs.getString("UserName", "invalid") + ".");
            } else {
                zapocniText.setText("Zdravo, " + prefs.getString("UserName", "invalid") + ".");
            }
        }else{
            if ((prefs.getLong("SleepTime", 0L) < compare) && (compare < prefs.getLong("SleepTime", 0) + 8 * 3600)){
                zapocniText.setText("Laku noć, " + prefs.getString("UserName", "invalid") + ".");
            } else {
                zapocniText.setText("Zdravo, " + prefs.getString("UserName", "invalid") + ".");
            }
        }

        long hours = prefs.getLong("SleepTime", 0L) / 3600;
        long minutes = (prefs.getLong("SleepTime", 0L) % 3600) / 60;
        String sleepTimeString = formatTime(hours, minutes);
        TextView currTime = root.findViewById(R.id.currentSleepTime);
        currTime.setText(sleepTimeString);

        long wakeUpTime = (prefs.getLong("SleepTime", 0)+sleepHours*3600)%86400;
        String wakeUpTimeString = formatTime(wakeUpTime/3600, (wakeUpTime%3600)/60);
        TextView wakeupTime = root.findViewById(R.id.wakeUpTimeText);
        wakeupTime.setText(wakeUpTimeString);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public String formatTime(long hours, long minutes){
        String time = "";
        if(hours<10){
            time = "0";
            time += String.valueOf(hours);
        }else{
            time = String.valueOf(hours);
        }

        time += ":";
        if(minutes<10){
            time += "0";
        }
        time += String.valueOf(minutes);

        return time;
    }
}