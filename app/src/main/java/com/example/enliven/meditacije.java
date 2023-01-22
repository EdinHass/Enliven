package com.example.enliven;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class meditacije extends Fragment {

   private RelativeLayout zastoMeditacije, vjezbaDisanja, MeditacijaZaSpavanje, MeditacijaZaPocetnike, VjezbaDisanjaZaSan, VjezbaDisanjaZaOpustanje;
   private LinearLayout meditacijaZaPocetnike;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.fragment_meditacije, container, false);

        zastoMeditacije=view1.findViewById(R.id.zastomeditacija);
        meditacijaZaPocetnike=view1.findViewById(R.id.meditacija);
        vjezbaDisanja=view1.findViewById(R.id.cardvjezbaDisanja);
        MeditacijaZaSpavanje=view1.findViewById(R.id.meditacijaZaSpavanje);
        MeditacijaZaPocetnike=view1.findViewById(R.id.MeditacijaZaPocetnike);
        VjezbaDisanjaZaOpustanje=view1.findViewById(R.id.VjezbaDisanjaZaOpustanje);
        VjezbaDisanjaZaSan=view1.findViewById(R.id.VjezbaDisanjaZaSan);

        MeditacijaZaPocetnike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(),R.id.meditacijaFragmentView).navigate(R.id.action_meditacije_to_meditacija_za_pocetnike);
            }
        });

        MeditacijaZaSpavanje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(),R.id.meditacijaFragmentView).navigate(R.id.action_meditacije_to_meditacija_za_spavanje);
            }
        });

        VjezbaDisanjaZaSan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(),R.id.meditacijaFragmentView).navigate(R.id.action_meditacije_to_vjezba_disanja_za_san2);
            }
        });

        VjezbaDisanjaZaOpustanje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(),R.id.meditacijaFragmentView).navigate(R.id.action_meditacije_to_vjezba_disanja_za_opusatnje2);
            }
        });

        zastoMeditacije.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(),R.id.meditacijaFragmentView).navigate(R.id.action_meditacije_to_dialog_meditacija);
            }
        });

        meditacijaZaPocetnike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(),R.id.meditacijaFragmentView).navigate(R.id.action_meditacije_to_meditacija_za_pocetnike);
            }
        });

        vjezbaDisanja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), vjezbe_disanjaa.class);
                startActivity(intent);
            }
        });



        return view1;
    }

}