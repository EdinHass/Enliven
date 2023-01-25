package com.example.enliven.ui.sleep.meditacije;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.enliven.R;


public class meditacija_za_pocetnike extends Fragment {

    RelativeLayout med3, med4, med5;
    LinearLayout med1, med2;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.fragment_meditacija_za_pocetnike, container, false);

        med1=view1.findViewById(R.id.med1);
        med2=view1.findViewById(R.id.med2);
        med3=view1.findViewById(R.id.med3);
        med4=view1.findViewById(R.id.med4);
        med5=view1.findViewById(R.id.med5);

        med1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(),R.id.meditacijaFragmentView).navigate(R.id.action_meditacija_za_pocetnike_to_osnovna_meditacija);
            }
        });

        med2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(),R.id.meditacijaFragmentView).navigate(R.id.action_meditacija_za_pocetnike_to_meditacija_stres);
            }
        });

        med3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(),R.id.meditacijaFragmentView).navigate(R.id.action_meditacija_za_pocetnike_to_meditacija_prihvacanje_sebe);
            }
        });
        med4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(),R.id.meditacijaFragmentView).navigate(R.id.action_meditacija_za_pocetnike_to_meditacija_za_spavanje);
            }
        });

        med5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(),R.id.meditacijaFragmentView).navigate(R.id.action_meditacija_za_pocetnike_to_meditacija_za_raspolozenje);
            }
        });
        return view1;
    }
}