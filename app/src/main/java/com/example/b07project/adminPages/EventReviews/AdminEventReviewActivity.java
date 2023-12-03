package com.example.b07project.adminPages.EventReviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07project.R;
import com.example.b07project.adminPages.EventSetUp.AdminEventDisplayActivity;
import com.example.b07project.adminPages.adminHomePage;
import com.example.b07project.studentPages.Review.Review;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public class AdminEventReviewActivity extends AppCompatActivity{
    RecyclerView recyclerView;
    DatabaseReference database;
    EventReviewAdapter myAdapter;
    ArrayList<Review> list;
    TextView RatingTitle, Ratingcount;
    Button Back, Homepage;
    RatingBar ratingBar;
    int count =0;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_review_display_page);

        String eventID = getIntent().getStringExtra("EventID");
        ratingBar = findViewById(R.id.Rating);

        Ratingcount = findViewById(R.id.Ratingcount);
        RatingTitle = findViewById(R.id.EventName);
        RatingTitle.setText("The rating of "+ getIntent().getStringExtra("EventName")+" is");

        recyclerView = findViewById(R.id.reviewList);
        database = FirebaseDatabase.getInstance().getReference("events").child(eventID).child("Reviews");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new EventReviewAdapter(this,list);
        recyclerView.setAdapter(myAdapter);

        Homepage=findViewById(R.id.home);
        Homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminEventReviewActivity.this, adminHomePage.class);
                startActivity(intent);
            }
        });

        Back=findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminEventReviewActivity.this, AdminEventDisplayActivity.class);
                startActivity(intent);
            }
        });

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    count = 0;
                    double totalRating = 0.0;

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        count++;
                        totalRating += dataSnapshot.child("ratingValue").getValue(double.class);

                        if (!dataSnapshot.child("comments").getValue(String.class).equals("")){
                            String userName = dataSnapshot.child("userName").getValue(String.class);
                            String userID = dataSnapshot.child("userId").getValue(String.class);
                            double ratingValue = dataSnapshot.child("ratingValue").getValue(double.class);
                            String comments = dataSnapshot.child("comments").getValue(String.class);
                            Review eList = new Review(userName, userID, ratingValue, comments);
                            list.add(eList);
                        }
                    }

                    myAdapter.notifyDataSetChanged();
                    Ratingcount.setText(Integer.toString(count) + " students rated");


                    ratingBar.setRating((float) (Math.round(totalRating / count * 4) / 4.0));
                        ratingBar.setIsIndicator(true);

                } else {
                    ratingBar.setRating(0.0f);
                    ratingBar.setIsIndicator(true);
                    Ratingcount.setText("0 students rated");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}