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
    }

    @Override
    public void onClick(View v) {

        ansA.setBackgroundColor(Color.GRAY);
        ansB.setBackgroundColor(Color.GRAY);
        ansC.setBackgroundColor(Color.GRAY);
        ansD.setBackgroundColor(Color.GRAY);
        Button clickedChoice = (Button) (v);

        if(clickedChoice.getId()==R.id.submit) {
            if (Ans.getQuestion1() == 0) {
                return;
            }
            loadQuestion2();
        }
        else{
            if (clickedChoice.getId()==R.id.ans_A){
                Ans.setQuestion1(1);
            }
            else if (clickedChoice.getId()==R.id.ans_B){
                Ans.setQuestion1(2);
            }
            else if (clickedChoice.getId()==R.id.ans_C){
                Ans.setQuestion1(3);
            }
            else if (clickedChoice.getId()==R.id.ans_D){
                Ans.setQuestion1(4);
            }
        }


    }

    void loadQuestion2(){
        Intent intent = new Intent(this, QualificationPage2.class);
        intent.putExtra("evaluateAnswer", Ans);
        startActivity(intent);
    }
}
