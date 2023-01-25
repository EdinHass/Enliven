package com.example.enliven.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enliven.ChangeNumberItemsListener;
import com.example.enliven.databinding.FragmentHomeBinding;
import com.example.enliven.ui.habits.FinalHabitsListA;
import com.example.enliven.ui.habits.HabitsList;
import com.example.enliven.R;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private LinearLayout habit;
    private LinearLayout task;
    private RelativeLayout profile;
    private RecyclerView recyclerViewList;
    private RecyclerView.Adapter adapter;
    private HabitsList habitsList;
    View root;
    private TextView emptyTxt;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        habitsList = new HabitsList(requireContext());
        root = binding.getRoot();

        habit = root.findViewById(R.id.cardhabit);
        task = root.findViewById(R.id.cardtodo);
        TextView pocetniText = root.findViewById(R.id.textpocetni);
        profile = root.findViewById(R.id.profile);

        Animation floatUpFast = AnimationUtils.loadAnimation(getActivity(), R.anim.animation_bottom_lighter);
        Animation floatDown = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_top_light);
        pocetniText.startAnimation(floatDown);
        habit.startAnimation(floatUpFast);
        task.startAnimation(floatUpFast);
        profile.startAnimation(floatUpFast);


        habit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main).navigate(R.id.action_navigation_home_to_main_habits);
            }
        });

        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main).navigate(R.id.action_navigation_home_to_MainActivity2);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main).navigate(R.id.action_navigation_home_to_profileFragment2);
            }
        });

        SharedPreferences sprefs = getActivity().getSharedPreferences("com.example.enliven", Context.MODE_PRIVATE);
        if (sprefs.getString("UserName", "invalid").equals("invalid")) {
            pocetniText.setText("Dobrodošli!");
        } else {
            pocetniText.setText("Šta radiš, " + sprefs.getString("UserName", "invalid") + "?");
        }

        initView();
        initList();

        return root;
    }

    private void initView() {
        recyclerViewList = root.findViewById(R.id.recycleviewfinall);
        emptyTxt = root.findViewById(R.id.emptyTxt);
    }

    private void initList () {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new FinalHabitsListA(HabitsList.getHabitList(), requireContext(), new ChangeNumberItemsListener() {
            @Override
            public void changed() {

            }
        });

        recyclerViewList.setAdapter(adapter);
        if (HabitsList.getHabitList().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
        }
        else {
            emptyTxt.setVisibility(View.GONE);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
        initList();
    }
}