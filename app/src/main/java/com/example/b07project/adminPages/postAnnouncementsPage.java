package com.example.b07project.adminPages;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

import com.example.b07project.R;
import com.example.b07project.studentPages.Announcement.Announcement;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class postAnnouncementsPage extends AppCompatActivity {

    private EditText editTextTitle;
    private EditText editTextDescription;
    private Button buttonPost;
    private Button buttonBack;
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
                //checks for valid title and description
                if (editTextTitle.getText().toString().trim().isEmpty() || editTextDescription.getText().toString().trim().isEmpty()){
                    Toast toast = Toast.makeText(postAnnouncementsPage.this, "Title or Description cannot be empty!", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    sendAnnouncementToDB();
                }
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

    private void showPostedAlertDialog() {
        //show posted notification and leads back to home page
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Posted!")
                .setMessage("Announcement is available to students!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(postAnnouncementsPage.this, adminHomePage.class);
                        startActivity(intent);
                        finish();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void sendAnnouncementToDB(){
        //create and send announcement to firebase

        db = FirebaseDatabase.getInstance();
        ref = db.getReference("announcements");

        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(currentDate);

        String announcementID = ref.push().getKey();
        String userId = getIntent().getStringExtra("userId");
        String userName = getIntent().getStringExtra("userName");
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        String postTime = formattedDate;

        Announcement announcement = new Announcement(announcementID, userId, userName, title, description, postTime);

        ref.child(announcementID).setValue(announcement);

        showPostedAlertDialog();
    }

}
