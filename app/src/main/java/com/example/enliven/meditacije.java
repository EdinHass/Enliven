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

   private RelativeLayout zastoMeditacije, vjezbaDisanja, interaktivnemeditacije;
   private LinearLayout meditacijaZaPocetnike;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.fragment_meditacije, container, false);

        zastoMeditacije=view1.findViewById(R.id.zastomeditacija);
        meditacijaZaPocetnike=view1.findViewById(R.id.meditacija);
        vjezbaDisanja=view1.findViewById(R.id.cardvjezbaDisanja);
        interaktivnemeditacije=view1.findViewById(R.id.interaktivnemeditacije);

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
                Navigation.findNavController(getActivity(),R.id.meditacijaFragmentView).navigate(R.id.action_meditacije_to_vjezba_disanja);
            }
        });
        interaktivnemeditacije.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), osnovna_meditacija_korak1.class);
                i.putExtra("Okean", "https://cdn.pixabay.com/download/audio/2021/09/06/audio_37aad22374.mp3?filename=sandy-beach-calm-waves-water-nature-sounds-8052.mp3");
                startActivity(i);
            }
        });


        return view1;
    }

}