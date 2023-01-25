package com.example.enliven.ui.emotions;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.enliven.R;

public class general_tip2 extends Fragment {

    private Button button_next1;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view1 = inflater.inflate(R.layout.fragment_general_tip2, container, false);

        button_next1=view1.findViewById(R.id.buttonnext1);

        button_next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.general_tips_fragmentView).navigate(R.id.action_general_tip2_to_general_tip3);
            }
        });

        return view1;
    }
}
