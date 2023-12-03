package com.example.b07project.studentPages.Review;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.b07project.R;
import com.example.b07project.adminPages.EventSetUp.Event;
import com.example.b07project.studentPages.studentHomePage;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class EventRating extends AppCompatActivity {

    private TextView Title;
    private EditText editTextComments;
    private RatingBar ratingBar;
    private float ratevalue;
    private Button buttonSubmit, buttonBack;
    private FirebaseDatabase db;
    private DatabaseReference ref;
    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_rating_page);

        Title = findViewById(R.id.EventName);
        editTextComments = findViewById(R.id.EditTextComments);
        ratingBar = findViewById(R.id.Rating);

        buttonSubmit = findViewById(R.id.buttonRatingSubmit);
        buttonBack = findViewById(R.id.buttonRatingBack);
        event = getIntent().getSerializableExtra("Event", Event.class);

        Title.setText("Rate "+ event.getTitle());

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratevalue = ratingBar.getRating();
            }
        });
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendReviewToDB();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // REMEMBER TO CHANGE THE INTENT ONCE THE RESERVED EVENT PAGE IS FINIHSED!!!!!!!!
                Intent intent = new Intent(EventRating.this, studentHomePage.class);
                startActivity(intent);
            }
        });

    }

    //show submitted notification and leads back to home page
    private void showSubmittedAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Submitted!")
                .setMessage("Thank you for your feedback!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // REMEMBER TO CHANGE THE INTENT ONCE THE RESERVED EVENT PAGE IS FINIHSED!!!!!!!!
                        Intent intent = new Intent(EventRating.this, studentHomePage.class);
                        startActivity(intent);
                        finish();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //create and send complaint to firebase
    private void sendReviewToDB(){
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("events");

        String eventID = event.getEventId();
        SharedPreferences p = getSharedPreferences("myprefs",
                Context.MODE_PRIVATE);
        String userId = p.getString("userId", "default_value");
        String userName = p.getString("userName", "default_value");
        String comments = editTextComments.getText().toString();
        if (comments.trim().isEmpty() || comments == null){ comments = "";}
        Review review = new Review(userName, userId, ratevalue, comments);

        ref.child(eventID).child("Reviews").child(userId).setValue(review);

        showSubmittedAlertDialog();
    }

}

