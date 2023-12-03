package com.example.b07project.LoginMVP;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.b07project.R;
import com.example.b07project.SignUpPage;
import com.example.b07project.adminPages.adminHomePage;

import com.example.b07project.studentPages.studentHomePage;

public class LoginView extends AppCompatActivity{

    private EditText editTextEmail, editTextPassword;
    LoginPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login_page);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        presenter = new LoginPresenter(this, new LoginModel());

        Button loginButton = findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLoginClick();
            }
        });

        TextView signUpTextView = findViewById(R.id.textViewSignUp);
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSignup();
            }
        });
    }

    public void switchToSignup(){
        // Handle the click event to navigate to the sign-up page
        Intent signUpIntent = new Intent(this, SignUpPage.class);
        startActivity(signUpIntent);
        finish();
    }

    public void switchToStudentHomePage(){
        // Handle the click event to navigate to the sign-up page
        Intent signUpIntent = new Intent(this, studentHomePage.class);
        startActivity(signUpIntent);
        finish();
    }

    public void switchToAdminHomePage(){
        // Handle the click event to navigate to the sign-up page
        Intent signUpIntent = new Intent(this, adminHomePage.class);
        startActivity(signUpIntent);
        finish();
    }

    public void displayText(String message){
        Toast.makeText(LoginView.this, message, Toast.LENGTH_SHORT).show();
    }

    public String findEmailEditText(){
        return editTextEmail.getText().toString().trim();
    }

    public String findPasswordEditText(){
        return editTextPassword.getText().toString().trim();
    }

    public SharedPreferences getCont(){
        return getSharedPreferences("myprefs", Context.MODE_PRIVATE);
    }


}
