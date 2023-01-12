package com.example.enliven.ui.dashboard;

import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.enliven.MoodActivity;
import com.example.enliven.R;
import com.example.enliven.MainActivity;
import com.example.enliven.SocialDialogFragment;
import com.example.enliven.SoundsPlayerActivity;
import com.example.enliven.citati_activity;
import com.example.enliven.data.UserExtra;
import  com.example.enliven.diaryactivity;
import com.example.enliven.databinding.FragmentDashboardBinding;
import com.example.enliven.general_tip;
import com.example.enliven.general_tips;
import com.example.enliven.osjecanja_tips;
import com.example.enliven.sreca_tips;
import com.example.enliven.ui.auth.AuthActivity;
import com.example.enliven.ui.chat.ChatActivity;
import com.example.enliven.data.StreamTokenApi;
import com.example.enliven.data.UserExtra;
import com.example.enliven.ui.auth.AuthActivity;
import com.example.enliven.ui.auth.SetupProfileFragment;
import com.example.enliven.ui.auth.StreamTokenProvider;
import com.example.enliven.ui.chat.ChatActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;
import java.sql.CallableStatement;

import java.util.HashMap;
import java.util.Map;

import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.models.User;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private RelativeLayout dnevnikcard, tipscard, socialinfo, enlivensocial;
    private LinearLayout story_citati;
    SharedPreferences prefs;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dnevnikcard = root.findViewById(R.id.dnevnikcard);
        tipscard = root.findViewById(R.id.card2);
        story_citati=root.findViewById(R.id.citati);
        socialinfo=root.findViewById(R.id.socialinfo);
        enlivensocial=root.findViewById(R.id.enlivensocial);


        prefs = getActivity().getSharedPreferences("com.example.enliven", Context.MODE_PRIVATE);




        enlivensocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getActivity().getSharedPreferences("com.example.enliven", Context.MODE_PRIVATE);
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if (currentUser == null) {
                    startActivity(new Intent(getContext(), AuthActivity.class));
                } else {
                    User user = new User();
                    user.setId(currentUser.getUid());
                    HashMap extraData = new HashMap<String, String>();
                    extraData.put(UserExtra.NAME, prefs.getString("loginName", ""));
                    extraData.put(UserExtra.PHONE, prefs.getString("loginPhone", ""));
                    extraData.put(UserExtra.IMAGE, prefs.getString("loginImage", ""));
                    user.setExtraData(extraData);
                    ChatClient.instance().connectUser(user, prefs.getString("loginToken", "")).enqueue(result -> {
                        if(result.isSuccess()){
                            startActivity(new Intent(getContext(), ChatActivity.class));
                        }else{
                            Toast.makeText(getContext(), "Login Error", Toast.LENGTH_LONG);
                        }
                    });
                }
            }
        });

        socialinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SocialDialogFragment socialDialogFragment = new SocialDialogFragment();
                socialDialogFragment.show(getChildFragmentManager(),"Informacije");
            }
        });



        tipscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main).navigate(R.id.action_navigation_dashboard_to_general_tip);
            }
        });

        dnevnikcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), osjecanja_tips.class);
                startActivity(intent);

            }
        });

        TextView pocetniText = root.findViewById(R.id.textpocetni);
        SharedPreferences sprefs = getActivity().getSharedPreferences("com.example.enliven", Context.MODE_PRIVATE);
        pocetniText.setText( "Zdravo, "+ sprefs.getString("UserName", "invalid") + "!");




        return root;
    }

    @Override
    public void onResume() {
        story_citati.removeAllViews();
        setupStories();
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setupStories(){
        final int[] currIndex = {-1};
        for(int i=0;i<20;i++){
            int finalI = i;
            ImageView temp = new ImageView(getContext());
            if(prefs.getBoolean("storyCitat"+i, false)) {
                continue;
            }
            else{
                currIndex[0]++;
                temp.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.questionmark));
            }

            Resources r = getContext().getResources();
            int px = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    80,
                    r.getDisplayMetrics()
            );
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(px, px);
            px = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    10,
                    r.getDisplayMetrics()
            );
            layoutParams.setMargins(0,0,px, 0);
            temp.setLayoutParams(layoutParams);
            story_citati.addView(temp);

            temp.setClickable(true);
            prefs.edit().putInt("storyRedoslijed"+ currIndex[0], finalI).apply();
            temp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), citati_activity.class);
                    intent.putExtra("Broj storija", finalI);
                    startActivity(intent);
                }
            });
        }

        for(int i=0;i<20;i++){
            int finalI = i;
            ImageView temp = new ImageView(getContext());
            if(prefs.getBoolean("storyCitat"+i, false)) {
                currIndex[0]++;
                temp.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.check));
            }
            else{
                continue;
            }

            Resources r = getContext().getResources();
            int px = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    80,
                    r.getDisplayMetrics()
            );
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(px, px);
            px = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    10,
                    r.getDisplayMetrics()
            );
            layoutParams.setMargins(0,0,px, 0);
            temp.setLayoutParams(layoutParams);
            story_citati.addView(temp);

            temp.setClickable(true);
            prefs.edit().putInt("storyRedoslijed" + currIndex[0], finalI).apply();
            temp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), citati_activity.class);
                    intent.putExtra("Broj storija", finalI);
                    startActivity(intent);
                }
            });
        }
        currIndex[0]=-1;
    }
}