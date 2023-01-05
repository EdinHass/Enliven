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


public class tip_o_pisanju extends Fragment {


    private RelativeLayout button_next3;
    private FragmentTugaaTipsBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTugaaTipsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        button_next3=(RelativeLayout) root.findViewById(R.id.buttonnext3);

        button_next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.moodActivity3).navigate(R.id.action_tip_o_pisanju_to_diaryactivity3);
            }
        });

        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}