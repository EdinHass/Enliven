package com.example.enliven.ui.welcomescreen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import com.example.enliven.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link welcome_sleep#newInstance} factory method to
 * create an instance of this fragment.
 */
public class welcome_sleep extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public welcome_sleep() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment welcome_sleep.
     */
    // TODO: Rename and change types and number of parameters
    public static welcome_sleep newInstance(String param1, String param2) {
        welcome_sleep fragment = new welcome_sleep();
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
        View view1 = inflater.inflate(R.layout.fragment_welcome_sleep, container, false);
        Button buttonPrev = view1.findViewById(R.id.buttonPrev);
        Button buttonNext = view1.findViewById(R.id.buttonNext);
        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_welcome_sleep_to_welcome_screen_info1);
            }
        });
        SharedPreferences prefs = getActivity().getSharedPreferences("com.example.enliven", Context.MODE_PRIVATE);
        if(!prefs.getBoolean("firstrun", true)){
            view1.findViewById(R.id.buttonPrev).setVisibility(View.GONE);
        }
        TimePicker vrijeme = view1.findViewById(R.id.timeSelect);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long vr = 0;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    vr = vrijeme.getHour() * 3600L + vrijeme.getMinute() * 60L;
                }
                SharedPreferences.Editor editor = prefs.edit();
                editor.putLong("SleepTime", vr);
                editor.apply();
                Navigation.findNavController(view).navigate(R.id.action_welcome_sleep_to_welcome_sleep2);
            }
        });
        return view1;
    }
}