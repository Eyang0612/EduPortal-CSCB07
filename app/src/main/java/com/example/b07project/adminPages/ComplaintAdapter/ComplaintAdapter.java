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
    Context context;
    ArrayList<Complaint> complaints;
    public ComplaintAdapter(Context context, ArrayList<Complaint> complaints){
        this.context = context;
        this.complaints = complaints;
    }
    @NonNull
    @Override
    public ComplaintAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //returns the views for our layout
        LayoutInflater inflator = LayoutInflater.from(context);
        View view = inflator.inflate(R.layout.admin_complaint_box, parent, false);

        return new ComplaintAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintAdapter.MyViewHolder holder, int position) {
        //assign values to the views, based on the position of our layout
        holder.complaintId.setText(complaints.get(position).getComplaintID());
        holder.complaintTitle.setText(complaints.get(position).getTitle());
        holder.complaintUserName.setText(complaints.get(position).getUserName());


    }

    @Override
    public int getItemCount() {
        //the number of item you want to display
        return complaints.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView complaintId, complaintTitle, complaintUserName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            complaintId = itemView.findViewById(R.id.textView2);
            complaintTitle = itemView.findViewById(R.id.textView3);
            complaintUserName = itemView.findViewById(R.id.textView4);
        }
    }
}
