package com.example.enliven;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class vjezba_disanja_za_opusatnje extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view1= inflater.inflate(R.layout.fragment_vjezba_disanja_za_opusatnje, container, false);
        TextView saznajvise=view1.findViewById(R.id.saznajvise);
        saznajvise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(), R.id.vjezbedisanja_fragmentView).navigate(R.id.action_vjezba_disanja_za_opusatnje_to_vjezba_disanja_opustanje_info);
            }
        });
                return view1;
    }
}