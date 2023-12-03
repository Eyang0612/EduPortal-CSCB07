package com.example.b07project.studentPages.Review;

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


public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {

    Context context;
    ArrayList<Event> list;
    EventClickListener eventClickListener;


    public interface EventClickListener {
        void onEventClick(Event event);
    }

    public ReviewAdapter(Context context, ArrayList<Event> list, EventClickListener eventClickListener) {
        this.context = context;
        this.list = list;
        this.eventClickListener=eventClickListener;
    }

    @NonNull
    @Override
    public ReviewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_event_display_list,parent,
                false);
        return  new ReviewAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.MyViewHolder holder, int position) {

        Event eList = list.get(position);
        holder.eventName.setText(eList.getTitle());
        holder.dateTime.setText(eList.getEventDate());
        holder.eventLocation.setText(eList.getLocation());


        holder.buttonComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eventClickListener != null) {
                    eventClickListener.onEventClick(eList);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView eventName, dateTime, eventLocation;
        Button buttonComment;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            eventName = itemView.findViewById(R.id.eventName);
            dateTime = itemView.findViewById(R.id.dateTime);
            eventLocation = itemView.findViewById(R.id.eventLocation);
            buttonComment = itemView.findViewById(R.id.rsvpButton);
            buttonComment.setText("Add comments or reviews");
        }

    }

}