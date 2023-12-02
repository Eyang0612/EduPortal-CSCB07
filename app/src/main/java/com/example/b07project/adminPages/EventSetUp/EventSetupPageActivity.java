package com.example.b07project.adminPages.EventSetUp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.b07project.R;
import com.example.b07project.adminPages.adminHomePage;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class EventSetupPageActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDatePicker, btnTimePicker, btnSubmit, btnBack;
    EditText txtDate, txtTime, edtTitle, edtLocation, edtLimit, edtDescription;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private DatabaseReference databaseReference;

    boolean isCurrentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_setup_page);

        // Finding buttons
        btnDatePicker = findViewById(R.id.btn_date);
        btnTimePicker = findViewById(R.id.btn_time);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnBack = findViewById(R.id.btnBack);

        // Finding texts to add to db
        txtDate = findViewById(R.id.in_date);
        txtTime = findViewById(R.id.in_time);
        //for date and time, do not allow user to input data directly
        txtDate.setEnabled(false);
        txtDate.setFocusable(false);
        txtTime.setEnabled(false);
        txtTime.setFocusable(false);

        edtTitle = findViewById(R.id.etTitle);
        edtLocation = findViewById(R.id.etLocation);
        edtLimit = findViewById(R.id.etLimit);
        edtDescription = findViewById(R.id.etDescription);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        btnBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == btnDatePicker) {
            showDatePicker();
        } else if (v == btnTimePicker) {
            showTimePicker();
        } else if (v == btnSubmit) {
            uploadEventData();
        } else if (v== btnBack) {
            onBacktoEventPage();
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

                        if (isDateInFuture(year, monthOfYear, dayOfMonth)) {

                            // Update the TextView with the selected date
                            txtDate.setText(formatDate(dayOfMonth, monthOfYear + 1, year));
                            isCurrentDate = false;
                            // If the date is valid, you can also show a TimePickerDialog or perform other actions.
                            // For simplicity, the TimePickerDialog is not included in this example.
                            // check if its the event is schedule in the current date
                        } else if(year == mYear && monthOfYear == mMonth && dayOfMonth == mDay){
                            isCurrentDate=true;
                            handleCurrentDate();

                        }
                        else {
                            // Show an error message or handle the invalid date as needed
                            Toast.makeText(EventSetupPageActivity.this, "Please select a future date for the event!.", Toast.LENGTH_SHORT).show();
                            isCurrentDate = false;
                        }

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private boolean isDateInFuture(int year, int month, int day) {
        return year > mYear || (year == mYear && month > mMonth) || (year == mYear && month == mMonth && day > mDay);
    }

    private void handleCurrentDate() {
        // Check if time is empty, if yes do nothing
        if (!txtTime.getText().toString().isEmpty()) {
            // If time is not empty, check if the current time is in the future
            int inputHour = Integer.parseInt(txtTime.getText().toString().substring(0, 2));
            int inputMinute = Integer.parseInt(txtTime.getText().toString().substring(3));

            // If time is in the past for the current date, show a message
            if (inputHour < mHour || (inputHour == mHour && inputMinute < mMinute)) {
                Toast.makeText(EventSetupPageActivity.this, "Please select a future time for today's event or choose another date!", Toast.LENGTH_SHORT).show();
            } else {
                // If date is the current date and time is in the future, update the date
                txtDate.setText(formatDate(mDay, mMonth + 1, mYear));
            }
        }else{
            txtDate.setText(formatDate(mDay, mMonth + 1, mYear));
        }
    }

    private String formatDate(int day, int month, int year) {
        return String.format(Locale.getDefault(), "%02d-%02d-%d", day, month, year);
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
                        if (isCurrentDate) {
                            // Check if date is current date and time is in the past
                            if (hourOfDay < mHour || (hourOfDay == mHour && minute < mMinute)) {
                                Toast.makeText(EventSetupPageActivity.this, "Please select a future time for today's event or choose another date!", Toast.LENGTH_SHORT).show();
                            } else {
                                // Set the selected time in the TextView
                                txtTime.setText(formatTime(hourOfDay, minute));
                            }
                        } else {
                            // Set the selected time in the TextView
                            txtTime.setText(formatTime(hourOfDay, minute));
                        }


                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private String formatTime(int hourOfDay, int minute) {
        // Use String.format for consistent formatting
        return String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);
    }

    private void uploadEventData() {
        // Get values from UI components
        String title = edtTitle.getText().toString();
        String location = edtLocation.getText().toString();
        String eventDate = txtDate.getText().toString();
        String time = txtTime.getText().toString();
        String limit = edtLimit.getText().toString();
        String description = edtDescription.getText().toString();

        // Initialize Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("events");

        // Check if any field is empty or not valid
        if (title.isEmpty() || location.isEmpty() || eventDate.isEmpty() || time.isEmpty() || limit.isEmpty() || description.isEmpty()) {
            Toast.makeText(EventSetupPageActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        // Check if limit is valid integer using regex method
        if (!limit.matches("\\d+")) {
            Toast.makeText(EventSetupPageActivity.this, "Number of participants must be a positive integer!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a unique key for the event
        String eventId = databaseReference.push().getKey();

        // Get the admin's name (replace with the actual method to obtain admin's name)
        String adminName = getAdminName(); // Assuming you have a method to get admin's name

        String postDate = getFormattedPostDate();

        // Create an Event object with the input data
        Event event = new Event(eventId,title,location, eventDate, time, limit, adminName, description, postDate);

        // Upload the event to Firebase
        databaseReference.child(eventId).setValue(event);

        // Create the "Participants" branch for the event (an empty node)
        databaseReference.child(eventId).child("Participants").setValue("Null");

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
        SharedPreferences p = getSharedPreferences("myprefs",
                Context.MODE_PRIVATE);
        String userName = p.getString("userName", "default_value");
        return userName;
    }

    private void onBacktoEventPage() {
        // Handle the click event to navigate to the sign-up page
        Intent homePage = new Intent(this, AdminEventDisplayActivity.class);
        startActivity(homePage);
        finish();//finish current activity; (Sign in).
    }

    private String getFormattedPostDate() {
        // Get the current date and format it as "dd-MM-yyyy"
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("America/Toronto"));
        return simpleDateFormat.format(new Date());
    }
}