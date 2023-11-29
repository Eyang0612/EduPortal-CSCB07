package com.example.b07project.studentPages;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07project.LoginPage;
import com.example.b07project.R;
import com.example.b07project.adminPages.EventSetUp.Event;
import com.example.b07project.studentPages.Announcement.Announcement;
import com.example.b07project.studentPages.AnnouncementRecycler.AnnouncementAdapter;
import com.example.b07project.studentPages.EventCheck.MainEventsActivity;
import com.example.b07project.studentPages.EventRecycler.EventAdapter;
import com.example.b07project.studentPages.EventRecycler.EventRecyclerInterface;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;


public class studentHomePage extends AppCompatActivity implements EventRecyclerInterface {

    private Button buttonLogout, buttonComplaints, buttonPOST;
    ToggleButton toggleButton;
    ArrayList<Event> events;
    FirebaseDatabase db;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_student_home_page);

        buttonLogout = findViewById(R.id.logoutNavButton);
        buttonComplaints = findViewById(R.id.complaintsPageNavButton);
        buttonPOST = findViewById(R.id.POSTNavButton);
        toggleButton = findViewById(R.id.toggleButton);
        setAnnouncements();

        buttonComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(studentHomePage.this, ComplaintsPage.class);
                startActivity(intent);
            }
        });

        buttonPOST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(studentHomePage.this, QualificationPage1.class);
                startActivity(intent);
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(studentHomePage.this, LoginPage.class);
                SharedPreferences p = getSharedPreferences("myprefs",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = p.edit();

                // Clear all data
                editor.clear();

                // Apply changes
                editor.apply();
                startActivity(intent);
            }
        });

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            // If announcement is chosen, changes recycler view to display announcements. If not,
            // changes recycler view to display events
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Update the RecyclerView based on the toggle state
                if (isChecked) {
                    setAnnouncements();
                } else {
                    setEvents();
                }
            }
        });

    }

    private void setAnnouncements(){
        //set Announcements to the recycler view
        ArrayList<Announcement> announcements= new ArrayList<>();
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("announcements");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String announcementId = userSnapshot.child("announcementID").getValue(String.class);
                    String description = userSnapshot.child("description").getValue(String.class);
                    String title = userSnapshot.child("title").getValue(String.class);
                    String userId = userSnapshot.child("userId").getValue(String.class);
                    String userName = userSnapshot.child("userName").getValue(String.class);
                    String postDate = userSnapshot.child("postDate").getValue(String.class);
                    announcements.add(new Announcement(announcementId, userId, userName, title, description, postDate));

                }

                //sorting announcements by latest date to earliest
                Collections.sort(announcements, new DateComparator<>("dd-MM-yyyy"));

                // userIds now contains all the user IDs from the "users" node
                // You can store, process, or use these IDs as needed
                RecyclerView recyclerView = findViewById(R.id.studentHomePageRecycle);
                AnnouncementAdapter adapter = new AnnouncementAdapter(studentHomePage.this,  announcements);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(studentHomePage.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
                Intent intent = new Intent(studentHomePage.this, LoginPage.class);
                startActivity(intent);
            }

        });

    }

    private void setEvents(){
        //set Events to the recycler view
        ArrayList<Event> events= new ArrayList<>();
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("events");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String eventId = userSnapshot.child("eventId").getValue(String.class);
                    String title = userSnapshot.child("title").getValue(String.class);
                    String location = userSnapshot.child("location").getValue(String.class);
                    String eventDate = userSnapshot.child("eventDate").getValue(String.class);
                    String time = userSnapshot.child("time").getValue(String.class);;
                    String limit = userSnapshot.child("limit").getValue(String.class);;
                    String adminName = userSnapshot.child("adminName").getValue(String.class);
                    String description = userSnapshot.child("description").getValue(String.class);
                    String postDate = userSnapshot.child("postDate").getValue(String.class);
                    events.add(new Event(eventId, title, location, eventDate, time, limit, adminName, description, postDate));

                }

                //sorting events by latest date to earliest
                Collections.sort(events, new DateComparator<>("dd-MM-yyyy"));

                // userIds now contains all the user IDs from the "users" node
                // You can store, process, or use these IDs as needed
                RecyclerView recyclerView = findViewById(R.id.studentHomePageRecycle);
                EventAdapter adapter = new EventAdapter(studentHomePage.this ,studentHomePage.this,  events);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(studentHomePage.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
                Intent intent = new Intent(studentHomePage.this, LoginPage.class);
                startActivity(intent);
            }

        });

    }

    @Override
    public void ClickItem(int position) {
        Intent intent = new Intent(studentHomePage.this, MainEventsActivity.class);
        intent.putExtra("eventId", events.get(position).getEventId());
        intent.putExtra("title",events.get(position).getTitle());
        intent.putExtra("location",events.get(position).getLocation());
        intent.putExtra("eventDate",events.get(position).getEventDate());
        intent.putExtra("time",events.get(position).getTime());
        intent.putExtra("limit",events.get(position).getLimit());
        intent.putExtra("adminName",events.get(position).getAdminName());
        intent.putExtra("description",events.get(position).getDescription());
        intent.putExtra("postDate",events.get(position).getPostDate());
        startActivity(intent);
    }

}
