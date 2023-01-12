package com.example.enliven;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ljutnja_tips extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.fragment_ljutnja_tips, container, false);

        Button button = view1.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NotesEditorActivity.class);
                intent.putExtra("emotion", "ang");
                startActivity(intent);
            }
        });
        return view1;
    }
}