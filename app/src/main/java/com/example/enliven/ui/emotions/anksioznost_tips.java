package com.example.enliven.ui.emotions;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.enliven.R;


public class anksioznost_tips extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.fragment_anksioznost_tips, container, false);

        Button button = view1.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NotesEditorActivity.class);
                intent.putExtra("emotion", "anx");
                startActivity(intent);
            }
        });
        return view1;
    }
}