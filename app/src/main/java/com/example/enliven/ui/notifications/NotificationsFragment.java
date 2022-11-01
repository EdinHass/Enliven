package com.example.enliven.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.enliven.MainActivity;
import com.example.enliven.R;
import com.example.enliven.card1activity;
import com.example.enliven.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private RelativeLayout card1Sleep, card2Sleep;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        getActivity().setContentView(R.layout.fragment_notifications);
        card1Sleep = (RelativeLayout) root.findViewById(R.id.card1Sleep);
        card2Sleep = (RelativeLayout) root.findViewById(R.id.card2Sleep);
        TextView zapocniText = (TextView) getActivity().findViewById(R.id.textViewZapocni);
        SharedPreferences prefs = getActivity().getSharedPreferences("com.example.enliven", Context.MODE_PRIVATE);

        zapocniText.setText("Zdravo " + prefs.getString("UserName", "invalid"));
        card1Sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main).navigate(R.id.action_navigation_notifications_to_card1activity);
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