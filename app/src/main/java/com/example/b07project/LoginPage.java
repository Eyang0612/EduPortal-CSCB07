package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;

    //initialze firebase
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login_page);

        // Find the EditText view
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        // Find the Login button
        Button loginButton = findViewById(R.id.buttonLogin);

        // ...
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
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

<<<<<<< HEAD
=======
        TextView BackTextView = findViewById(R.id.textViewBack);
        BackTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackClick();
            }
        });
>>>>>>> f9c74c8b99295f860a79b5469c548e32f65c2c04
    }

    private void onLoginClick() {
        // Handle the login logic here
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        // Add your authentication logic here
        // For example, you can check the credentials against a database

        //check if username and password are empty
        if (TextUtils.isEmpty(email)){
            Toast.makeText(LoginPage.this, "Enter Email!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(LoginPage.this, "Enter Password!", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // If authentication is successful, you can navigate to another activity
                            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent homeIntent = new Intent(getApplicationContext(), HomePage.class);
                            startActivity(homeIntent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Incorrect Password Entered or Username Does Not Exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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
}
