package com.example.b07project;


import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;


import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b07project.Login.LoginPage;
import com.example.b07project.User.Admin;
import com.example.b07project.User.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.example.b07project.User.Student;
import com.google.firebase.database.ValueEventListener;

/*
public class SignUpPage extends AppCompatActivity {


    private EditText nameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    private TextView passwordRequirementsTextView, haveAccountTextView, loginTextView;
    private Button signUpButton;
    private Spinner spinnerRole;

    private boolean isFound;


    private FirebaseDatabase db;
    private DatabaseReference ref;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sign_up_page);


        // Initialize UI elements
        nameEditText = findViewById(R.id.editTextName);
        emailEditText = findViewById(R.id.editTextEmailSignUp);
        passwordEditText = findViewById(R.id.editTextPasswordSignUp);
        confirmPasswordEditText = findViewById(R.id.editTextConfirmPasswordSignUp);

        // Initialize Firebase Authenticator
        mAuth = FirebaseAuth.getInstance();


        // Set onClickListener for the role choose spinner
        spinnerRole = findViewById(R.id.spinnerRole);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.roles_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRole.setAdapter(adapter);


        // Set onClickListener for the SignUp button
        signUpButton = findViewById(R.id.buttonSignUp);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String confirmPass = confirmPasswordEditText.getText().toString();
                String selectedRole = spinnerRole.getSelectedItem().toString();
                String password = passwordEditText.getText().toString();

                db = FirebaseDatabase.getInstance();
                ref = db.getReference("users");

                if (validateInputs(name, email, confirmPass, selectedRole, password)) {

                        saveToAuth(name, email, password, selectedRole);

                }
            }



            private boolean validateInputs(String name, String email, String confirmPass, String selectedRole, String password) {
                if (spinnerRole.getSelectedItemPosition() == 0) {
                    showMessage("Fails: choose a valid role");
                    return false;
                }

                if (!checkNoneEmptyField(name, email)) {
                    showMessage("Fails: do not leave empty fields");
                    return false;
                }

                if (!isValidEmail(email)) {
                    showMessage("Fails: please enter valid email");
                    return false;
                }

                if (!confirmPass.equals(password)) {
                    showMessage("Fails: password does not match");
                    return false;
                }

                if (!isValidPassword(password)) {
                    passwordEditText.setError("Invalid password");
                    return false;
                }
                checkIfEmailExists(email);
                if(isFound){
                    showMessage("Invalid: email already exist");
                    return false;
                }

                return true;
            }
        });


        // Set onClickListener for the Login TextView
        loginTextView = findViewById(R.id.textViewLoginFromSignUp);
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add your login logic here
                // For example, you can navigate to the login page
                onBacktoLoginClick();
            }
        });
    }

    // Add username and password to Firebase Authentication
    public void saveToAuth(String name, String email, String password, String role){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user= mAuth.getCurrentUser();
                            String userId = user.getUid();
                            if(role.equals("Student")) {
                                Student student = new Student(name, email, password, userId, role);
                                saveToDataBase(student);
                            }else{
                                Admin admin = new Admin(name, email, password, userId, role);
                                saveToDataBase(admin);
                            }

                            onBacktoLoginClick();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignUpPage.this, "Authentication Failed!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    // password validation function
    private boolean isValidPassword(String password) {
        // Add your password validation logic here
        // For example, check if it meets the specified requirements
        return password.length() >= 8 && containsNumber(password) && containsUppercase(password);
    }


    // Example helper functions for password validation
    private boolean containsNumber(String str) {
        return str.matches(".*\\d.*");
    }
    private boolean checkNoneEmptyField(String name, String email){
        return (name.length() >0 && email.length() >0);
    }


    private boolean containsUppercase(String str) {
        return !str.equals(str.toLowerCase());
    }




    public boolean isValidEmail(String email){
        // Check for null or empty string
        if (email == null || email.isEmpty()) {
            return false;
        }


        // Check for "@" character
        int atIndex = email.indexOf('@');
        if (atIndex == -1) {
            return false;
        }


        // Check for at least one character before "@"
        if (atIndex == 0) {
            return false;
        }


        // Check for at least one character after "@"
        if (atIndex == email.length() - 1) {
            return false;
        }




        // Check for "." character after "@"
        int dotIndex = email.indexOf('.', atIndex);
        return !(dotIndex == -1 || dotIndex == atIndex + 1 || dotIndex == email.length() - 1);




    }

    public void checkIfEmailExists(String emailToCheck) {
        Query emailQuery = ref.orderByChild("email").equalTo(emailToCheck);

        emailQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    isFound = true;
                    // Email exists in the database
                    // Handle the logic here (e.g., display a message, perform an action)
                } else {
                    isFound = false;
                    // Email does not exist in the database
                    // Handle the logic here (e.g., display a message, perform an action)
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }
        });
    }


    private void onBacktoLoginClick() {
        // Handle the click event to navigate to the sign-up page
        Intent LoginIntent = new Intent(this, LoginPage.class);
        startActivity(LoginIntent);
        finish();//finish current activity; (Sign in).
    }


    private void saveToDataBase(Student student) {
        // code for save the account to database
        String userId = student.getUserId();
        ref.child(userId).setValue(student);
        showMessage("Registration successful!");
    }

    private void saveToDataBase(Admin admin) {
        // code for save the account to database
        String userId = admin.getUserId();
        ref.child(userId).setValue(admin);
        showMessage("Registration successful!");
    }
    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

 */

public class SignUpPage extends AppCompatActivity {

    private EditText nameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    private TextView passwordRequirementsTextView, haveAccountTextView, loginTextView;
    private Button signUpButton;
    private Spinner spinnerRole;
    private boolean isFound;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sign_up_page);

        initializeUI();
        setOnClickListeners();
    }

    private void initializeUI() {
        nameEditText = findViewById(R.id.editTextName);
        emailEditText = findViewById(R.id.editTextEmailSignUp);
        passwordEditText = findViewById(R.id.editTextPasswordSignUp);
        confirmPasswordEditText = findViewById(R.id.editTextConfirmPasswordSignUp);

        spinnerRole = findViewById(R.id.spinnerRole);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.roles_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRole.setAdapter(adapter);

        signUpButton = findViewById(R.id.buttonSignUp);
        loginTextView = findViewById(R.id.textViewLoginFromSignUp);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");
    }

    private void setOnClickListeners() {
        spinnerRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String confirmPass = confirmPasswordEditText.getText().toString();
                String selectedRole = spinnerRole.getSelectedItem().toString();
                String password = passwordEditText.getText().toString();

                if (validateInputs(name, email, confirmPass, selectedRole, password)) {
                    saveToAuth(name, email, password, selectedRole);
                }
            }
        });

        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBacktoLoginClick();
            }
        });
    }

    private boolean validateInputs(String name, String email, String confirmPass, String selectedRole, String password) {
        if (spinnerRole.getSelectedItemPosition() == 0) {
            showMessage("Fails: choose a valid role");
            return false;
        }

        if (!checkNoneEmptyField(name, email)) {
            showMessage("Fails: do not leave empty fields");
            return false;
        }

        if (!isValidEmail(email)) {
            showMessage("Fails: please enter a valid email");
            return false;
        }

        if (!confirmPass.equals(password)) {
            showMessage("Fails: password does not match");
            return false;
        }

        if (!isValidPassword(password)) {
            passwordEditText.setError("Invalid password");
            return false;
        }

        checkIfEmailExists(email);
        if (isFound) {
            showMessage("Invalid: email already exists");
            return false;
        }

        return true;
    }

    private void saveToAuth(String name, String email, String password, String role) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            if (user != null) {
                                String userId = user.getUid();
                                saveToDatabase(name, email, password, role, userId);
                                onBacktoLoginClick();
                            }
                        } else {
                            showMessage("This email has been used for another account");
                        }
                    }
                });
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8 && containsNumber(password) && containsUppercase(password);
    }

    private boolean checkNoneEmptyField(String name, String email) {
        return !name.isEmpty() && !email.isEmpty();
    }

    private boolean containsNumber(String str) {
        return str.matches(".*\\d.*");
    }

    private boolean containsUppercase(String str) {
        return !str.equals(str.toLowerCase());
    }

    private boolean isValidEmail(String email) {
        return email != null && !email.isEmpty() && email.matches(".+@.+\\..+");
    }

    private void checkIfEmailExists(String emailToCheck) {
        databaseReference.orderByChild("email").equalTo(emailToCheck).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                isFound = dataSnapshot.exists();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }
        });
    }

    private void saveToDatabase(String name, String email, String password, String role, String userId) {
        User user = (role.equals("Student")) ? new Student(name, email, password, userId, role) :
                new Admin(name, email, password, userId, role);

        databaseReference.child(userId).setValue(user);
        showMessage("Registration successful!");
    }

    private void onBacktoLoginClick() {
        Intent loginIntent = new Intent(this, LoginPage.class);
        startActivity(loginIntent);
        finish();
    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}