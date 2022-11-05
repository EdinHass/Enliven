package com.example.enliven.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.enliven.R;
import com.example.enliven.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ConstraintLayout dnevnikcard, osjecanjecard;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        getActivity().setContentView(R.layout.fragment_home);
        dnevnikcard = (ConstraintLayout) root.findViewById(R.id.dnevnikcard);
        osjecanjecard = (ConstraintLayout) root.findViewById(R.id.osjecanjecard);
        TextView textpocetni = (TextView) getActivity().findViewById(R.id.textpocetni);
        SharedPreferences prefs = getActivity().getSharedPreferences("com.example.enliven", Context.MODE_PRIVATE);

        textpocetni.setText("Kako se danas osjećaš?");


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}