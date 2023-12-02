package com.example.b07project.adminPages.EventReviews;

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

public class EventReviewAdapter extends RecyclerView.Adapter<EventReviewAdapter.MyViewHolder> {

    Context context;

    ArrayList<Review> list;




    public EventReviewAdapter(Context context, ArrayList<Review> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public EventReviewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.review_boxes,parent,false);
        return  new EventReviewAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventReviewAdapter.MyViewHolder holder, int position) {

        Review eList = list.get(position);

        if (eList.getcomments().equals("")){
            holder.itemView.setVisibility(View.GONE);
        }
        else{
            holder.Comments.setText(eList.getcomments());
            holder.Username.setText(eList.getUserName());
            holder.UserID.setText(eList.getUserId());

        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Username, UserID, Comments;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Username = itemView.findViewById(R.id.textUsername);
            UserID = itemView.findViewById(R.id.textUserID);
            Comments = itemView.findViewById(R.id.textReview);
        }

    }

}