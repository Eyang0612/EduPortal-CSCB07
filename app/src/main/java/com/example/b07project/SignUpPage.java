package com.example.b07project;


import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import com.example.b07project.User.Student;




public class SignUpPage extends AppCompatActivity {


    private EditText nameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    private TextView passwordRequirementsTextView, haveAccountTextView, loginTextView;
    private Button signUpButton;
    private Spinner spinnerRole;


    FirebaseDatabase db;
    DatabaseReference ref;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sign_up_page);


        // Initialize UI elements
        nameEditText = findViewById(R.id.editTextName);
        emailEditText = findViewById(R.id.editTextEmailSignUp);
        passwordEditText = findViewById(R.id.editTextPasswordSignUp);
        confirmPasswordEditText = findViewById(R.id.editTextConfirmPasswordSignUp);






        // Set onClickListener for the role choose spinner
        spinnerRole = findViewById(R.id.spinnerRole);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.roles_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRole.setAdapter(adapter);


       /*
       spinnerRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
               // posiiton means the choice of spinner, 0 is "choose your role"


               if(position != 0){
                   String selectedRole = parentView.getItemAtPosition(position).toString();
               }
           }


           @Override
           public void onNothingSelected(AdapterView<?> parentView) {
               // Do nothing here
           }
       });


        */

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
                    boolean isAdmin = (selectedRole.equals("Admin"));
                    if (!isAdmin) {
                        Student student = new Student(name, email, password, isAdmin);
                        saveToDataBase(student);
                        onBacktoLoginClick();
                    }
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


    private void onBacktoLoginClick() {
        // Handle the click event to navigate to the sign-up page
        Intent LoginIntent = new Intent(this, LoginPage.class);
        startActivity(LoginIntent);
    }


    private void saveToDataBase(Student student) {
        // code for save the account to database
        ref.child(student.getEmail()).setValue(student);
        showMessage("Registration successful!");
    }
    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
