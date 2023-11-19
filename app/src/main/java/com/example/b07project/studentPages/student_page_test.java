package com.example.b07project.studentPages;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.b07project.R;


public class student_page_test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_test_page);
        // Retrieve extras
        String userEmail = getIntent().getStringExtra("email");
        String userName = getIntent().getStringExtra("userName");
        String userRole = getIntent().getStringExtra("userRole");

        // Now you can use 'email' and 'userName' as needed in your 'test' activity
        // For example, display them in TextViews
        TextView userEmailTextView = findViewById(R.id.emailTextView);
        TextView userNameTextView = findViewById(R.id.userNameTextView);

        userEmailTextView.setText("Email: " + userEmail);
        userNameTextView.setText("User Name: " + userName);

    }
}
