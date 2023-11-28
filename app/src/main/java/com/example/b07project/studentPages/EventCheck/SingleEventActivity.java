package com.example.b07project.studentPages.EventCheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.b07project.R;

import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.view.View;
import android.widget.Toast;


public class SingleEventActivity extends AppCompatActivity {

    private DatabaseReference eventsRef;
    private Reservation res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_event);

        // Retrieve the event UID from the intent
        //String eventUid = getIntent().getStringExtra("eventUid");
        //String studentUid = getIntent().getStringExtra("studentUid");
        res = getIntent().getSerializableExtra("Reservation", Reservation.class);


        // Initialize Firebase Database reference
        eventsRef = FirebaseDatabase.getInstance().getReference().child("events");

        // Load and display event details
        loadEventDetails(res.getEvent().getEventId());

        // Handle Reserve button click
        Button reserveButton = findViewById(R.id.reserveButton);
        reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement your logic when the Reserve button is clicked
                // For example, you can add the user to the event or navigate to another page

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
                    String publisher = dataSnapshot.child("publisher").getValue(String.class);
                    String publishDate = dataSnapshot.child("postDate").getValue(String.class);
                    String eventDate = dataSnapshot.child("eventDate").getValue(String.class);
                    String eventTime = dataSnapshot.child("time").getValue(String.class);
                    String location = dataSnapshot.child("location").getValue(String.class);
                    String description = dataSnapshot.child("description").getValue(String.class);
                    long totalLimit = dataSnapshot.child("limit").getValue(Long.class);
                    long participantsCount = dataSnapshot.child("Participants").getChildrenCount();

                    // Calculate remaining seats
                    long remainingSeats = totalLimit - participantsCount;

                    // Display event details in your UI
                    displayEventDetails(title, publisher, publishDate,eventDate, eventTime, location, description, remainingSeats);
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
        Intent mainActivityIntent = new Intent(this, MainEventsActivity.class);
        mainActivityIntent.putExtra("Reservation", res);
        startActivity(mainActivityIntent);
        finish();//finish current activity; (Sign in).
    }
}
