package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;

import com.example.b07project.adminPages.EventSetupPageActivity;
import com.example.b07project.adminPages.adminHomePage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.example.b07project.studentPages.studentHomePage;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginPage extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private FirebaseAuth mAuth;
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


    private void onSignUpClick() {
        // Handle the click event to navigate to the sign-up page
        Intent signUpIntent = new Intent(this, EventSetupPageActivity.class);
        startActivity(signUpIntent);
    }
    private void onLoginClick() {
        mAuth = FirebaseAuth.getInstance();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
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
                            FirebaseUser user = mAuth.getCurrentUser();
                            fetchAndDisplayUserData(user.getUid());
                            //Intent homeIntent = new Intent(getApplicationContext(), StudentHomePage.class);
                            //startActivity(homeIntent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Incorrect Password Entered or Username Does Not Exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void fetchAndDisplayUserData(String uid) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(uid);

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String userName = dataSnapshot.child("name").getValue(String.class);
                    String userEmail = dataSnapshot.child("email").getValue(String.class);
                    String userRole = dataSnapshot.child("role").getValue(String.class);
                    Log.d("LoginPage", "User data retrieved successfully. Name: " + userName + ", Email: " + userEmail);
                    redirectToHomepage(userEmail, userName, userRole, uid);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }

    private void redirectToHomepage(String userEmail, String userName,String userRole, String uid) {
        // Intent to start the homepage activity
        Intent intent;
        if(userRole.equals("Student")){
            intent = new Intent(this, studentHomePage.class);
        }else{
            intent = new Intent(this, adminHomePage.class);
        }
        intent.putExtra("email", userEmail);
        intent.putExtra("userName", userName);
        intent.putExtra("userRole", userRole);
        intent.putExtra("userId", uid);
        startActivity(intent);
        finish(); // finish the current activity (login)
    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
