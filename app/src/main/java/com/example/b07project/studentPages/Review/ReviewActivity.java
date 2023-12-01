package com.example.b07project.studentPages.Review;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.b07project.R;
import com.example.b07project.adminPages.EventSetUp.Event;
import com.example.b07project.studentPages.EventCheck.EventAdapter;
import com.example.b07project.studentPages.EventCheck.MainEventsActivity;
import com.example.b07project.studentPages.EventCheck.Reservation;
import com.example.b07project.studentPages.EventCheck.SingleEventActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity implements EventAdapter.EventClickListener{

    RecyclerView recyclerView;
    DatabaseReference db;
    ReviewAdapter myAdapter;
    ArrayList<Event> list;

    //Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        recyclerView = findViewById(R.id.recyclerView);
        db = FirebaseDatabase.getInstance().getReference("events");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new ReviewAdapter(this,list, this::onEventClick);
        recyclerView.setAdapter(myAdapter);
        //backButton=findViewById(R.id.backButton);

        db.addValueEventListener(new ValueEventListener() {
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
        Intent intent = new Intent(ReviewActivity.this, EventRating.class);
        intent.putExtra("Event", event);
        startActivity(intent);
        //Toast.makeText(this, "Event Name: " + event.getTitle(), Toast.LENGTH_SHORT).show();
    }

    //@Override
    //public void onBackClick(){
    //    Intent intent = new Intent(MainEventsActivity.this, adminHomePage.class);
    //    //intent.putExtra("userName", getIntent().getStringExtra("userName"));
    //    startActivity(intent);
    //}
}