package com.example.enliven.ui.dashboard;

import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
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
    private  ImageView story1, story2, story3, story4, story5, story6, story7, story8, story9, story10, story11, story12, story13, story14, story15, story16, story17, story18, story19, story20;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dnevnikcard = root.findViewById(R.id.dnevnikcard);
        tipscard = root.findViewById(R.id.card2);
        story_citati=root.findViewById(R.id.citati);
        socialinfo=root.findViewById(R.id.socialinfo);
        enlivensocial=root.findViewById(R.id.enlivensocial);

        story1=root.findViewById(R.id.story1_1);
        story2=root.findViewById(R.id.story21);
        story3=root.findViewById(R.id.story31);
        story4=root.findViewById(R.id.story41);
        story5=root.findViewById(R.id.story51);
        story6=root.findViewById(R.id.story61);
        story7=root.findViewById(R.id.story71);
        story8=root.findViewById(R.id.story81);
        story9=root.findViewById(R.id.story91);
        story10=root.findViewById(R.id.story101);
        story11=root.findViewById(R.id.story111);
        story12=root.findViewById(R.id.story121);
        story13=root.findViewById(R.id.story131);
        story14=root.findViewById(R.id.story141);
        story15=root.findViewById(R.id.story151);
        story16=root.findViewById(R.id.story161);
        story17=root.findViewById(R.id.story171);
        story18=root.findViewById(R.id.story181);
        story19=root.findViewById(R.id.story191);
        story20=root.findViewById(R.id.story201);



        story1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), citati_activity.class);

                intent.putExtra("Broj storija", 0);
                startActivity(intent);
            }
        });
        story2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), citati_activity.class);

                intent.putExtra("Broj storija", 1);
                startActivity(intent);
            }
        });
        story3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), citati_activity.class);

                intent.putExtra("Broj storija", 2);
                startActivity(intent);
            }
        });
        story4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), citati_activity.class);

                intent.putExtra("Broj storija", 3);
                startActivity(intent);
            }
        });
        story5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), citati_activity.class);

                intent.putExtra("Broj storija", 4);
                startActivity(intent);
            }
        });
        story6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), citati_activity.class);

                intent.putExtra("Broj storija", 5);
                startActivity(intent);
            }
        });
        story7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), citati_activity.class);

                intent.putExtra("Broj storija", 6);
                startActivity(intent);
            }
        });
        story8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), citati_activity.class);

                intent.putExtra("Broj storija", 7);
                startActivity(intent);
            }
        });
        story9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), citati_activity.class);

                intent.putExtra("Broj storija", 8);
                startActivity(intent);
            }
        });
        story10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), citati_activity.class);

                intent.putExtra("Broj storija", 9);
                startActivity(intent);
            }
        });
        story11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), citati_activity.class);

                intent.putExtra("Broj storija", 10);
                startActivity(intent);
            }
        });
        story12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), citati_activity.class);

                intent.putExtra("Broj storija", 11);
                startActivity(intent);
            }
        });
        story13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), citati_activity.class);

                intent.putExtra("Broj storija", 12);
                startActivity(intent);
            }
        });
        story14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), citati_activity.class);

                intent.putExtra("Broj storija", 13);
                startActivity(intent);
            }
        });
        story15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), citati_activity.class);

                intent.putExtra("Broj storija", 14);
                startActivity(intent);
            }
        });
        story16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), citati_activity.class);

                intent.putExtra("Broj storija", 15);
                startActivity(intent);
            }
        });
        story17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), citati_activity.class);

                intent.putExtra("Broj storija", 16);
                startActivity(intent);
            }
        });
        story18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), citati_activity.class);

                intent.putExtra("Broj storija", 17);
                startActivity(intent);
            }
        });
        story19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), citati_activity.class);

                intent.putExtra("Broj storija", 18);
                startActivity(intent);
            }
        });
        story20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), citati_activity.class);

                intent.putExtra("Broj storija", 19);
                startActivity(intent);
            }
        });


        for(int i=0;i<20;i++){
            int i1=i;
            story_citati.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), citati_activity.class);

                    intent.putExtra("Broj storija", i1);
                            startActivity(intent);
                }
            });

        }


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
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}