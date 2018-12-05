package com.systems.fennec.chatbot;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.systems.fennec.chatbot.WiFiDirectBroadcastReceiver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Setting extends Fragment {

    Button btnOnOff;
    Button btnDiscover;
    Button btnSend;
    ListView listView;
    TextView read_msg_box;
    public TextView connectionStatus;
    EditText writeMsg;

    WifiManager wifiManager;
    WifiP2pManager mManager;
    WifiP2pManager.Channel mChannel;

    BroadcastReceiver broadcastReceiver;
    IntentFilter intentFilter;

    List<WifiP2pDevice> peers = new ArrayList<WifiP2pDevice>();
    String[] deviceNameArray;
    WifiP2pDevice[] devicesArray;

    static final int MESSAGE_READ = 1;

    ServerClass serverClass;
    ClientClass clientClass;
    SendReceive sendReceive;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_setting, null);

        //initView(rootView);


        btnOnOff = (Button) rootView.findViewById(R.id.onOff);
        btnDiscover = (Button) rootView.findViewById(R.id.discover);
        btnSend = (Button) rootView.findViewById(R.id.sendButton);

        listView = (ListView) rootView.findViewById(R.id.peerListView);
        read_msg_box = (TextView) rootView.findViewById(R.id.readMsg);
        connectionStatus = (TextView) rootView.findViewById(R.id.connectionStatus);

        writeMsg = (EditText) rootView.findViewById(R.id.writeMsg);

        wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        mChannel = mManager.initialize(getActivity().getApplicationContext(), getActivity().getMainLooper(), null);

        broadcastReceiver = new WiFiDirectBroadcastReceiver(mManager, mChannel, this);

        intentFilter = new IntentFilter();
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
        //exqListener();

        btnOnOff.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(wifiManager.isWifiEnabled())
                {
                    wifiManager.setWifiEnabled(false);
                    connectionStatus.setText("OFF");
                }else
                {
                    wifiManager.setWifiEnabled(true);
                    connectionStatus.setText("ON");
                }
            }
        });

        btnDiscover.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
                    @Override
                    public void onSuccess()
                    {
                        connectionStatus.setText("Discovery started");
                    }

                    @Override
                    public void onFailure(int i)
                    {
                        connectionStatus.setText("Discovery Starting failed");
                    }
                });
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                final WifiP2pDevice device = devicesArray[i];
                WifiP2pConfig config = new WifiP2pConfig();

                config.deviceAddress = device.deviceAddress;

                mManager.connect(mChannel, config, new WifiP2pManager.ActionListener()
                {
                    @Override
                    public void onSuccess()
                    {
                        Toast.makeText(getActivity().getApplicationContext(), "Connected to " + device.deviceName, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int i)
                    {
                        Toast.makeText(getActivity().getApplicationContext(), "Not Connected", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String msg = writeMsg.getText().toString();
                sendReceive.write(msg.getBytes());
            }
        });

        return rootView;
    }



    Handler handler = new Handler(new Handler.Callback()
    {
        @Override
        public boolean handleMessage(Message message)
        {
            switch (message.what)
            {
                case MESSAGE_READ:
                    byte[] readBuff = (byte[]) message.obj;
                    String tempMsg = new String(readBuff, 0, message.arg1);
                    read_msg_box.setText(tempMsg);
                    break;
            }
            return true;
        }
    });


/*
    private void exqListener()
    {

    }
*/
    /*
    private void initView(View view) {

    }
    */

    public WifiP2pManager.PeerListListener peerListListener = new WifiP2pManager.PeerListListener() {
        @Override
        public void onPeersAvailable(WifiP2pDeviceList peerList) {
            if (!peerList.getDeviceList().equals(peers)) {
                peers.clear();
                peers.addAll(peerList.getDeviceList());

                deviceNameArray = new String[peerList.getDeviceList().size()];
                devicesArray = new WifiP2pDevice[peerList.getDeviceList().size()];

                int index = 0;

                for (WifiP2pDevice device : peerList.getDeviceList()) {
                    deviceNameArray[index] = device.deviceName;
                    devicesArray[index] = device;
                    index++;
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, deviceNameArray);
                listView.setAdapter(adapter);
            }

            if (peers.size() == 0) {
                Toast.makeText(getActivity().getApplicationContext(), "No Device Found", Toast.LENGTH_SHORT).show();
            }
        }
    };


    public WifiP2pManager.ConnectionInfoListener connectionInfoListener = new WifiP2pManager.ConnectionInfoListener() {
        @Override
        public void onConnectionInfoAvailable(WifiP2pInfo wifiP2pInfo) {
            final InetAddress groupOwnerAddress = wifiP2pInfo.groupOwnerAddress;

            if (wifiP2pInfo.groupFormed && wifiP2pInfo.isGroupOwner) {
                connectionStatus.setText("Host");
                serverClass = new ServerClass();
                serverClass.start();
            } else if (wifiP2pInfo.groupFormed) {
                connectionStatus.setText("Client");
                clientClass = new ClientClass(groupOwnerAddress);
                clientClass.start();
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(broadcastReceiver);
    }

    public class ServerClass extends Thread {
        Socket socket;
        ServerSocket serverSocket;

        @Override
        public void run() {
            try {
                serverSocket = new ServerSocket(8888);
                socket = serverSocket.accept();
                sendReceive = new SendReceive(socket);
                sendReceive.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class SendReceive extends Thread {
        private Socket socket;
        private InputStream inputStream;
        private OutputStream outputStream;

        public SendReceive(Socket skt) {
            socket = skt;
            try {
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            byte[] buffer = new byte[1024];
            int bytes;

            while (socket != null) {
                try {
                    bytes = inputStream.read(buffer);
                    if (bytes > 0) {
                        handler.obtainMessage(MESSAGE_READ, bytes, -1, buffer).sendToTarget();

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void write(byte[] bytes) {
            try {
                outputStream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class ClientClass extends Thread {
        Socket socket;
        String hostAddr;

        public ClientClass(InetAddress hostAddr) {
            this.hostAddr = hostAddr.getHostAddress();
            socket = new Socket();
            sendReceive = new SendReceive(socket);
            sendReceive.start();
        }

        @Override
        public void run() {
            try {
                socket.connect(new InetSocketAddress(hostAddr, 8888), 500);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

