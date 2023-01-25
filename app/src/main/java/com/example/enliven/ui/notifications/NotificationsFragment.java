package com.example.enliven.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.enliven.databinding.FragmentNotificationsBinding;
import com.example.enliven.ui.sleep.sounds.CustomSoundActivity;
import com.example.enliven.R;
import com.example.enliven.ui.sleep.sounds.SoundItem;
import com.example.enliven.ui.sleep.sounds.SoundsPlayerActivity;
import com.example.enliven.ui.welcomescreen.Welcome_Activity;
import com.example.enliven.ui.sleep.meditacije.meditacija;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private RelativeLayout card1Sleep, card2Sleep, cardMeditacije;
    TextView timeUntil1, timeUntil2, zapocniText;
    private View changeBtn;
    private View root;
    private View infoPlane;
    private View wakeupTimePlane;
    private View favorites;
    private View infoPlane2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        zapocniText = root.findViewById(R.id.textViewZapocni);
        timeUntil1 = root.findViewById(R.id.timeUntil1);
        timeUntil2 = root.findViewById(R.id.timeUntil2);
        card1Sleep = root.findViewById(R.id.card1Sleep);
        infoPlane = root.findViewById(R.id.infoPlane);
        wakeupTimePlane = root.findViewById(R.id.wakeUpTimePlane);
        favorites = root.findViewById(R.id.favorites);
        infoPlane2 = root.findViewById(R.id.infoPlane2);
        cardMeditacije=root.findViewById(R.id.cardMeditacije);
        //card2Sleep = root.findViewById(R.id.card2Sleep);
        card1Sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main).navigate(R.id.action_navigation_notifications_to_card1activity);
            }
        });
        cardMeditacije.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), meditacija.class);
                startActivity(intent);
            }
        });
        setupCustomSounds(infoPlane2, new float[]{0.5f, 0.5f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f});
        Animation floatUp = AnimationUtils.loadAnimation(getActivity(),R.anim.slide_out_bottom);
        Animation floatUpFast = AnimationUtils.loadAnimation(getActivity(),R.anim.slide_out_bottom_light);
        Animation floatDown = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_top_light);
        infoPlane.startAnimation(floatUpFast);
        card1Sleep.startAnimation(floatUpFast);
        wakeupTimePlane.startAnimation(floatUpFast);
        favorites.startAnimation(floatUpFast);
        zapocniText.startAnimation(floatDown);
        changeBtn = root.findViewById(R.id.changeBtn);
        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Welcome_Activity.class);
                intent.putExtra("changeSleep", true);
                startActivity(intent);
            }
        });

        setupTimeTextViews();

        setupFavorites();



        return root;
    }

    private void setupFavorites() {
        SharedPreferences prefs = getActivity().getSharedPreferences("com.example.enliven", Context.MODE_PRIVATE);


        binding.recommended.removeAllViews();


        Set<String> emp = new HashSet<String>();
        Set<String> currentFavs = prefs.getStringSet("favorites", emp);

        if(currentFavs.isEmpty()){
            binding.getRoot().findViewById(R.id.noFavs).setVisibility(View.VISIBLE);
            return;
        }else{
            binding.getRoot().findViewById(R.id.noFavs).setVisibility(View.GONE);
        }

        int i = 0;
        for (Iterator<String> it = currentFavs.iterator(); it.hasNext(); ) {
            String temp = it.next();
            String[] curr = temp.split(",");
            LinearLayout card = (LinearLayout) View.inflate(requireContext(), R.layout.sounds_card_layout, binding.recommended);
            TextView Text = card.getChildAt(i).findViewById(R.id.cardText);
            Text.setText(curr[0]);
            card.setClickable(true);
            setupSound(card.getChildAt(i), new SoundItem(curr[0], curr[1], Integer.parseInt(curr[2])));

            i++;
        }





    }

    @Override
    public void onResume() {
        super.onResume();
        setupTimeTextViews();
        setupFavorites();
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

    public String formatTime2(long hours, long minutes){
        String time = "";
        time = hours + "h " + minutes + "min";
        return time;
    }

    private void setupTimeTextViews(){
        long compare = GregorianCalendar.getInstance().get(Calendar.HOUR_OF_DAY)*3600 + GregorianCalendar.getInstance().get(Calendar.MINUTE)*60;
        SharedPreferences prefs = getActivity().getSharedPreferences("com.example.enliven", Context.MODE_PRIVATE);
        int sleepHours = prefs.getInt("sleepHours", 8);
        long sleepTime = prefs.getLong("SleepTime", 0);
        if((sleepTime+sleepHours*3600L)/86400L!=0) {
            if ((sleepTime < compare) || (compare < (sleepTime + sleepHours * 3600) % 86400)) {
                zapocniText.setText("Laku noć, " + prefs.getString("UserName", "invalid") + ".");
                timeUntil2.setText("do buđenja");
                long timeBetween = Math.abs(((sleepTime+3600*sleepHours)% 86400)-compare);
                timeUntil1.setText(formatTime2(timeBetween/3600, (timeBetween%3600)/60));
                Toast.makeText(getContext(), "Napomena: Vrijeme je za spavanje!", Toast.LENGTH_SHORT).show();
            } else {
                zapocniText.setText("Zdravo, " + prefs.getString("UserName", "invalid") + ".");
                timeUntil2.setText("do spavanja");
                long timeBetween = sleepTime - compare;
                if(timeBetween<0){
                    timeBetween = 24*3600 - Math.abs(timeBetween);
                }
                timeUntil1.setText(formatTime2(timeBetween/3600, (timeBetween%3600)/60));
            }
        }else{
            if ((sleepTime < compare) && (compare < sleepTime + sleepHours * 3600)){
                zapocniText.setText("Laku noć, " + prefs.getString("UserName", "invalid") + ".");
                timeUntil2.setText("do buđenja");
                long timeBetween = Math.abs((sleepTime+3600*sleepHours)-compare);
                timeUntil1.setText(formatTime2(timeBetween/3600, (timeBetween%3600)/60));
                Toast.makeText(getContext(), "Napomena: Vrijeme je za spavanje!", Toast.LENGTH_SHORT).show();
            } else {
                zapocniText.setText("Zdravo, " + prefs.getString("UserName", "invalid") + ".");
                timeUntil2.setText("do spavanja");
                long timeBetween = sleepTime - compare;
                if(timeBetween<0){
                    timeBetween = 24*3600 - Math.abs(timeBetween);
                }
                timeUntil1.setText(formatTime2(timeBetween/3600, (timeBetween%3600)/60));
            }
        }

        long hours = prefs.getLong("SleepTime", 0L) / 3600;
        long minutes = (prefs.getLong("SleepTime", 0L) % 3600) / 60;
        String sleepTimeString = formatTime(hours, minutes);
        TextView currTime = root.findViewById(R.id.currentSleepTime);
        currTime.setText(sleepTimeString);

        long wakeUpTime = (prefs.getLong("SleepTime", 0)+sleepHours*3600L)%86400;
        String wakeUpTimeString = formatTime(wakeUpTime/3600, (wakeUpTime%3600)/60);
        TextView wakeupTime = root.findViewById(R.id.wakeUpTimeText);
        wakeupTime.setText(wakeUpTimeString);
    }

    public void setupCustomSounds(View view, float[] vols){
        view.setOnClickListener(new View.OnClickListener() {
            private long lastClickTime = 0;
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                view.setClickable(false);
                Intent i = new Intent(getActivity(), CustomSoundActivity.class);
                i.putExtra("vol1", vols[0]);
                i.putExtra("vol2", vols[1]);
                i.putExtra("vol3", vols[2]);
                i.putExtra("vol4", vols[3]);
                i.putExtra("vol5", vols[4]);
                i.putExtra("vol6", vols[5]);
                i.putExtra("vol7", vols[6]);
                i.putExtra("vol8", vols[7]);
                view.setClickable(true);
                startActivity(i);
            }
        });
    }

    public void setupSound(View view, SoundItem soundItem){
        view.setOnClickListener(new View.OnClickListener() {
            private long lastClickTime = 0;
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
                view.setClickable(false);
                Intent i = new Intent(getContext(), SoundsPlayerActivity.class);
                i.putExtra("SoundName", soundItem.getName());
                i.putExtra("SoundData", soundItem.getSoundData());
                i.putExtra("ImageData", soundItem.getPictureData());
                view.setClickable(true);
                startActivity(i);
            }
        });
    }
}