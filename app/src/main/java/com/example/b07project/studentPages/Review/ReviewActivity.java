package com.example.b07project.studentPages.Review;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.b07project.R;
import com.example.b07project.adminPages.EventSetUp.Event;
import com.example.b07project.studentPages.EventCheck.EventAdapter;
import com.example.b07project.studentPages.studentHomePage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity implements EventAdapter.RSVPClickListener {

    RecyclerView recyclerView;
    FirebaseDatabase db;
    Button backButton;

    DatabaseReference ref;
    ReviewAdapter myAdapter;
    ArrayList<Event> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        recyclerView = findViewById(R.id.recyclerView);
        backButton = findViewById(R.id.reviewBackButton);
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("events");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new ReviewAdapter(this,list, this::onEventClick);
        recyclerView.setAdapter(myAdapter);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReviewActivity.this, studentHomePage.class);
                startActivity(intent);
            }
        });

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    SharedPreferences p = getSharedPreferences("myprefs", Context.MODE_PRIVATE);
                    String userId = p.getString("userId", "None");
                    if(dataSnapshot.child("Participants").child(userId).exists()){
                        Event eList = dataSnapshot.getValue(Event.class);
                        list.add(eList);
                    }



                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public void onEventClick(Event event) {
        //put other redirect actions here
        SharedPreferences p = getSharedPreferences("myprefs", Context.MODE_PRIVATE);
        String userId = p.getString("userId", "None");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(event.getEventId()).child("Reviews").
                        child(userId).exists()) {
                    Toast.makeText(ReviewActivity.this, "You already submitted",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(ReviewActivity.this, EventRating.class);
                    intent.putExtra("Event", event);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }
        });
    }

}