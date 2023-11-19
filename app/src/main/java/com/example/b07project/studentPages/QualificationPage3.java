package com.example.b07project;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.b07project.studentPages.QuestionAnswer.EvaluateAnswer;


public class QualificationPage3 extends AppCompatActivity implements View.OnClickListener {
    TextView questionTextView, course1, course2, course3, course4, course5, course6;
    Button submit;
    EditText grade1, grade2, grade3, grade4, grade5;
    EvaluateAnswer Ans;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_qualification_page2);
        Intent intent = getIntent();
        Ans = intent.getParcelableExtra("evaluateAnswer");

        questionTextView = findViewById(R.id.question);
        grade1 = findViewById((R.id.text_1));
        grade2 = findViewById(R.id.text_2);
        grade3 = findViewById((R.id.text_3));
        grade4 = findViewById(R.id.text_4);
        grade5 = findViewById(R.id.text_5);

        submit = findViewById(R.id.submit);

        submit.setOnClickListener(this);

        loadQuestion2();

    }

    @Override
    public void onClick(View v) {

        Button clickedChoice = (Button) (v);

        if(v.getId()==R.id.submit) {

            String[] grade_list = new String[5];

            grade_list[0] = grade1.getText().toString();
            grade_list[1] = grade2.getText().toString();
            grade_list[2] = grade3.getText().toString();
            grade_list[3] = grade4.getText().toString();
            grade_list[4] = grade5.getText().toString();
            for (int i=0; i<5; i++){
                if (TextUtils.isEmpty(grade_list[i])){
                    Ans.setQuestion3_index(0.0, i);
                }
                else{
                    try{
                        double actualgrade = Double.parseDouble(grade_list[i]);
                        Ans.setQuestion3_index(actualgrade, i);
                    }
                    catch (NumberFormatException e){
                        String errorMessage= "Invalid Number Format";
                        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    void loadQuestion2(){
        questionTextView.setText(QuestionAnswer.QA.Questions[2]);

    }



}
