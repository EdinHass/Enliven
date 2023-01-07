package com.example.enliven;

import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link welcome_screen_start#newInstance} factory method to
 * create an instance of this fragment.
 */
public class welcome_screen_start extends Fragment {

    private Button btnNext;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public welcome_screen_start() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment welcome_screen_start.
     */
    // TODO: Rename and change types and number of parameters
    public static welcome_screen_start newInstance(String param1, String param2) {
        welcome_screen_start fragment = new welcome_screen_start();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.fragment_welcome_screen_start, container, false);
        btnNext = view1.findViewById(R.id.buttonNext);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment navHostFragment =
                        (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.welcome_screen_fragmentView);
                NavController navController = navHostFragment.getNavController();
                navController.navigate(R.id.action_welcome_screen_start_to_welcome_screen2);
            }

        });

        ImageView img = view1.findViewById(R.id.imageStarting1);
        TextView text = view1.findViewById(R.id.textWelcome1);
        Animation animationTop = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_out_top);
        Animation animationBottom = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_out_bottom);
        Animation fade = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fade_in_slow);
        img.startAnimation(animationTop);
        text.startAnimation(animationBottom);
        btnNext.startAnimation(fade);
        return view1;
    }
}