package com.example.b07project.studentPages;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.b07project.studentPages.QualificationPage2;
import com.example.b07project.studentPages.QuestionAnswer.EvaluateAnswer;


import com.example.b07project.R;

import com.example.b07project.studentPages.QuestionAnswer.QA;


public class QualificationPage1 extends AppCompatActivity implements View.OnClickListener{
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Button submit;


    private EvaluateAnswer Ans = new EvaluateAnswer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_qualification_page3);

        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submit = findViewById(R.id.submit);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submit.setOnClickListener(this);

        loadQuestion1();
    }

    @Override
    public void onClick(View v) {

        ansA.setBackgroundColor(Color.GRAY);
        ansB.setBackgroundColor(Color.GRAY);
        ansC.setBackgroundColor(Color.GRAY);
        ansD.setBackgroundColor(Color.GRAY);
        Button clickedChoice = (Button) (v);

        if(v.getId()==R.id.submit) {
            if (Ans.getQuestion1() == 0) {
                return;
            }
            loadQuestion2();
        }
        else{
            if (v.getId()==R.id.ans_A){
                Ans.setQuestion1(1);
            }
            else if (v.getId()==R.id.ans_B){
                Ans.setQuestion1(2);
            }
            else if (v.getId()==R.id.ans_C){
                Ans.setQuestion1(3);
            }
            else if (v.getId()==R.id.ans_D){
                Ans.setQuestion1(4);
            }
        }


    }

    void loadQuestion1(){
        questionTextView.setText(QA.Questions[0]);
        ansA.setText(QA.Choices[0][0]);
        ansB.setText(QA.Choices[0][1]);
        ansC.setText(QA.Choices[0][2]);
        ansD.setText(QA.Choices[0][3]);
    }

    void loadQuestion2(){
        Intent intent = new Intent(this, QualificationPage2.class);
        intent.putExtra("evaluateAnswer", Ans);
        startActivity(intent);
    }
}
