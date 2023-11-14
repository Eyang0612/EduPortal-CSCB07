package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;

import com.example.b07project.studentPages.StudentHomePage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


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
    }

    private void onLoginClick() {
        // Handle the login logic here
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        // Add your authentication logic here
        // For example, you can check the credentials against a database
        isValid(email, password);
        //else {
        //    // Display an error message or handle invalid password
        //    showToast("Error: Wrong email or password, please try again.");
        //}
    }

    private void onSignUpClick() {
        // Handle the click event to navigate to the sign-up page
        Intent signUpIntent = new Intent(this, SignUpPage.class);
        startActivity(signUpIntent);
    }
    private void isValid(String email, String password) {
        // Add your password validation logic here
        // check if email and correspond password is in database
        if (TextUtils.isEmpty(email)){
            Toast.makeText(LoginPage.this, "Email Cannot Be Empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(LoginPage.this, "Password Cannot Be Empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("studentUsers");
        Query checkUserDB = ref.orderByChild("email").equalTo(email);
        checkUserDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    editTextEmail.setError(null);
                    String passwordFromDB = snapshot.child(email).child("password").getValue(String.class);
                    if (passwordFromDB.equals(password)){
                        editTextEmail.setError(null);
                        Intent homeIntent = new Intent(LoginPage.this, StudentHomePage.class);
                        startActivity(homeIntent);
                    }else{
                        editTextPassword.setError("Invalid Password!");
                        editTextPassword.requestFocus();
                        Toast.makeText(LoginPage.this, "Invalid Password!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }else{
                    editTextEmail.setError("Username Does Not Exist!");
                    editTextPassword.requestFocus();
                    Toast.makeText(LoginPage.this, "Username Does Not Exist!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Toast.makeText(LoginPage.this, "Test", Toast.LENGTH_SHORT).show();
        return;
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
