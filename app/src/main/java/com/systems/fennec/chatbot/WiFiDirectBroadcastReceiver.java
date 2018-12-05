package com.systems.fennec.chatbot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.widget.Toast;

import com.systems.fennec.chatbot.Setting;

public class WiFiDirectBroadcastReceiver extends BroadcastReceiver
{
    private WifiP2pManager pManager;
    private WifiP2pManager.Channel channel;
    private Setting setting;

    public WiFiDirectBroadcastReceiver(WifiP2pManager pManager, WifiP2pManager.Channel channel, Setting setting)
    {
        this.pManager = pManager;
        this.channel = channel;
        this.setting = setting;
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        String action = intent.getAction();

        if(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action))
        {
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);

            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED)
            {
                Toast.makeText(context, "Wifi is ON", Toast.LENGTH_LONG).show();
            }else
            {
                Toast.makeText(context, "Wifi is OFF", Toast.LENGTH_LONG).show();
            }
        }else if(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action))
        {
            if (pManager != null)
            {
                pManager.requestPeers(channel, setting.peerListListener);
            }
        }else if(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action))
        {
            if (pManager == null)
            {
                return;
            }

            NetworkInfo networkInfo = intent.getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);

            if (networkInfo.isConnected())
            {
                pManager.requestConnectionInfo(channel, setting.connectionInfoListener);
            }else {
                setting.connectionStatus.setText("Device Disconnected");
            }

        }else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action))
        {
            //TODO:
        }
    }
}
