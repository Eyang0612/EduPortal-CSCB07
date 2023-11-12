package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login_page);

        // Find the EditText view
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        // Find the Login button
        Button loginButton = findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              onLoginClick();
            }
        });

        // Find the "Sign up" TextView
        TextView signUpTextView = findViewById(R.id.textViewSignUp);
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignUpClick();
            }
        });

        TextView BackTextView = findViewById(R.id.textViewBack);
        BackTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackClick();
            }
        });
    }

    private void onLoginClick() {
        // Handle the login logic here
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        // Add your authentication logic here
        // For example, you can check the credentials against a database
        if (isValid(password)) {
            // Password is valid, proceed to home page
            Intent homeIntent = new Intent(this, HomePage.class);
            startActivity(homeIntent);
        } else {
            // Display an error message or handle invalid password
            showToast("Error: Wrong email or password, please try again.");
        }
    }

    private void onSignUpClick() {
        // Handle the click event to navigate to the sign-up page
        Intent signUpIntent = new Intent(this, SignUpPage.class);
        startActivity(signUpIntent);
    }

    private void onBackClick() {
        // Handle the click event to navigate to the sign-up page
        Intent BackIntent = new Intent(this, MainActivity.class);
        startActivity(BackIntent);
    }
    private boolean isValid(String password) {
        // Add your password validation logic here
        // check if email and correspond password is in database
        return true;
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
