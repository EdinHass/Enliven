package com.example.enliven.ui.dashboard;

import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.enliven.R;
import com.example.enliven.SocialDialogFragment;
import com.example.enliven.citati_activity;
import com.example.enliven.data.ImageURLS;
import com.example.enliven.data.UserExtra;
import  com.example.enliven.diaryactivity;
import com.example.enliven.databinding.FragmentDashboardBinding;
import com.example.enliven.general_tip;
import com.example.enliven.ui.auth.AuthActivity;
import com.example.enliven.ui.chat.ChatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;

import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.models.User;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private RelativeLayout dnevnikcard, socialinfo, enlivensocial, vodic;
    private LinearLayout tipscard;
    private LinearLayout story_citati;
    SharedPreferences prefs;
    View root;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        dnevnikcard = root.findViewById(R.id.dnevnikcard);
        tipscard = root.findViewById(R.id.card2);
        story_citati=root.findViewById(R.id.citati);
        socialinfo=root.findViewById(R.id.socialinfo);
        vodic=root.findViewById(R.id.vodic);
        enlivensocial=root.findViewById(R.id.enlivensocial);
        TextView pocetniText = root.findViewById(R.id.textpocetni);

        Animation floatUpFast = AnimationUtils.loadAnimation(getActivity(),R.anim.animation_bottom_lighter);
        Animation floatDown = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_top_light);
        Animation slideoutright = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_from_right_light);
        pocetniText.startAnimation(floatDown);
        enlivensocial.startAnimation(floatUpFast);
        dnevnikcard.startAnimation(floatUpFast);
        story_citati.startAnimation(slideoutright);
        tipscard.startAnimation(floatUpFast);
        vodic.startAnimation(floatUpFast);


        prefs = getActivity().getSharedPreferences("com.example.enliven", Context.MODE_PRIVATE);




        enlivensocial.setOnClickListener(new View.OnClickListener() {
            private long lastClickTime = 0;
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
                    return;
                }
                lastClickTime = SystemClock.elapsedRealtime();
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
                    extraData.put(UserExtra.IMAGE, getImageEmotion());
                    if(ChatClient.instance().getCurrentUser()!=null){
                        ChatClient.instance().getCurrentUser().setImage(getImageEmotion());
                    }
                    user.setExtraData(extraData);
                    ChatClient.instance().connectUser(user, prefs.getString("loginToken", "")).enqueue(result -> {
                        if(result.isSuccess()){
                            startActivity(new Intent(getContext(), ChatActivity.class));
                        }else{
                            Log.e("ERROR", result.error().getMessage());
                            Toast.makeText(getContext(), "Login Error", Toast.LENGTH_LONG).show();
                            FirebaseAuth.getInstance().signOut();
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
                startActivity(new Intent(getContext(), general_tip.class));
            }
        });

        dnevnikcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), diaryactivity.class);
                startActivity(intent);
            }
        });


        SharedPreferences sprefs = getActivity().getSharedPreferences("com.example.enliven", Context.MODE_PRIVATE);
        if(sprefs.getString("UserName", "invalid").equals("invalid")){
            pocetniText.setText( "Dobrodošli!");
        }else{
            pocetniText.setText( "Kako si, " + sprefs.getString("UserName", "invalid") + "?");
        }

        setupLastEmotions();




        return root;
    }

    public void setupLastEmotions(){


        ImageView[] icons = new ImageView[5];
        icons[0]=root.findViewById(R.id.icon5);
        icons[1]=root.findViewById(R.id.icon4);
        icons[2]=root.findViewById(R.id.icon3);
        icons[3]=root.findViewById(R.id.icon2);
        icons[4]=root.findViewById(R.id.icon1);

        TextView noText = root.findViewById(R.id.nohisttext);
        if(prefs.getString("lastEmotion1",null)==null && prefs.getString("lastEmotion2",null)==null && prefs.getString("lastEmotion3",null)==null
           && prefs.getString("lastEmotion4",null)==null && prefs.getString("lastEmotion5",null)==null
        ){
            noText.setVisibility(View.VISIBLE);
            return;
        }else{
            noText.setVisibility(View.GONE);
        }
        if(prefs.getString("lastEmotion1",null)!=null)
            setupIcon(icons[0], prefs.getString("lastEmotion1",null));
        if(prefs.getString("lastEmotion2",null)!=null)
            setupIcon(icons[1], prefs.getString("lastEmotion2",null));
        if(prefs.getString("lastEmotion3",null)!=null)
            setupIcon(icons[2], prefs.getString("lastEmotion3",null));
        if(prefs.getString("lastEmotion4",null)!=null)
            setupIcon(icons[3], prefs.getString("lastEmotion4",null));
        if(prefs.getString("lastEmotion5",null)!=null)
            setupIcon(icons[4], prefs.getString("lastEmotion5",null));



    }

    public void setupIcon(ImageView viewIcon, String emotion){
        Drawable happyIcon = AppCompatResources.getDrawable(getContext(), R.drawable.happyicon);
        Drawable sadIcon = AppCompatResources.getDrawable(getContext(), R.drawable.sadicon);
        Drawable angryIcon = AppCompatResources.getDrawable(getContext(), R.drawable.angryicon);
        Drawable anxiousIcon = AppCompatResources.getDrawable(getContext(), R.drawable.anxiousicon);
        Drawable scaredIcon = AppCompatResources.getDrawable(getContext(), R.drawable.scaredicon);
        Drawable stressIcon = AppCompatResources.getDrawable(getContext(), R.drawable.stressicon);

        switch(emotion){
            case "sad":
                viewIcon.setImageDrawable(sadIcon);
                viewIcon.setVisibility(View.VISIBLE);
                return;
            case "hap":
                viewIcon.setImageDrawable(happyIcon);
                viewIcon.setVisibility(View.VISIBLE);
                return;
            case "ang":
                viewIcon.setImageDrawable(angryIcon);
                viewIcon.setVisibility(View.VISIBLE);
                return;
            case "anx":
                viewIcon.setImageDrawable(anxiousIcon);
                viewIcon.setVisibility(View.VISIBLE);
                return;
            case "sca":
                viewIcon.setImageDrawable(scaredIcon);
                viewIcon.setVisibility(View.VISIBLE);
                return;
            case "str":
                viewIcon.setImageDrawable(stressIcon);
                viewIcon.setVisibility(View.VISIBLE);
                return;
            default:
                return;
        }
    }

    @Override
    public void onResume() {
        story_citati.removeAllViews();
        setupStories();
        setupLastEmotions();
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
                temp.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.checked));
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

    public String getImageEmotion(){
        String emotion = null;
        int i = 5;
        while(emotion==null && i>0) {
            emotion = prefs.getString("lastEmotion" + i, null);
            i--;
        }
        if(emotion==null){
            return UserExtra.DEFAULT_AVATAR;
        }else{
            switch(emotion){
                case "sad":
                    return ImageURLS.SAD;
                case "hap":
                    return ImageURLS.HAPPY;
                case "ang":
                    return ImageURLS.ANGRY;
                case "anx":
                    return ImageURLS.ANXIOUS;
                case "sca":
                    return ImageURLS.SCARED;
                case "str":
                    return ImageURLS.STRESSED;
                default:
                    return UserExtra.DEFAULT_AVATAR;
            }
        }
    }
}