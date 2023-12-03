package com.example.b07project.studentPages.EventRecycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07project.R;
import com.example.b07project.adminPages.EventSetUp.Event;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {
    private final EventRecyclerInterface Recycler_Interface;
    Context context;
    ArrayList<Event> events;
    public EventAdapter(EventRecyclerInterface Recycler_Interface, Context context, ArrayList<Event> events){
        this.Recycler_Interface = Recycler_Interface;
        this.context = context;
        this.events = events;
    }
    @NonNull
    @Override
    public EventAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //returns the views for our layout
        LayoutInflater inflator = LayoutInflater.from(context);
        View view = inflator.inflate(R.layout.student_homepage_events_box, parent, false);

        return new EventAdapter.MyViewHolder(view, Recycler_Interface);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.MyViewHolder holder, int position) {
        //assign values to the views, based on the position of our layout
        holder.eventTime.setText(events.get(position).getTime());
        holder.eventDate.setText(events.get(position).getEventDate());
        holder.eventTitle.setText(events.get(position).getTitle());
        holder.eventLocation.setText(events.get(position).getLocation());


    }

    @Override
    public int getItemCount() {
        //the number of item you want to display
        return events.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView eventTime, eventDate, eventTitle, eventLocation;

        public MyViewHolder(@NonNull View itemView, EventRecyclerInterface Recycler_Interface) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Recycler_Interface != null){
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){
                            Recycler_Interface.ClickItem(position);
                        }
                    }
                }
            });
        }
    }
}
