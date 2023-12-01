package com.example.b07project.studentPages;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07project.LoginPage;
import com.example.b07project.R;
import com.example.b07project.User.Student;
import com.example.b07project.adminPages.EventSetUp.Event;
import com.example.b07project.studentPages.Announcement.Announcement;
import com.example.b07project.studentPages.AnnouncementRecycler.AnnouncementAdapter;
import com.example.b07project.studentPages.EventCheck.EventAdapter;
import com.example.b07project.studentPages.EventCheck.MainEventsActivity;
import com.example.b07project.studentPages.EventCheck.Reservation;
import com.example.b07project.studentPages.EventCheck.SingleEventActivity;
import com.example.b07project.studentPages.EventRecycler.EventRecyclerInterface;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;


public class studentHomePage extends AppCompatActivity implements com.example.b07project.studentPages.EventCheck.EventAdapter.EventClickListener {

    private Button buttonLogout, buttonComplaints, buttonPOST;
    ToggleButton toggleButton;
    MaterialButtonToggleGroup toggleGroup;
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
        toggleGroup = findViewById(R.id.toggleGroup);
        toggleGroup.check(R.id.announcementsButton);
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

        toggleGroup.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (isChecked) {
                if (checkedId == R.id.announcementsButton) {
                    setAnnouncements();
                } else if (checkedId == R.id.eventsButton) {
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
                Collections.reverse(announcements);


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
        Toast.makeText(this, "pass", Toast.LENGTH_SHORT).show();
        RecyclerView recyclerView;
        DatabaseReference database;
        EventAdapter myAdapter;
        ArrayList<Event> list;

        recyclerView = findViewById(R.id.studentHomePageRecycle);
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

        Collections.sort(list, new DateComparator<>("dd-MM-yyyy"));
        Collections.reverse(list);


    }



    @Override
    public void onEventClick(Event e) {
        Intent intent = new Intent(studentHomePage.this, SingleEventActivity.class);
        SharedPreferences p = getSharedPreferences("myprefs", Context.MODE_PRIVATE);
        intent.putExtra("eventID", e.getEventId());
        intent.putExtra("studentID", p.getString("userId", "No suitable studentID"));
        //Toast.makeText(this, "Event Name: " + e.getTitle(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Student Name: " + p.getString("userID", "No suitable studentID"), Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

}
