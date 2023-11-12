package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class LoginPage extends Fragment {

    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Find the EditText views
        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextPassword = view.findViewById(R.id.editTextPassword);

        // Find the Login button
        Button loginButton = view.findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginClick();
            }
        });


        // Find the "Sign up" TextView
        TextView signUpTextView = view.findViewById(R.id.textViewSignUp);
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignUpClick();
            }
        });
    }

    private void onLoginClick() {
        // Handle the login logic here
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        // Add your authentication logic here
        // For example, you can check the credentials against a database

        // If authentication is successful, you can navigate to another activity
        Intent homeIntent = new Intent(getActivity(), HomePage.class);
        startActivity(homeIntent);
    }

    private void onSignUpClick() {
        // Handle the click event to navigate to the sign-up page
        Intent signUpIntent = new Intent(getActivity(), SignUpPage.class);
        startActivity(signUpIntent);
    }
}
