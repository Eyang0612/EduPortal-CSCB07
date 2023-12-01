package com.example.b07project.adminPages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.b07project.R;
import com.example.b07project.adminPages.EventSetUp.AdminEventDisplayActivity;
import com.example.b07project.adminPages.EventSetUp.EventSetupPageActivity;
import com.example.b07project.adminPages.adminComplaintPage;
import com.example.b07project.studentPages.EventCheck.MainEventsActivity;
import com.example.b07project.studentPages.QualificationPage2;
import com.example.b07project.studentPages.QualificationPage3;
import com.example.b07project.LoginPage;
import com.example.b07project.studentPages.studentHomePage;

public class adminHomePage extends AppCompatActivity{
    private TextView Name, Email, Role;
    private Button ComplaintButton, AnnounceButton;
    //test button to display events page

    private Button testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

        Name = findViewById(R.id.userNameTextView);
        Email = findViewById(R.id.userEmailTextView);
        Role = findViewById(R.id.userRoleTextView);
        ComplaintButton = findViewById(R.id.CheckComplaint);
        AnnounceButton = findViewById(R.id.AddAnnoucement);

        //test code below
        testButton=findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminHomePage.this, AdminEventDisplayActivity.class);
                //intent.putExtra("userName", getIntent().getStringExtra("userName"));
                startActivity(intent);
            }
        });
        //test code above

        /*ComplaintButton.setOnClickListener(this);
        EventButton.setOnClickListener(this);
        AnnounceButton.setOnClickListener(this);*/
        SharedPreferences p = getSharedPreferences("myprefs",
                Context.MODE_PRIVATE);
        Name.setText(p.getString("userName", "default_value"));
        Email.setText(p.getString("email", "default_value"));
        Role.setText(p.getString("userRole", "default_value"));

        ComplaintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminHomePage.this, adminComplaintPage.class);
                startActivity(intent);
            }
        });
        AnnounceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminHomePage.this, postAnnouncementsPage.class);
                startActivity(intent);
            }
        });
    }



/*    @Override
    public void onClick(View v) {
        Button clickedChoice = (Button) (v);
        if(clickedChoice.getId()==R.id.AddEvent) {
            Intent intent = new Intent(adminHomePage.this, EventSetupPageActivity.class);
            intent.putExtra("userName", getIntent().getStringExtra("userName"));
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
    }*/
}