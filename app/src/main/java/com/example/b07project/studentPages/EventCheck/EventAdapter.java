package com.example.b07project.studentPages.EventCheck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



import com.example.b07project.R;
import com.example.b07project.adminPages.EventSetUp.Event;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    Context context;

    ArrayList<Event> list;
    RSVPClickListener rsvpClickListener;

    public interface RSVPClickListener {
        void onEventClick(Event event);
    }

    public EventAdapter(Context context, ArrayList<Event> list, RSVPClickListener eventClickListener) {
        this.context = context;
        this.list = list;
        this.rsvpClickListener =eventClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_event_display_list,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Event eList = list.get(position);
        holder.eventNameDisplay.setText(eList.getTitle());
        holder.eventTimeDisplay.setText(eList.getEventDate());
        holder.eventLocationDisplay.setText(eList.getLocation());
        holder.eventPostTimeDisplay.setText(eList.getPostDate());

        holder.rsvpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rsvpClickListener != null) {
                    rsvpClickListener.onEventClick(eList);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView eventNameDisplay, eventTimeDisplay, eventLocationDisplay, eventPostTimeDisplay;
        Button rsvpButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            eventNameDisplay = itemView.findViewById(R.id.eventName);
            eventTimeDisplay = itemView.findViewById(R.id.dateTime);
            eventLocationDisplay = itemView.findViewById(R.id.eventLocation);
            eventPostTimeDisplay = itemView.findViewById(R.id.eventPostTime);
            rsvpButton = itemView.findViewById(R.id.rsvpButton);

        }

    }

}