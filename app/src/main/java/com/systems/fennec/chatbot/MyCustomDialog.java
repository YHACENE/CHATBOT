package com.systems.fennec.chatbot;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MyCustomDialog extends DialogFragment
{
    private static final String TAG = "MyCustomDialog";

    private EditText mInput;
    private TextView mActionCancel;
    private TextView mActionOK;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.my_custom_dialog, container, false);

        mInput = (EditText) rootView.findViewById(R.id.ip_adress);
        mActionOK = (TextView) rootView.findViewById(R.id.btn_OK);
        mActionCancel = (TextView) rootView.findViewById(R.id.btn_cancel);

        mActionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Dismiss", Toast.LENGTH_LONG).show();
                getDialog().dismiss();
            }
        });

        mActionOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Dismiss", Toast.LENGTH_LONG).show();
                String input = mInput.getText().toString();

                if (!input.equals(""))
                {
                    Toast.makeText(getActivity(), "Put the IP adress", Toast.LENGTH_LONG).show();
                }
            }
        });
        return rootView;
    }
}
