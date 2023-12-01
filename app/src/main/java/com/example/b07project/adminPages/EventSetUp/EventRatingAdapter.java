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
import com.example.b07project.studentPages.Review.Review;

import java.util.ArrayList;

public class EventRatingAdapter extends RecyclerView.Adapter<EventRatingAdapter.MyViewHolder> {

    Context context;

    ArrayList<Event> list;
    EventClickListener eventClickListener;


    public interface EventClickListener {
        void onEventClick(Event event);
    }

    public EventRatingAdapter(Context context, ArrayList<Event> list, EventClickListener eventClickListener) {
        this.context = context;
        this.list = list;
        this.eventClickListener=eventClickListener;
        //this.btn=btn;
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



        holder.ReviewButton.setOnClickListener(new View.OnClickListener() {
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

        TextView EventName, DateTime, Location;
        Button ReviewButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            EventName = itemView.findViewById(R.id.tvfirstName);
            DateTime = itemView.findViewById(R.id.tvlastName);
            Location = itemView.findViewById(R.id.tvage);
            ReviewButton = itemView.findViewById(R.id.rsvpButton);
            //backButton=itemView.findViewById(R.id.backButton);

        }

    }

}