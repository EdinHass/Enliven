package com.example.enliven.ui.sleep.sounds;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.enliven.R;
import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.Slider;

public class TimerDialogFragment extends DialogFragment {

    public interface NoticeDialogListener {
        void onDialogPositiveClick(TimerDialogFragment dialog);
        void onDialogNegativeClick(TimerDialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    NoticeDialogListener listener;
    Slider slider;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View layoutDialog = inflater.inflate(R.layout.timer_dialog, null);
        slider = layoutDialog.findViewById(R.id.sliderTimer);


        slider.setLabelFormatter(new LabelFormatter() {
            @NonNull
            @Override
            public String getFormattedValue(float value) {
                int val = Math.round(value*100);
                int minutes = val*2;
                int hours = minutes/60;
                minutes = minutes % 60;
                String res = hours + "h " + minutes + "m";
                return res;
            }
        });

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(layoutDialog)
                // Add action buttons
                .setPositiveButton("SET", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogPositiveClick(TimerDialogFragment.this);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogNegativeClick(TimerDialogFragment.this);
                        TimerDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }


    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (NoticeDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(getActivity().toString()
                    + " must implement NoticeDialogListener");
        }
    }
}
