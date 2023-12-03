package com.example.b07project.adminPages.ComplaintAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.b07project.R;
import com.example.b07project.studentPages.Complaint.Complaint;

import java.util.ArrayList;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.MyViewHolder> {
    private final RecyclerViewInterface Recycler_Interface;
    Context context;
    ArrayList<Complaint> complaints;
    public ComplaintAdapter(RecyclerViewInterface Recycler_Interface, Context context, ArrayList<Complaint> complaints){
        this.Recycler_Interface = Recycler_Interface;
        this.context = context;
        this.complaints = complaints;
    }
    @NonNull
    @Override
    public ComplaintAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //returns the views for our layout
        LayoutInflater inflator = LayoutInflater.from(context);
        View view = inflator.inflate(R.layout.admin_complaint_box, parent, false);

        return new ComplaintAdapter.MyViewHolder(view, Recycler_Interface);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintAdapter.MyViewHolder holder, int position) {
        //assign values to the views, based on the position of our layout
        holder.complaintId.setText(complaints.get(position).getComplaintID());
        holder.complaintTitle.setText(complaints.get(position).getTitle());
        holder.complaintUserName.setText(complaints.get(position).getUserName());
        holder.complaintTime.setText(complaints.get(position).getSubmissionTime());


    }

    @Override
    public int getItemCount() {
        //the number of item you want to display
        return complaints.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView complaintId, complaintTitle, complaintUserName, complaintTime;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface Recycler_Interface) {
            super(itemView);

            complaintId = itemView.findViewById(R.id.complaintId);
            complaintTitle = itemView.findViewById(R.id.complaintTitle);
            complaintUserName = itemView.findViewById(R.id.name);
            complaintTime = itemView.findViewById(R.id.complaintTime);

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
