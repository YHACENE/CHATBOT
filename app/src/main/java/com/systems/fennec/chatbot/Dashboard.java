package com.systems.fennec.chatbot;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Dashboard extends Fragment
{
    ImageView btnBluetooth;

    ImageView btnForward;
    ImageView btnBackward;
    ImageView btnRight;
    ImageView btnLeft;

    ImageView btnGetTemperature;
    ImageView btnGetDistance;
    ImageView btnLedControl;
    ImageView btnLevelBattery;

    TextView stateLeds;
    TextView stateRightMotor;
    TextView stateLeftMotor;

    char command;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.activity_dashboard, null);
        btnForward = rootView.findViewById(R.id.directionUp);
        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getContext(), "Avancer", Toast.LENGTH_LONG).show();
            }
        });
        return rootView;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Toast.makeText(getContext(), "Dashboard", Toast.LENGTH_LONG).show();
    }
}
