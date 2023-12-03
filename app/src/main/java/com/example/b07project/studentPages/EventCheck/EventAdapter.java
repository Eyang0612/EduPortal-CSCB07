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
    EventClickListener eventClickListener;

    //BackButtonClick btn;

    public interface EventClickListener {
        void onEventClick(Event event);
    }

    //public interface BackButtonClick{
    //    void onBackClick();
    //}
    public EventAdapter(Context context, ArrayList<Event> list, EventClickListener eventClickListener) {
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
        holder.firstName.setText(eList.getTitle());
        holder.lastName.setText(eList.getEventDate());
        holder.age.setText(eList.getLocation());

        //holder.backButton.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        btn.onBackClick();
         //   }
        //});

        holder.rsvpButton.setOnClickListener(new View.OnClickListener() {
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

        TextView firstName, lastName, age;
        Button rsvpButton, backButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.tvfirstName);
            lastName = itemView.findViewById(R.id.tvlastName);
            age = itemView.findViewById(R.id.tvage);
            rsvpButton = itemView.findViewById(R.id.rsvpButton);
            //backButton=itemView.findViewById(R.id.backButton);

        }

    }

}