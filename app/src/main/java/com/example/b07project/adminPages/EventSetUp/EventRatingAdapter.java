package com.example.b07project.adminPages.EventSetUp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07project.R;

import java.util.ArrayList;

public class EventRatingAdapter extends RecyclerView.Adapter<EventRatingAdapter.MyViewHolder> {

    Context context;

    ArrayList<Event> list;
    InfoClickListener rsvpClickListener;


    public interface InfoClickListener {
        void onEventClick(Event event);
    }

    public EventRatingAdapter(Context context, ArrayList<Event> list, InfoClickListener eventClickListener) {
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
        holder.EventName.setText(eList.getTitle());
        holder.DateTime.setText(eList.getEventDate());
        holder.Location.setText(eList.getLocation());
        holder.ReviewButton.setText("More Reviews");
        holder.PostTime.setText(eList.getPostDate());



        holder.ReviewButton.setOnClickListener(new View.OnClickListener() {
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

        TextView EventName, DateTime, Location, PostTime;
        Button ReviewButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            EventName = itemView.findViewById(R.id.eventName);
            DateTime = itemView.findViewById(R.id.dateTime);
            Location = itemView.findViewById(R.id.eventLocation);
            ReviewButton = itemView.findViewById(R.id.rsvpButton);
            PostTime = itemView.findViewById(R.id.eventPostTime);

        }

    }

}