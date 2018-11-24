package com.systems.fennec.chatbot;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
{
    Context mContext;
    List<Gamepad> mData;

    public MyAdapter(Context mContext, List<Gamepad> mData)
    {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.gamepad_ticket, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        holder.gp_title.setText(mData.get(position).getTitle());
        holder.gp_descrition.setText(mData.get(position).getDescription());
        holder.gp_image.setImageResource(mData.get(position).getImage());

    }

    @Override
    public int getItemCount()
    {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView gp_title;
        private TextView gp_descrition;
        private ImageView gp_image;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            gp_title = (TextView) itemView.findViewById(R.id.title_gamepad);
            gp_descrition = (TextView) itemView.findViewById(R.id.description_gamepad);
            gp_image = (ImageView) itemView.findViewById(R.id.img_gamepad);
        }
    }
}






















/*
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter
{
    ArrayList<Gamepad> gamepads;
    Context context;

    public MyAdapter(Context context, ArrayList<Gamepad> gamepads)
    {
        this.context = context;
        this.gamepads = gamepads;
    }

    @Override
    public int getCount()
    {
        return gamepads.size();
    }

    @Override
    public Object getItem(int position)
    {
        return gamepads.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.fragment_help, parent, false);

        TextView title = row.findViewById(R.id.title_gamepad);
        TextView description = row.findViewById(R.id.description_gamepad);
        ImageView image = row.findViewById(R.id.img_gamepad);

        Gamepad current = gamepads.get(position);

        title.setText(current.title);
        description.setText(current.description);
        image.setImageResource(current.image);
        return null;
    }
}

*/
/*
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
{

    private LayoutInflater inflater;

    List<Gamepad> gamepadList = Collections.emptyList();

    public MyAdapter(Context context, List<Gamepad> gamepadList)
    {
        inflater = LayoutInflater.from(context);
        this.gamepadList = gamepadList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = inflater.inflate(R.layout.gamepad_ticket, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        Gamepad current = gamepadList.get(position);
        holder.title.setText(current.title);
        holder.description.setText(current.description);
        holder.image.setImageResource(current.image);
    }


    @Override
    public int getItemCount()
    {
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;
        TextView description;
        ImageView image;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_gamepad);
            description = (TextView) itemView.findViewById(R.id.description_gamepad);
            image = (ImageView) itemView.findViewById(R.id.img_gamepad);
        }
    }
}

*/
