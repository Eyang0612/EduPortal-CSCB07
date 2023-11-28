package com.example.b07project.studentPages;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
}
