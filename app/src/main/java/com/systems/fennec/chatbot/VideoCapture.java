package com.systems.fennec.chatbot;

import android.content.pm.ActivityInfo;
import android.net.rtp.RtpStream;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class VideoCapture extends Fragment
{
    Button btnGet;
    TextView dataView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_video_capture, container, false);

       // btnGet = (Button) rootView.findViewById(R.id.btn_capt_data);
        dataView = (TextView) rootView.findViewById(R.id.txt_view_data);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();

            }
        });

        return rootView;
    }


    private void getData()
    {
        MessageSender messageSender = new MessageSender();
        messageSender.execute("get_video");
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toast.makeText(getContext(), "VideoCapture", Toast.LENGTH_LONG).show();
    }
}
