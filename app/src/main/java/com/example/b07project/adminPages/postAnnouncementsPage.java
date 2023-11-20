package com.example.b07project.adminPages;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.b07project.R;
import com.example.b07project.studentPages.Announcement.Announcement;
import com.example.b07project.studentPages.Complaint.Complaint;
import com.example.b07project.studentPages.ComplaintsPage;
import com.example.b07project.studentPages.studentHomePage;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class postAnnouncementsPage extends AppCompatActivity {

    private EditText editTextTitle, editTextDescription;
    private Button buttonPost, buttonBack;
    private FirebaseDatabase db;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_post_announcements_page);

        editTextTitle = findViewById(R.id.editTextAnnouncementTitle);
        editTextDescription = findViewById(R.id.editTextAnnouncementDescription);
        buttonPost = findViewById(R.id.buttonAnnouncementPost);
        buttonBack = findViewById(R.id.buttonAnnouncementBack);

        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAnnouncementToDB();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(postAnnouncementsPage.this, adminHomePage.class);
                startActivity(intent);
            }
        });

    }

    //show posted notification and leads back to home page
    private void showSubmittedAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Posted!")
                .setMessage("Announcement is available to students!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(postAnnouncementsPage.this, studentHomePage.class);
                        startActivity(intent);
                        finish();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //create and send announcement to firebase
    private void sendAnnouncementToDB(){
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("announcements");

        String announcementID = ref.push().getKey();
        String userId = getIntent().getStringExtra("userId");
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        Announcement announcement = new Announcement(announcementID, userId, title, description);

        ref.child(announcementID).setValue(announcement);

        showSubmittedAlertDialog();
    }

}
