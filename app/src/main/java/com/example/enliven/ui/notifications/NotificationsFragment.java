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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.enliven.MainActivity;
import com.example.enliven.R;
import com.example.enliven.card1activity;
import com.example.enliven.data.StreamTokenApi;
import com.example.enliven.data.UserExtra;
import com.example.enliven.databinding.FragmentNotificationsBinding;
import com.example.enliven.ui.auth.AuthActivity;
import com.example.enliven.ui.auth.SetupProfileFragment;
import com.example.enliven.ui.auth.StreamTokenProvider;
import com.example.enliven.ui.chat.ChatActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.sql.CallableStatement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.models.User;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private RelativeLayout card1Sleep, card2Sleep;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        card1Sleep = root.findViewById(R.id.card1Sleep);
        card2Sleep = root.findViewById(R.id.card2Sleep);
        card1Sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main).navigate(R.id.action_navigation_notifications_to_card1activity);
            }
        });




        TextView zapocniText = root.findViewById(R.id.textViewZapocni);

        long compare = GregorianCalendar.getInstance().get(Calendar.HOUR_OF_DAY)*3600 + GregorianCalendar.getInstance().get(Calendar.MINUTE)*60;
        SharedPreferences prefs = getActivity().getSharedPreferences("com.example.enliven", Context.MODE_PRIVATE);
        int sleepHours = prefs.getInt("sleepHours", 8);
        long sleepTime = prefs.getLong("SleepTime", 0);
        if((sleepTime+sleepHours*3600L)/86400L!=0) {
            if ((sleepTime < compare) || (compare < (sleepTime + sleepHours * 3600) % 86400)) {
                zapocniText.setText("Laku noć, " + prefs.getString("UserName", "invalid") + ".");
                Toast.makeText(getContext(), "Napomena: Vrijeme je za spavanje!", Toast.LENGTH_SHORT).show();
            } else {
                zapocniText.setText("Zdravo, " + prefs.getString("UserName", "invalid") + ".");
            }
        }else{
            if ((sleepTime < compare) && (compare < sleepTime + sleepHours * 3600)){
                zapocniText.setText("Laku noć, " + prefs.getString("UserName", "invalid") + ".");
                Toast.makeText(getContext(), "Napomena: Vrijeme je za spavanje!", Toast.LENGTH_SHORT).show();
            } else {
                zapocniText.setText("Zdravo, " + prefs.getString("UserName", "invalid") + ".");
            }
        }
        Animation animationTopLight = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_out_top_light);
        zapocniText.startAnimation(animationTopLight);
        Animation animationSide = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_from_left_slow);
        Animation animationBottomLight = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_out_bottom_light);
        Animation animationFade = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fade_in_slow);
        root.findViewById(R.id.informationLayout).startAnimation(animationSide);
        root.findViewById(R.id.mainCards).startAnimation(animationBottomLight);
        root.findViewById(R.id.linearLayout2).startAnimation(animationFade);

        long hours = prefs.getLong("SleepTime", 0L) / 3600;
        long minutes = (prefs.getLong("SleepTime", 0L) % 3600) / 60;
        String sleepTimeString = formatTime(hours, minutes);
        TextView currTime = root.findViewById(R.id.currentSleepTime);
        currTime.setText(sleepTimeString);

        long wakeUpTime = (prefs.getLong("SleepTime", 0)+sleepHours*3600L)%86400;
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