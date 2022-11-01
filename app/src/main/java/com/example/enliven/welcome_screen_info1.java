package com.example.enliven;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link welcome_screen_info1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class welcome_screen_info1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public welcome_screen_info1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment welcome_screen_info1.
     */
    // TODO: Rename and change types and number of parameters
    public static welcome_screen_info1 newInstance(String param1, String param2) {
        welcome_screen_info1 fragment = new welcome_screen_info1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.fragment_welcome_screen_info1, container, false);
        Button buttonPrev = view1.findViewById(R.id.buttonPrev);
        Button buttonNext = view1.findViewById(R.id.buttonNext);
        EditText nameField = view1.findViewById(R.id.nameField);
        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_welcome_screen_info1_to_welcome_screen4);
            }
        });
        SharedPreferences prefs = getActivity().getSharedPreferences("com.example.enliven", Context.MODE_PRIVATE);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("UserName", nameField.getText().toString());
                editor.putBoolean("firstrun", false);
                editor.apply();
                getActivity().finish();
            }
        });
        return view1;
    }
}