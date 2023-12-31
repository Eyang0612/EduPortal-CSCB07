package com.example.b07project.LoginMVP;

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

public class LoginModel{
    FirebaseAuth mAuth;

    public void checkLogin(String email, String password, LoginPresenter mainPresenter) {
        mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // If authentication is successful, you can navigate to another activity
                            FirebaseUser user = mAuth.getCurrentUser();
                            mainPresenter.onLogin(user.getUid());

                        } else {
                            // If sign in fails, display a message to the user.
                            mainPresenter.onLogin("");
                        }
                    }
                });
    }

    public void fetchAndDisplayUserData(String uid, LoginPresenter mainPresenter) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(uid);

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mainPresenter.checkUser(dataSnapshot, uid);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void redirectHomePage(DataSnapshot userData, String uid, LoginPresenter mainPresenter){
        String userName = userData.child("name").getValue(String.class);
        String userEmail = userData.child("email").getValue(String.class);
        String userRole = userData.child("role").getValue(String.class);
        SharedPreferences p = mainPresenter.fetchContext();
        SharedPreferences.Editor editor = p.edit();
        editor.putString("email", userEmail);
        editor.putString("userName", userName);
        editor.putString("userRole", userRole);
        editor.putString("userId", uid);
        editor.apply();
        mainPresenter.checkRole(userRole);
    }
}
