package com.example.enliven;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class vjezbe_disanja_intro3 extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.fragment_vjezbe_disanja_intro3, container, false);
        Button buttonNext=view1.findViewById(R.id.buttonnext1);
        Button buttonPrev=view1.findViewById(R.id.buttonback1);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_vjezbe_disanja_intro3_to_vjezbe_disanja);
            }
        });

        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_vjezbe_disanja_intro3_to_vjezbe_disanja_intro2);
            }
        });

        return view1;
    }
}