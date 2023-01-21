package com.example.enliven;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class vjezba_disanja_za_ravnotezu extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view1= inflater.inflate(R.layout.fragment_vjezba_disanja_za_ravnotezu, container, false);
        TextView saznajvise=view1.findViewById(R.id.saznajvise);
        ImageView imageView=view1.findViewById(R.id.imageView);
        saznajvise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(), R.id.vjezbedisanja_fragmentView).navigate(R.id.action_vjezba_disanja_za_ravnotezu_to_vjezba_disanja_za_ravnotezu_info);
            }
        });
        Postavi(imageView, new PostaviVjezbeDisanja("Vježba za ravnotežu", "https://cdn.pixabay.com/download/audio/2021/09/06/audio_37aad22374.mp3?filename=sandy-beach-calm-waves-water-nature-sounds-8052.mp3", 7 , 5));
        return view1;
    }

    public void Postavi(ImageView imageView, PostaviVjezbeDisanja postaviVjezbeDisanja){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),vjezba_disanja1.class);
                i.putExtra("vjezbaIme", postaviVjezbeDisanja.getName());
                i.putExtra("SoundData", postaviVjezbeDisanja.getSoundData());
                i.putExtra("vrijemeIzdisanja", postaviVjezbeDisanja.getVrijemeIzdisanja());
                i.putExtra("vrijemeUdisanja",postaviVjezbeDisanja.getVrijemeUzdisanja());
                startActivity(i);
            }
        });
    }
    }
