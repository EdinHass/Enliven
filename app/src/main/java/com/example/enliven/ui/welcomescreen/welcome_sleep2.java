package com.example.enliven.ui.welcomescreen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.example.enliven.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link welcome_sleep2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class welcome_sleep2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public welcome_sleep2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment welcome_sleep2.
     */
    // TODO: Rename and change types and number of parameters
    public static welcome_sleep2 newInstance(String param1, String param2) {
        welcome_sleep2 fragment = new welcome_sleep2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.fragment_welcome_sleep2, container, false);

        RelativeLayout card1, card2, card3;
        card1 = view1.findViewById(R.id.SixHourPlanCard);
        card2 = view1.findViewById(R.id.SevenHourPlanCard);
        card3 = view1.findViewById(R.id.EightHourPlanCard);

        Animation slideLeft = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_from_left_slow);
        Animation slideRight = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_from_right_slow);


        view1.findViewById(R.id.SixHourContainer).startAnimation(slideLeft);
        view1.findViewById(R.id.SevenHourContainer).startAnimation(slideRight);
        view1.findViewById(R.id.EightHourContainer).startAnimation(slideLeft);

        SharedPreferences prefs = getActivity().getSharedPreferences("com.example.enliven", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt("sleepHours", 6);
                editor.putBoolean("firstrun", false);
                editor.apply();
                getActivity().finish();
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt("sleepHours", 7);
                editor.putBoolean("firstrun", false);
                editor.apply();
                getActivity().finish();
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt("sleepHours", 8);
                editor.putBoolean("firstrun", false);
                editor.apply();
                getActivity().finish();
            }
        });

        return view1;
    }
}