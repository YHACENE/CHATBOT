package com.systems.fennec.chatbot;

import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
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

import java.net.Socket;

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

    char command;
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
                MyCustomDialog dialog = new MyCustomDialog();
                dialog.show(getFragmentManager(), "MyCustomDialog");
            }
        });


        return rootView;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public class Socket_AsyncTask extends AsyncTask<Void, Void, Void>
    {
        Socket socket;
        @Override
        protected Void doInBackground(Void... voids)
        {
            return null;
        }
    }
}
