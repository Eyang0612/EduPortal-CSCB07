package com.example.b07project.Login;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginModel implements Contract.Model{
    private Contract.View mainView;
    FirebaseAuth mAuth;

    public LoginModel(Contract.View mainView){
        this.mainView=mainView;
    }
    public void checkLogin(String email, String password) {
        mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // If authentication is successful, you can navigate to another activity
                            mainView.printLoginSuccessful();
                            FirebaseUser user = mAuth.getCurrentUser();
                            fetchAndDisplayUserData(user.getUid());

                        } else {
                            // If sign in fails, display a message to the user.
                            //maybe put this to loginpresenter for better MVP logic
                            mainView.printLoginFailed();
                        }
                    }
                });
    }

    public void fetchAndDisplayUserData(String uid) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(uid);

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String userName = dataSnapshot.child("name").getValue(String.class);
                    String userEmail = dataSnapshot.child("email").getValue(String.class);
                    String userRole = dataSnapshot.child("role").getValue(String.class);

                    redirectHomePage(userEmail, userName, userRole, uid);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void redirectHomePage(String userEmail, String userName, String userRole, String uid){
        SharedPreferences p = mainView.getCont();
        SharedPreferences.Editor editor = p.edit();
        editor.putString("email", userEmail);
        editor.putString("userName", userName);
        editor.putString("userRole", userRole);
        editor.putString("userId", uid);
        editor.apply();
        if(userRole.equals("Student")){
            mainView.switchToStudentHomePage();
        }else {
            mainView.switchToAdminHomePage();
        }
    }
}
