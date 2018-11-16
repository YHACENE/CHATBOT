package com.systems.fennec.chatbot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Setting extends Fragment
{
    private WifiManager wifiManager;
    private ListView listView;
    private Button btnScan;

    private List<ScanResult> results;
    private ArrayList<String> arrayList;
    private Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_setting, null);

        listView = rootView.findViewById(R.id.wifi_list);


        btnScan = rootView.findViewById(R.id.btn_scan);

        btnScan.setOnClickListener(btnOnClickListener);

        wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (!wifiManager.isWifiEnabled())
        {
            Toast.makeText(getContext(), "WiFi is disabled ... We need to enable it", Toast.LENGTH_LONG).show();
            wifiManager.setWifiEnabled(true);
        }

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        return rootView;
    }

    private View.OnClickListener btnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            scanWifi();
        }
    };

    private void scanWifi()
    {
        arrayList.clear();
        getActivity().registerReceiver(wifiRegister, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.startScan();
        Toast.makeText(getContext(), "SCANNING ... WiFi", Toast.LENGTH_LONG).show();
    }

    BroadcastReceiver wifiRegister = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            results = wifiManager.getScanResults();
            getActivity().unregisterReceiver(this);
            for (ScanResult scanResult : results)
            {
                arrayList.add(scanResult.SSID);
                //adapter.notify();
            }
        }
    };



}

