package com.example.b07project.studentPages;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.b07project.R;


public class test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_test_page);
        // Retrieve extras
        String email = getIntent().getStringExtra("email");
        String userName = getIntent().getStringExtra("userName");

        // Now you can use 'email' and 'userName' as needed in your 'test' activity
        // For example, display them in TextViews
        TextView emailTextView = findViewById(R.id.emailTextView);
        TextView userNameTextView = findViewById(R.id.userNameTextView);

        emailTextView.setText("Email: " + email);
        userNameTextView.setText("User Name: " + userName);
    }
}
