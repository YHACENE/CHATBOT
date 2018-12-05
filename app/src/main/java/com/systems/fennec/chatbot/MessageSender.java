package com.systems.fennec.chatbot;

import android.os.AsyncTask;
//import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MessageSender extends AsyncTask<String, Void, Void>
{
    Socket socket;
    //DataOutputStream dataOutputStream;
    PrintWriter writer;

    @Override
    protected Void doInBackground(String... params)
    {
        String commande = params[0];
        //String addr_IP = params[1];

        try
        {
            socket = new Socket("192.168.43.152", 12000);
            writer = new PrintWriter(socket.getOutputStream());
            writer.write(commande);
            writer.flush();
            writer.close();
            socket.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
