package com.example.b07project.studentPages.EventCheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.b07project.R;
import com.example.b07project.studentPages.studentHomePage;

import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.widget.Toast;


public class SingleEventActivity extends AppCompatActivity {

    private DatabaseReference eventsRef;
    private String eventUid;
    private String studentUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_event);

        // Retrieve the event UID from the intent
        eventUid = getIntent().getStringExtra("eventID");
        studentUid = getIntent().getStringExtra("studentID");
        //res = getIntent().getSerializableExtra("Reservation", Reservation.class);


        // Initialize Firebase Database reference
        eventsRef = FirebaseDatabase.getInstance().getReference().child("events");

        // Load and display event details
        loadEventDetails(eventUid);
        checkReservationStatus();
        // Handle Reserve button click
        Button reserveButton = findViewById(R.id.reserveButton);
        reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement your logic when the Reserve button is clicked
                // For example, you can add the user to the event or navigate to another page

                reserveSeatInEvent(eventUid, studentUid);
                createReservedNodeInStudent(eventUid,studentUid);
                updateButtonState(true);


            }
        });

        Button backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call finish to close the current activity and go back to the previous one
                onBacktoMainEvents();
            }
        });
    }

    private void loadEventDetails(String eventUid) {
        // Use the eventUid to fetch event details from Firebase Realtime Database
        eventsRef.child(eventUid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Check if the event exists
                if (dataSnapshot.exists()) {
                    // Parse event details
                    String title = dataSnapshot.child("title").getValue(String.class);
                    String publisher = dataSnapshot.child("adminName").getValue(String.class);
                    String publishDate = dataSnapshot.child("postDate").getValue(String.class);
                    String eventDate = dataSnapshot.child("eventDate").getValue(String.class);
                    String eventTime = dataSnapshot.child("time").getValue(String.class);
                    String location = dataSnapshot.child("location").getValue(String.class);
                    String description = dataSnapshot.child("description").getValue(String.class);
                    String limitString = dataSnapshot.child("limit").getValue(String.class);
                    long totalLimit = 0;
                    if (limitString != null) {
                            totalLimit = Long.parseLong(limitString);
                    }
                    long participantsCount = dataSnapshot.child("Participants").getChildrenCount();

                    // Calculate remaining seats
                    long remainingSeats = totalLimit - participantsCount;

                    // Display event details in your UI
                    displayEventDetails(title, publisher, publishDate,eventDate, eventTime, location, description, remainingSeats);

                    updateButtonState(remainingSeats);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(SingleEventActivity.this, "Database Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayEventDetails(String title, String publisher, String publishDate, String eventDate, String eventTime, String location, String description, long remain) {
        // Example: Display event details in TextViews
        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView publisherInfoTextView = findViewById(R.id.publisherInfoTextView);
        TextView eventDateTimeTextView = findViewById(R.id.eventDateTimeTextView);
        TextView locationTextView = findViewById(R.id.locationTextView);
        TextView descriptionTextView = findViewById(R.id.descriptiondetailTextView);
        TextView seatRemainingTextView = findViewById(R.id.seatRemainingTextView);

        titleTextView.setText(title);
        publisherInfoTextView.setText("Published by " + publisher + " on " + publishDate);
        eventDateTimeTextView.setText("Event Date"+eventDate + " " + eventTime + "EST");
        locationTextView.setText("Location: "+ location);
        descriptionTextView.setText(description);
        seatRemainingTextView.setText("Seats Remaining: " + remain);
    }

    private void onBacktoMainEvents() {
        // Handle the click event to navigate to the sign-up page
        Intent studentHomePageIntent = new Intent(this, studentHomePage.class);
        studentHomePageIntent.putExtra("eventID", eventUid);
        studentHomePageIntent.putExtra("studentID", studentUid);
        startActivity(studentHomePageIntent);
        finish();
    }

    private void reserveSeatInEvent(String eventUid, String studentUid) {
        // Add the student UID to the Participants node of the event
        DatabaseReference participantsRef = eventsRef.child(eventUid).child("Participants");
        participantsRef.child(studentUid).setValue(true);
    }

    private void createReservedNodeInStudent( String eventUid, String studentUid) {
        // Create a new node called "reservedNode" in the student's node and save the event UID in it
        DatabaseReference studentRef = FirebaseDatabase.getInstance().getReference().child("users").child(studentUid);
        DatabaseReference reservedNodeRef = studentRef.child("reservedEvent");

        reservedNodeRef.child(eventUid).setValue(true);
    }

    private void updateButtonState(boolean reserved) {
        Button reserveButton = findViewById(R.id.reserveButton);

        if (reserved) {
            reserveButton.setEnabled(false);
            reserveButton.setBackgroundColor(ContextCompat.getColor(this, R.color.gray));
            reserveButton.setText("Reserved");
        }
    }

    private void updateButtonState(long remainingSeats) {
        Button reserveButton = findViewById(R.id.reserveButton);

        if (remainingSeats == 0) {
            reserveButton.setEnabled(false);
            reserveButton.setBackgroundColor(ContextCompat.getColor(this, R.color.gray));
            reserveButton.setText("Fully Booked");
        }
    }

    private void checkReservationStatus() {
        DatabaseReference participantsRef = eventsRef.child(eventUid).child("Participants");
        participantsRef.child(studentUid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean reserved = dataSnapshot.exists();
                updateButtonState(reserved);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(SingleEventActivity.this, "Firebase Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
