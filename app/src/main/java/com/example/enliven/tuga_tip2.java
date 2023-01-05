package com.example.enliven;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.enliven.databinding.FragmentTugaaTipsBinding;

public class tuga_tip2 extends Fragment {

    private RelativeLayout button_next1;
    private FragmentTugaaTipsBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTugaaTipsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        button_next1=(RelativeLayout) root.findViewById(R.id.buttonnext1);

        button_next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.moodActivity3).navigate(R.id.action_tuga_tip2_to_tuga_tip3);
            }
        });

        return root;
    }
}