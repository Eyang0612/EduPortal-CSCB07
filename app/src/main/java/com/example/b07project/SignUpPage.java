package com.example.b07project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SignUpPage extends AppCompatActivity {

    private EditText firstNameEditText, lastNameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    private TextView passwordRequirementsTextView, haveAccountTextView, loginTextView;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sign_up_page);

        // Initialize UI elements
        firstNameEditText = findViewById(R.id.editTextFirstName);
        lastNameEditText = findViewById(R.id.editTextLastName);
        emailEditText = findViewById(R.id.editTextEmailSignUp);
        passwordEditText = findViewById(R.id.editTextPasswordSignUp);
        confirmPasswordEditText = findViewById(R.id.editTextConfirmPasswordSignUp);




        // Set onClickListener for the SignUp button
        signUpButton = findViewById(R.id.buttonSignUp);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add your signup logic here
                // You can retrieve user input using getText().toString() and perform validation

                // For example, you can check if the password meets the requirements
                String password = passwordEditText.getText().toString();
                if (isValidPassword(password)) {
                    // Password is valid, proceed with signup
                    saveToDataBase();
                    onBacktoLoginClick();
                } else {
                    // Display an error message or handle invalid password
                    passwordEditText.setError("Invalid password");
                }
            }
        });

        // Set onClickListener for the Login TextView
        loginTextView = findViewById(R.id.textViewLoginFromSignUp);
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add your login logic here
                // For example, you can navigate to the login page
                onBacktoLoginClick();
            }
        });
    }

    // password validation function
    private boolean isValidPassword(String password) {
        // Add your password validation logic here
        // For example, check if it meets the specified requirements
        return password.length() >= 8 && containsNumber(password) && containsUppercase(password);
    }

    // Example helper functions for password validation
    private boolean containsNumber(String str) {
        return str.matches(".*\\d.*");
    }

    private boolean containsUppercase(String str) {
        return !str.equals(str.toLowerCase());
    }

    private void onBacktoLoginClick() {
        // Handle the click event to navigate to the sign-up page
        Intent LoginIntent = new Intent(this, LoginPage.class);
        startActivity(LoginIntent);
    }

    private void saveToDataBase() {
        // code for save the account to database
        showSuccessMessage();
    }
    private void showSuccessMessage() {
        Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();
    }
}
