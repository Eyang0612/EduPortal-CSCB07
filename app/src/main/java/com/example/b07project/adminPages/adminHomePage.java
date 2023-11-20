package com.example.b07project.adminPages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.b07project.R;
import com.example.b07project.studentPages.QualificationPage2;
import com.example.b07project.studentPages.QualificationPage3;

public class adminHomePage extends AppCompatActivity implements View.OnClickListener {
    private TextView Name, Email, Role;
    private Button ComplaintButton, EventButton, AnnounceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

        Name = findViewById(R.id.userEmailTextView);
        Email = findViewById(R.id.userNameTextView);
        Role= findViewById(R.id.userRoleTextView);
        ComplaintButton = findViewById(R.id.CheckComplaint);
        EventButton = findViewById(R.id.AddEvent);
        AnnounceButton= findViewById(R.id.AddAnnoucement);

        ComplaintButton.setOnClickListener(this);
        EventButton.setOnClickListener(this);
        AnnounceButton.setOnClickListener(this);


        Name.setText(getIntent().getStringExtra("userName"));
        Email.setText(getIntent().getStringExtra("userName"));
        Role.setText(getIntent().getStringExtra("userRole"));
    }

    @Override
    public void onClick(View v) {
        Button clickedChoice = (Button) (v);
        if(clickedChoice.getId()==R.id.AddEvent) {
            Intent intent = new Intent(adminHomePage.this, EventSetupPageActivity.class);
            startActivity(intent);
        }
        else if(clickedChoice.getId()==R.id.CheckComplaint) {
            Intent intent = new Intent(adminHomePage.this, adminComplaintPage.class);
            startActivity(intent);
        }
        else if(clickedChoice.getId()==R.id.AddAnnoucement) {
            Intent intent = new Intent(adminHomePage.this, postAnnouncementsPage.class);
            startActivity(intent);
        }
    }
}