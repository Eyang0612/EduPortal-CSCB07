package com.example.b07project.LoginMVP;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.firebase.database.DataSnapshot;


public class LoginPresenter{

    private LoginView mainView;

    private LoginModel model;

    public LoginPresenter(LoginView mainView, LoginModel model) {
        this.mainView = mainView;
        this.model = model;
    }

    public void onLoginClick(){
        //mAuth = FirebaseAuth.getInstance();
        String email = mainView.findEmailEditText();
        String password = mainView.findPasswordEditText();
        if (TextUtils.isEmpty(email)){
            mainView.displayText("Email cannot Be Empty!");

        }else if (TextUtils.isEmpty(password)){
            mainView.displayText("Password cannot Be Empty!");

        }else {
            model.checkLogin(email, password, this);
        }

    }

    public void onLogin(String uid){
        if (!(uid.equals(""))){
            mainView.displayText("Login Successful");
            model.fetchAndDisplayUserData(uid, this);
        }else{
            mainView.displayText("Incorrect Password Entered or Username Does Not Exist!");
        }

    }

    public SharedPreferences fetchContext(){
        return mainView.getCont();
    }
    public void checkRole(String userRole){
        if (userRole.equals("Student")){
            mainView.switchToStudentHomePage();
        }else {
            mainView.switchToAdminHomePage();
        }
    }

    public void checkUser(DataSnapshot userData,String uid){
        if(userData.exists()){
            model.redirectHomePage(userData, uid, this);
        }else{
            mainView.displayText("Userdata Not Found");
        }
    }
}




