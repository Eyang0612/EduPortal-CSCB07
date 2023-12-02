package com.example.b07project.Login;

import android.content.Context;
import android.content.SharedPreferences;

public interface Contract {
    interface View{
        void switchToSignup();
        void switchToStudentHomePage();
        void switchToAdminHomePage();
        void showErrorText(String err);

        void printLoginSuccessful();

        void printLoginFailed();

        String findEmailEditText();
        String findPasswordEditText();
        SharedPreferences getCont();

    }
    interface Model{
        void checkLogin(String email, String password);
        void fetchAndDisplayUserData(String uid);
    }
    interface Presenter{
        void onSignUpClick();
        void onLoginClick();
        void onLoginSuccess();
        void onLoginFailed();

        SharedPreferences fetchContext();

        void signalSwitchToStudent();
        void signalSwitchToAdmin();

    }
}
