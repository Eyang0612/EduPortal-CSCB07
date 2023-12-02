package com.example.b07project.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.example.b07project.R;
import com.example.b07project.SignUpPage;
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


public class LoginPage extends AppCompatActivity{

    private EditText editTextEmail, editTextPassword;
    LoginPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login_page);

        // Find the EditText view
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        presenter = new LoginPresenter(this, new LoginModel());

        // Find the Login button
        Button loginButton = findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLoginClick();
            }
        });

        // Find the "Sign up" TextView
        TextView signUpTextView = findViewById(R.id.textViewSignUp);
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSignup();
            }
        });
    }

    //@Override
    public void switchToSignup(){
        // Handle the click event to navigate to the sign-up page
        Intent signUpIntent = new Intent(this, SignUpPage.class);
        startActivity(signUpIntent);
        finish();
    }
    //@Override
    public void switchToStudentHomePage(){
        // Handle the click event to navigate to the sign-up page
        Intent signUpIntent = new Intent(this, studentHomePage.class);
        startActivity(signUpIntent);
        finish();
    }
    //@Override
    public void switchToAdminHomePage(){
        // Handle the click event to navigate to the sign-up page
        Intent signUpIntent = new Intent(this, adminHomePage.class);
        startActivity(signUpIntent);
        finish();
    }
    //@Override
    public void displayText(String message){
        Toast.makeText(LoginPage.this, message, Toast.LENGTH_SHORT).show();
    }
    //@Override
    public String findEmailEditText(){
        return editTextEmail.getText().toString().trim();
    }
    //@Override
    public String findPasswordEditText(){
        return editTextPassword.getText().toString().trim();
    }
    //@Override
    public SharedPreferences getCont(){
        return getSharedPreferences("myprefs", Context.MODE_PRIVATE);
    }


}
