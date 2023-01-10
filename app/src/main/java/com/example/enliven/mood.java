package com.example.enliven;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.enliven.databinding.FragmentDashboardBinding;
import com.example.enliven.databinding.FragmentMoodBinding;


public class mood extends Fragment {

    RelativeLayout sreca;
    RelativeLayout tuga;
    RelativeLayout ljutnja;
    RelativeLayout strah;
    RelativeLayout stres;
    RelativeLayout anksioznost;

    private FragmentMoodBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                                ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMoodBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        sreca=root.findViewById(R.id.textsreca1);
        tuga=root.findViewById(R.id.texttuga1);
        ljutnja=root.findViewById(R.id.textljutnja1);
        strah=root.findViewById(R.id.textstrah1);
        stres=root.findViewById(R.id.textstres1);
        anksioznost=root.findViewById(R.id.textanksioznost1);
        tuga=root.findViewById(R.id.texttuga1);




        tuga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(), R.id.general_tips_fragmentView).navigate(R.id.action_mood_to_tuga_tips);

            }
        });

        sreca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.general_tips_fragmentView).navigate(R.id.action_mood_to_sreca_tips);


            }
        });



        ljutnja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.general_tips_fragmentView).navigate(R.id.action_mood_to_ljutnja_tips);

            }
        });

        strah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.general_tips_fragmentView).navigate(R.id.action_mood_to_strah_tips);

            }
        });

        stres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.general_tips_fragmentView).navigate(R.id.action_mood_to_stres_tips);

            }
        });

        anksioznost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.general_tips_fragmentView).navigate(R.id.action_mood_to_anksioznost_tips);

            }
        });


return root;
    }

}