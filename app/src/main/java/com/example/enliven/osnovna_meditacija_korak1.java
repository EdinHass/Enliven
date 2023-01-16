package com.example.enliven;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class osnovna_meditacija_korak1 extends Fragment {

    Button button1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view1=  inflater.inflate(R.layout.fragment_osnovna_meditacija_korak1, container, false);
        button1=view1.findViewById(R.id.buttonnext1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(), R.id.meditacijaFragmentView).navigate(R.id.action_osnovna_meditacija_korak1_to_vjezba_disanja2);
            }
        });
        return view1;
    }
}