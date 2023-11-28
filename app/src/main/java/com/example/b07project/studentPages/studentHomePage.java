package com.example.b07project.studentPages;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07project.LoginPage;
import com.example.b07project.R;
import com.example.b07project.adminPages.ComplaintAdapter.ComplaintAdapter;
import com.example.b07project.adminPages.ComplaintAdapter.RecyclerViewInterface;
import com.example.b07project.adminPages.adminComplaintContentPage2;
import com.example.b07project.adminPages.adminComplaintPage;
import com.example.b07project.studentPages.Announcement.Announcement;
import com.example.b07project.studentPages.AnnouncementRecycler.AnnouncementAdapter;
import com.example.b07project.studentPages.AnnouncementRecycler.AnnouncementRecycleInterface;
import com.example.b07project.studentPages.Complaint.Complaint;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class studentHomePage extends AppCompatActivity implements AnnouncementRecycleInterface {

    private Button buttonLogout, buttonComplaints, buttonPOST;
    ArrayList<Announcement> announcements= new ArrayList<>();
    FirebaseDatabase db;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_student_home_page);

        buttonLogout = findViewById(R.id.logoutNavButton);
        buttonComplaints = findViewById(R.id.complaintsPageNavButton);
        buttonPOST = findViewById(R.id.POSTNavButton);
        RecyclerView recyclerView = findViewById(R.id.studentHomePageRecycle);
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
    }

    private void setAnnouncements(){
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
                    String postTime = userSnapshot.child("postTime").getValue(String.class);
                    announcements.add(new Announcement(announcementId, userId, userName, title, description, postTime));

                }

                // userIds now contains all the user IDs from the "users" node
                // You can store, process, or use these IDs as needed
                RecyclerView recyclerView = findViewById(R.id.studentHomePageRecycle);
                AnnouncementAdapter adapter = new AnnouncementAdapter(studentHomePage.this, studentHomePage.this, announcements);
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

}
