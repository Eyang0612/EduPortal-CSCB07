package com.example.b07project.studentPages.EventCheck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.b07project.R;
import com.example.b07project.adminPages.EventSetUp.Event;
import com.example.b07project.adminPages.adminHomePage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainEventsActivity extends AppCompatActivity implements EventAdapter.EventClickListener{

    RecyclerView recyclerView;
    DatabaseReference database;
    EventAdapter myAdapter;
    ArrayList<Event> list;

    //Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_events);

        recyclerView = findViewById(R.id.eventList);
        database = FirebaseDatabase.getInstance().getReference("events");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new EventAdapter(this,list, this);
        recyclerView.setAdapter(myAdapter);
        //backButton=findViewById(R.id.backButton);

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
        //put other redirect actions here
        //Intent SingleEventintent = new Intent(MainEventsActivity.this, SingleEventActivity.class);
        //SingleEventintent.putExtra(new Reservation())
        Toast.makeText(this, "Event Name: " + event.getTitle(), Toast.LENGTH_SHORT).show();
    }

    //@Override
    //public void onBackClick(){
    //    Intent intent = new Intent(MainEventsActivity.this, adminHomePage.class);
    //    //intent.putExtra("userName", getIntent().getStringExtra("userName"));
    //    startActivity(intent);
    //}
}