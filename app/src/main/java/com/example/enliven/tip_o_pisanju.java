package com.example.enliven;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.enliven.databinding.FragmentGeneralTipsBinding;


public class tip_o_pisanju extends Fragment {


    private Button button_next3;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view1 = inflater.inflate(R.layout.fragment_tip_o_pisanju, container, false);

        button_next3=view1.findViewById(R.id.buttonnext3);

        button_next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(),R.id.general_tips_fragmentView).navigate(R.id.action_tip_o_pisanju_to_diaryactivity2);
            }
        });

        return view1;
    }


}