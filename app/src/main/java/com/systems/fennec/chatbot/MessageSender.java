package com.systems.fennec.chatbot;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MessageSender extends AsyncTask<String, Void, Void>
{
    Socket socket;
    DataOutputStream dataOutputStream;
    PrintWriter writer;

    @Override
    protected Void doInBackground(String... voids)
    {
        String commande = voids[0];

        try {
            socket = new Socket("172.21.149.197", 5566);
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
