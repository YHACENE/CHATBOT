package com.systems.fennec.chatbot;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Help extends Fragment
{
    private RecyclerView mRecyclerView;
    private List<Gamepad> listGamepads;

    public Help()
    {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_help, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.list_commands_view);
        MyAdapter myAdapter = new MyAdapter(getContext(), listGamepads);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(myAdapter);

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        listGamepads = loadGamepads();
    }

    public static List<Gamepad> loadGamepads()
    {
        List<Gamepad> listGamepads = new ArrayList<>();

        listGamepads.add(new Gamepad("WiFi", "Wifi communication", R.drawable.bluetooth));
        listGamepads.add(new Gamepad("Avancer", "Ce button permet d'avancer", R.drawable.direction_up));
        listGamepads.add(new Gamepad("A droite", "Ce button permet de tourner à droite", R.drawable.direction_right));
        listGamepads.add(new Gamepad("A Gauche", "Ce button permet de tourner à gauche", R.drawable.direction_left));
        listGamepads.add(new Gamepad("Reculer", "Ce button permet de reculer", R.drawable.direction_down));
        listGamepads.add(new Gamepad("WiFi", "Wifi communication", R.drawable.button_triangle));
        listGamepads.add(new Gamepad("WiFi", "Wifi communication", R.drawable.button_circle));
        listGamepads.add(new Gamepad("WiFi", "Wifi communication", R.drawable.button_x));
        listGamepads.add(new Gamepad("WiFi", "Wifi communication", R.drawable.button_carree));
        listGamepads.add(new Gamepad("WiFi", "Wifi communication", R.drawable.temperature));
        listGamepads.add(new Gamepad("WiFi", "Wifi communication", R.drawable.battery));
        listGamepads.add(new Gamepad("WiFi", "Wifi communication", R.drawable.radar));
        listGamepads.add(new Gamepad("WiFi", "Wifi communication", R.drawable.spot_light));

        return listGamepads;
    }
}



/*
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Help extends Fragment
{
    ListView listView;
    protected List<Gamepad> gamepadList = new ArrayList<Gamepad>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_help, container, false);
        listView = (ListView) rootView.findViewById(R.id.commands_list);
        //listView.setAdapter(new MyAdapter(getContext(), gamepadList));
        return rootView;
    }

    private void loadGamepads()
    {
        gamepadList.add(new Gamepad("WiFi", "Etablissement de connection", R.drawable.bluetooth));
        gamepadList.add(new Gamepad("WiFi", "Etablissement de connection", R.drawable.direction_up));
        gamepadList.add(new Gamepad("WiFi", "Etablissement de connection", R.drawable.direction_right));
        gamepadList.add(new Gamepad("WiFi", "Etablissement de connection", R.drawable.direction_down));
        gamepadList.add(new Gamepad("WiFi", "Etablissement de connection", R.drawable.direction_left));
        gamepadList.add(new Gamepad("WiFi", "Etablissement de connection", R.drawable.button_triangle));
        gamepadList.add(new Gamepad("WiFi", "Etablissement de connection", R.drawable.button_carree));
        gamepadList.add(new Gamepad("WiFi", "Etablissement de connection", R.drawable.button_x));
        gamepadList.add(new Gamepad("WiFi", "Etablissement de connection", R.drawable.button_circle));



    }
}

*/

/*
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Help extends Fragment
{
    private MyAdapter myAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_help, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.commands_list);
        linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);
        myAdapter = new MyAdapter(getContext(), loadCommands());
        recyclerView.setAdapter(myAdapter);
        Toast.makeText(getActivity(), "Help", Toast.LENGTH_LONG).show();

        return rootView;
    }

    public static List<Gamepad> loadCommands()
    {
        List<Gamepad> gamepads = new ArrayList<>();

        String[] titles = { "Bluetooth",
                            "Button Carree",
                            "Button Carree",
                            "Button Carree",
                            "Button Carree",
                            "Button Carree",
                            "Button Carree",
                            "Button Carree",
                            "Button Carree"
                          };

        String[] descriptions = { "Bluetooth",
                                  "Button Carree",
                                  "Button Carree",
                                  "Button Carree",
                                  "Button Carree",
                                  "Button Carree",
                                  "Button Carree",
                                  "Button Carree",
                                  "Button Carree"
                                 };

        int[] images = { R.drawable.bluetooth,
                         R.drawable.button_carree,
                         R.drawable.button_circle,
                         R.drawable.button_triangle,
                         R.drawable.button_x,
                         R.drawable.direction_up,
                         R.drawable.direction_right,
                         R.drawable.direction_down,
                         R.drawable.direction_left
                        };

        for (int i = 0; i < titles.length && i < descriptions.length && i < images.length; i++)
        {
            Gamepad current = new Gamepad();
            current.title = titles[i];
            current.description = descriptions[i];
            current.image = images[i];

            gamepads.add(current);

        }
        return gamepads;
    }
}
*/