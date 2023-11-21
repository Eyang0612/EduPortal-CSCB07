package com.example.b07project.studentPages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.b07project.LoginPage;
import com.example.b07project.R;


public class studentHomePage extends AppCompatActivity {

    private Button buttonLogout, buttonComplaints, buttonPOST;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_student_home_page);

        buttonLogout = findViewById(R.id.logoutNavButton);
        buttonComplaints = findViewById(R.id.complaintsPageNavButton);
        buttonPOST = findViewById(R.id.POSTNavButton);

        buttonComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(studentHomePage.this, ComplaintsPage.class);
                String userId = getIntent().getStringExtra("userId");
                String userName = getIntent().getStringExtra("userName");
                intent.putExtra("userId", userId);
                intent.putExtra("userName", userName);
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
                startActivity(intent);
            }
        });
    }
}
