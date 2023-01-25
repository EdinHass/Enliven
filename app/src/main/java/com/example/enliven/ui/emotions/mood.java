package com.example.enliven.ui.emotions;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.enliven.R;
import com.example.enliven.databinding.FragmentDashboardBinding;
import com.example.enliven.databinding.FragmentMoodBinding;


public class mood extends Fragment {

    RelativeLayout sreca;
    RelativeLayout tuga;
    RelativeLayout ljutnja;
    RelativeLayout strah;
    RelativeLayout stres;
    RelativeLayout anksioznost;
    Button button;

    private FragmentMoodBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.fragment_mood, container, false);


        sreca = view1.findViewById(R.id.textsreca1);
        tuga = view1.findViewById(R.id.texttuga1);
        ljutnja = view1.findViewById(R.id.textljutnja1);
        strah = view1.findViewById(R.id.textstrah1);
        stres = view1.findViewById(R.id.textstres1);
        anksioznost = view1.findViewById(R.id.textanksioznost1);
        tuga = view1.findViewById(R.id.texttuga1);
        button = view1.findViewById(R.id.button2);

        button.setVisibility(View.INVISIBLE);


        tuga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setVisibility(View.VISIBLE);
                resetall();
                tuga.setBackgroundTintList(getResources().getColorStateList(R.color.Pallete1, getActivity().getTheme()));

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Navigation.findNavController(view1).navigate(R.id.action_mood_to_tuga_tips);
                    }
                });


            }
        });

        sreca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setVisibility(View.VISIBLE);
                resetall();
                sreca.setBackgroundTintList(getResources().getColorStateList(R.color.Pallete1, getActivity().getTheme()));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Navigation.findNavController(view1).navigate(R.id.action_mood_to_sreca_tips);
                    }
                });


            }
        });


        ljutnja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setVisibility(View.VISIBLE);
                resetall();
                ljutnja.setBackgroundTintList(getResources().getColorStateList(R.color.Pallete1, getActivity().getTheme()));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Navigation.findNavController(view1).navigate(R.id.action_mood_to_ljutnja_tips);
                    }
                });


            }
        });

        strah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setVisibility(View.VISIBLE);
                resetall();
                strah.setBackgroundTintList(getResources().getColorStateList(R.color.Pallete1, getActivity().getTheme()));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Navigation.findNavController(view1).navigate(R.id.action_mood_to_strah_tips);
                    }
                });


            }
        });

        stres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setVisibility(View.VISIBLE);
                resetall();
                stres.setBackgroundTintList(getResources().getColorStateList(R.color.Pallete1, getActivity().getTheme()));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Navigation.findNavController(view1).navigate(R.id.action_mood_to_stres_tips);

                    }
                });


            }
        });

        anksioznost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setVisibility(View.VISIBLE);
                resetall();
                anksioznost.setBackgroundTintList(getResources().getColorStateList(R.color.Pallete1, getActivity().getTheme()));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Navigation.findNavController(view1).navigate(R.id.action_mood_to_anksioznost_tips);
                    }
                });


            }
        });


        return view1;
    }

    void resetall() {
        tuga.setBackgroundTintList(getResources().getColorStateList(R.color.transparent, getActivity().getTheme()));
        sreca.setBackgroundTintList(getResources().getColorStateList(R.color.transparent, getActivity().getTheme()));
        anksioznost.setBackgroundTintList(getResources().getColorStateList(R.color.transparent, getActivity().getTheme()));
        strah.setBackgroundTintList(getResources().getColorStateList(R.color.transparent, getActivity().getTheme()));
        stres.setBackgroundTintList(getResources().getColorStateList(R.color.transparent, getActivity().getTheme()));
        ljutnja.setBackgroundTintList(getResources().getColorStateList(R.color.transparent, getActivity().getTheme()));
    }


}