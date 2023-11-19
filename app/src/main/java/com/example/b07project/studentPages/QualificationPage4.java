package com.example.b07project.studentPages;

import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.b07project.R;
import com.example.b07project.studentPages.QuestionAnswer.EvaluateAnswer;



public class QualificationPage4 extends AppCompatActivity {
    TextView Result, Message;
    EvaluateAnswer Ans;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_qualification_page4);
        Intent intent = getIntent();
        Ans = intent.getParcelableExtra("evaluateAnswer");

        Result = findViewById(R.id.result);
        Message = findViewById(R.id.message);


        /*
        questionTextView = findViewById(R.id.question);
        course1 = findViewById(R.id.course1);
        course2 = findViewById(R.id.course2);
        course3 = findViewById(R.id.course3);
        course4 = findViewById(R.id.course4);
        course5 = findViewById(R.id.course5);
        course6 = findViewById(R.id.course6);

        grade1 = findViewById((R.id.text_1));
        grade2 = findViewById(R.id.text_2);
        grade3 = findViewById((R.id.text_3));
        grade4 = findViewById(R.id.text_4);
        grade5 = findViewById(R.id.text_5);
        grade6 =findViewById(R.id.text_6);


        submit = findViewById(R.id.submit);

        submit.setOnClickListener(this);
*/
        loadResult();

    }
/*
    @Override
    public void onClick(View v) {

        Button clickedChoice = (Button) (v);

        if(v.getId()==R.id.submit) {

            String[] grade_list = new String[6];

            grade_list[0] = grade1.getText().toString();
            grade_list[1] = grade2.getText().toString();
            grade_list[2] = grade3.getText().toString();
            grade_list[3] = grade4.getText().toString();
            grade_list[4] = grade5.getText().toString();
            grade_list[5] = grade6.getText().toString();
            for (int i=0; i<6; i++){
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

            LoadNewPage();


        }
    }
*/
    void loadResult(){
        String[] Evaluate_result= Ans.Qualify();
        Result.setText(Evaluate_result[0]);
        Message.setText(Evaluate_result[1]);
    }





}
