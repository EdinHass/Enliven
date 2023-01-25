package com.example.enliven.ui.emotions;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.enliven.R;


public class tip_o_pisanju extends Fragment {


    private Button button_next3;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view1 = inflater.inflate(R.layout.fragment_tip_o_pisanju, container, false);

        button_next3=view1.findViewById(R.id.buttonnext1);
        button_next3.setText("Zapiši");

        button_next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), diaryactivity.class));
            }
        });

        return view1;
    }


}