package com.example.enliven.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.enliven.HabitsDomain;
import com.example.enliven.HabitHelperDB;
import com.example.enliven.R;
import com.example.enliven.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private LinearLayout habit;
    private LinearLayout task;
    private RelativeLayout profile;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


       habit = root.findViewById(R.id.cardhabit);
       task = root.findViewById(R.id.cardtodo);
       TextView pocetniText = root.findViewById(R.id.textpocetni);
       profile = root.findViewById(R.id.profile);


        Animation floatUpFast = AnimationUtils.loadAnimation(getActivity(),R.anim.animation_bottom_lighter);
        Animation floatDown = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_top_light);
        Animation slideoutright = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_from_right_light);
        pocetniText.startAnimation(floatDown);
        habit.startAnimation(floatUpFast);
        task.startAnimation(floatUpFast);
        profile.startAnimation(floatUpFast);


      habit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main).navigate(R.id.action_navigation_home_to_main_habits);
            }
        });

        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main).navigate(R.id.action_navigation_home_to_MainActivity2);
            }
        });

        SharedPreferences sprefs = getActivity().getSharedPreferences("com.example.enliven", Context.MODE_PRIVATE);
        if(sprefs.getString("UserName", "invalid").equals("invalid")){
            pocetniText.setText( "Dobrodošli!");
        }else{
            pocetniText.setText( "Šta radiš, " + sprefs.getString("UserName", "invalid") + "?");
        }


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}