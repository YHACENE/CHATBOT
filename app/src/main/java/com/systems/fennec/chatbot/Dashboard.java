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
    ImageView btnWiFi;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.activity_dashboard, null);

        btnWiFi = (ImageView) rootView.findViewById(R.id.btnConnectBluetooth);

        btnForward = (ImageView) rootView.findViewById(R.id.directionUp);
        btnBackward = (ImageView) rootView.findViewById(R.id.directionDown);
        btnLeft = (ImageView) rootView.findViewById(R.id.directionLeft);
        btnRight = (ImageView) rootView.findViewById(R.id.directionRight);

        btnLedControl = (ImageView) rootView.findViewById(R.id.lightUp);
        btnGetTemperature = (ImageView) rootView.findViewById(R.id.temperature);
        btnLevelBattery = (ImageView) rootView.findViewById(R.id.getCharge);
        btnGetDistance = (ImageView) rootView.findViewById(R.id.getDistance);

        stateLeds = (TextView) rootView.findViewById(R.id.set_led_state);
        stateLeftMotor = (TextView) rootView.findViewById(R.id.stateMotorLeft);
        stateRightMotor = (TextView) rootView.findViewById(R.id.stateMotorRight);



        btnWiFi.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /*
                MyCustomDialog dialog = new MyCustomDialog();
                dialog.show(getFragmentManager(), "MyCustomDialog");
                */
                sendCommand("stop");
                Toast.makeText(getActivity(), "STOP", Toast.LENGTH_SHORT).show();L
            }
        });

        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                sendCommand("forward");
                Toast.makeText(getActivity(), "Forward", Toast.LENGTH_LONG).show();
            }
        });

        btnBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                sendCommand("backward");

            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                sendCommand("right");
            }
        });

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCommand("left");
            }
        });

        btnGetDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getActivity(), "Not implemented yet", Toast.LENGTH_LONG).show();
            }
        });

        btnLevelBattery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getActivity(), "Not implemented yet", Toast.LENGTH_LONG).show();
            }
        });

        btnLedControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getActivity(), "Not implemented yet", Toast.LENGTH_LONG).show();
            }
        });

        btnGetTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getActivity(), "Not implemented yet", Toast.LENGTH_LONG).show();
            }
        });
        return rootView;
    }

    private void sendCommand(String command)
    {
        MessageSender messageSender = new MessageSender();
        messageSender.execute(command);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

}
