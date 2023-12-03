package com.example.b07project.adminPages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.b07project.R;


public class adminComplaintContentPage2 extends AppCompatActivity {

    private TextView Title, UserName, UserID, Description;
    private Button buttonBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_complaint_content);

        Title = findViewById(R.id.TextComplaintTitle);
        UserName = findViewById(R.id.ComplaintUserName);
        UserID = findViewById(R.id.ComplaintUserID);
        Description = findViewById(R.id.ComplaintDescription);
        buttonBack = findViewById(R.id.buttonComplaintBack);

        Title.setText(getIntent().getStringExtra("Title"));
        UserID.setText(getIntent().getStringExtra("UserID"));
        UserName.setText(getIntent().getStringExtra("Username"));
        Description.setText(getIntent().getStringExtra("Description"));

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminComplaintContentPage2.this, adminComplaintPage.class);
                startActivity(intent);
            }
        });

    }


}