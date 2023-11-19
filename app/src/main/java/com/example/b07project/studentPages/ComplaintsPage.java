package com.example.b07project.studentPages;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;

import com.example.b07project.R;


public class ComplaintsPage extends AppCompatActivity {

    private EditText editTextTitle;
    private EditText editTextDescription;
    private Button buttonSubmit;

    private Button buttonBack;

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
                showSubmittedAlertDialog();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComplaintsPage.this, StudentHomePage.class);
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
                        Intent intent = new Intent(ComplaintsPage.this, StudentHomePage.class);
                        startActivity(intent);
                        finish();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}

