package com.example.enliven;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.enliven.databinding.FragmentTugaaTipsBinding;


public class tugaa_tips extends Fragment {

private RelativeLayout button_next;
private FragmentTugaaTipsBinding binding;




    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       binding = FragmentTugaaTipsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        button_next=(RelativeLayout) root.findViewById(R.id.buttonnext);

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.moodActivity3).navigate(R.id.action_tugaa_tips_to_tuga_tip2);
            }
        });

return root;
    }
}