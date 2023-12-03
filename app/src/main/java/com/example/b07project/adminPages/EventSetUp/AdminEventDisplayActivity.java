package com.example.b07project.adminPages.EventSetUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07project.R;
import com.example.b07project.adminPages.EventReviews.AdminEventReviewActivity;
import com.example.b07project.adminPages.adminHomePage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminEventDisplayActivity extends AppCompatActivity implements EventRatingAdapter.EventClickListener{

    RecyclerView recyclerView;
    DatabaseReference database;
    EventRatingAdapter myAdapter;
    ArrayList<Event> list;

    Button Addnewevent, Homepage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_event_display_page);

        recyclerView = findViewById(R.id.eventList);
        database = FirebaseDatabase.getInstance().getReference("events");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new EventRatingAdapter(this,list, this);
        recyclerView.setAdapter(myAdapter);

        Addnewevent=findViewById(R.id.AddEvent);
        Addnewevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminEventDisplayActivity.this, EventSetupPageActivity.class);
                startActivity(intent);
            }
        });

        Homepage=findViewById(R.id.back);
        Homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminEventDisplayActivity.this, adminHomePage.class);
                startActivity(intent);
            }
        });


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Event eList = dataSnapshot.getValue(Event.class);
                    list.add(eList);


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
        Toast.makeText(this, "Event Name: " + event.getTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AdminEventDisplayActivity.this, AdminEventReviewActivity.class);
        intent.putExtra("EventID", event.getEventId());
        intent.putExtra("EventName", event.getTitle());
        startActivity(intent);
    }

    //@Override
    //public void onBackClick(){
    //    Intent intent = new Intent(MainEventsActivity.this, adminHomePage.class);
    //    //intent.putExtra("userName", getIntent().getStringExtra("userName"));
    //    startActivity(intent);
    //}
}