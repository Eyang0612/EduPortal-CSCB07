package com.example.b07project.adminPages.EventSetUp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.b07project.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EventSetupPageActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDatePicker, btnTimePicker, btnSubmit;
    EditText txtDate, txtTime, edtTitle, edtLocation, edtLimit, edtDescription;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_setup_page);

        // Finding buttons
        btnDatePicker = findViewById(R.id.btn_date);
        btnTimePicker = findViewById(R.id.btn_time);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Finding texts to add to db
        txtDate = findViewById(R.id.in_date);
        txtTime = findViewById(R.id.in_time);
        edtTitle = findViewById(R.id.etTitle);
        edtLocation = findViewById(R.id.etLocation);
        edtLimit = findViewById(R.id.etLimit);
        edtDescription = findViewById(R.id.etDescription);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnDatePicker) {
            showDatePicker();
        } else if (v == btnTimePicker) {
            showTimePicker();
        } else if (v == btnSubmit) {
            uploadEventData();
        }
    }

    private void showDatePicker() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void showTimePicker() {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        txtTime.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private void uploadEventData() {
        // Get values from UI components
        String title = edtTitle.getText().toString();
        String location = edtLocation.getText().toString();
        String date = txtDate.getText().toString();
        String time = txtTime.getText().toString();
        String limit = edtLimit.getText().toString();
        String description = edtDescription.getText().toString();

        // Initialize Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("events");

        // Check if any field is empty
        if (title.isEmpty() || location.isEmpty() || date.isEmpty() || time.isEmpty() || limit.isEmpty() || description.isEmpty()) {
            Toast.makeText(EventSetupPageActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a unique key for the event
        String eventId = databaseReference.push().getKey();

        // Get the admin's name (replace with the actual method to obtain admin's name)
        String adminName = getAdminName(); // Assuming you have a method to get admin's name

        // Create an Event object with the input data
        Event event = new Event(eventId, adminName, title, location, date, time, limit, description);

        // Upload the event to Firebase
        databaseReference.child(eventId).setValue(event);

        // Show a success message
        Toast.makeText(EventSetupPageActivity.this, "Event added successfully", Toast.LENGTH_SHORT).show();

        // Clear the input fields
        edtTitle.setText("");
        edtLocation.setText("");
        txtDate.setText("");
        txtTime.setText("");
        edtLimit.setText("");
        edtDescription.setText("");
    }

    // Replace this with the actual method to obtain admin's name from your authentication system
    private String getAdminName() {
        String userName = getIntent().getStringExtra("userName");
        return userName;
    }
}
