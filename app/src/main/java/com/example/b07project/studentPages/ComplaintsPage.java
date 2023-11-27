package com.example.b07project.studentPages;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;

import com.example.b07project.adminPages.postAnnouncementsPage;
import com.example.b07project.studentPages.Complaint.Complaint;
import com.example.b07project.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ComplaintsPage extends AppCompatActivity {

    private EditText editTextTitle, editTextDescription;
    private Button buttonSubmit, buttonBack;
    private FirebaseDatabase db;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_complaints_page);

        editTextTitle = findViewById(R.id.editTextComplaintTitle);
        editTextDescription = findViewById(R.id.editTextComplaintDescription);
        buttonSubmit = findViewById(R.id.buttonComplaintSubmit);
        buttonBack = findViewById(R.id.buttonComplaintBack);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextTitle.getText().toString().trim().isEmpty() || editTextDescription.getText().toString().trim().isEmpty()){
                    Toast toast = Toast.makeText(ComplaintsPage.this, "Title or Description cannot be empty!", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    sendComplaintToDB();
                }
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComplaintsPage.this, studentHomePage.class);
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
                        Intent intent = new Intent(ComplaintsPage.this, studentHomePage.class);
                        startActivity(intent);
                        finish();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //create and send complaint to firebase
    private void sendComplaintToDB(){
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("complaints");

        String complaintID = ref.push().getKey();
        String userId = getIntent().getStringExtra("userId");
        String userName = getIntent().getStringExtra("userName");
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        Complaint complaint = new Complaint(complaintID, userId, userName, title, description);

        ref.child(complaintID).setValue(complaint);

        showSubmittedAlertDialog();
    }

}

