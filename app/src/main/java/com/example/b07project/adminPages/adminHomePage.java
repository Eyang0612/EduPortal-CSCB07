package com.example.b07project.adminPages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.b07project.R;

public class adminHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

        String userEmail = getIntent().getStringExtra("email");
        String userName = getIntent().getStringExtra("userName");
        String userRole = getIntent().getStringExtra("userRole");

        // Now you can use 'email' and 'userName' as needed in your 'test' activity
        // For example, display them in TextViews
        TextView userEmailTextView = findViewById(R.id.userEmailTextView);
        TextView userNameTextView = findViewById(R.id.userNameTextView);
        TextView userRoleTextView = findViewById(R.id.userRoleTextView);

        userEmailTextView.setText("User Email: " + userEmail);
        userNameTextView.setText("User Name: " + userName);
        userRoleTextView.setText("User Role:"+ userRole);
    }
}