package com.example.enliven.ui.dashboard;

import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.enliven.MoodActivity;
import com.example.enliven.R;
import com.example.enliven.MainActivity;
import  com.example.enliven.diaryactivity;
import com.example.enliven.databinding.FragmentDashboardBinding;

import org.w3c.dom.Text;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private ConstraintLayout dnevnikcard, osjecanjecard;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();





        dnevnikcard = (ConstraintLayout) root.findViewById(R.id.dnevnikcard);
        osjecanjecard = (ConstraintLayout) root.findViewById(R.id.osjecanjecard);

        osjecanjecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main).navigate(R.id.action_navigation_dashboard_to_moodActivity);

            }
        });

        dnevnikcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main).navigate(R.id.action_navigation_dashboard_to_diaryactivity);
            }
        });

        TextView pocetniText = (TextView) root.findViewById(R.id.textpocetni);
        SharedPreferences sprefs = getActivity().getSharedPreferences("com.example.enliven", Context.MODE_PRIVATE);
        pocetniText.setText( "Zdravo,"+ sprefs.getString("UserName", "invalid") + "!");




        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}