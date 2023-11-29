package com.example.b07project.studentPages.AnnouncementRecycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07project.R;
import com.example.b07project.studentPages.Announcement.Announcement;


import java.util.ArrayList;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.MyViewHolder>{
    Context context;
    ArrayList<Announcement> announcements;
    public AnnouncementAdapter(Context context, ArrayList<Announcement> announcements){
        this.context = context;
        this.announcements = announcements;
    }
    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //returns the views for our layout
        LayoutInflater inflator = LayoutInflater.from(context);
        View view = inflator.inflate(R.layout.student_homepage_announcement_box, parent, false);

        return new AnnouncementAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnnouncementAdapter.MyViewHolder holder, int position) {
        //assign values to the views, based on the position of our layout
        holder.announcementPostTime.setText(announcements.get(position).getPostDate());
        holder.announcementTitle.setText(announcements.get(position).getTitle());
        holder.announcementDescription.setText(announcements.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        //the number of item you want to display
        return announcements.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView announcementPostTime, announcementTitle, announcementDescription;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            announcementPostTime = itemView.findViewById(R.id.announcementPostTime);
            announcementTitle = itemView.findViewById(R.id.announcementTitle);
            announcementDescription = itemView.findViewById(R.id.announcementDescription);

        }
    }
}
