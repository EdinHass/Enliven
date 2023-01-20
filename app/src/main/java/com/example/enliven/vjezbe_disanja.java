package com.example.enliven;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;


public class vjezbe_disanja extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.fragment_vjezbe_disanja, container, false);
        RelativeLayout vjezbeZaSan=view1.findViewById(R.id.zasan);
        RelativeLayout vjezbeZaOpustanje=view1.findViewById(R.id.zaopustanje);
        RelativeLayout vjezbeZaFokus=view1.findViewById(R.id.zafokus);
        RelativeLayout vjezbeZaRazvnotezu=view1.findViewById(R.id.zaravnotezu);

        vjezbeZaSan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(), R.id.vjezbedisanja_fragmentView).navigate(R.id.action_vjezbe_disanja_to_vjezba_disanja_za_san);
            }
        });

        vjezbeZaOpustanje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(), R.id.vjezbedisanja_fragmentView).navigate(R.id.action_vjezbe_disanja_to_vjezba_disanja_za_opusatnje);
            }
        });
        vjezbeZaFokus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(), R.id.vjezbedisanja_fragmentView).navigate(R.id.action_vjezbe_disanja_to_vjezbe_disanja_za_fokus);
            }
        });

        vjezbeZaRazvnotezu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(), R.id.vjezbedisanja_fragmentView).navigate(R.id.action_vjezbe_disanja_to_vjezba_disanja_za_ravnotezu);
            }
        });

        return view1;
    }
}