package com.example.b07project;

import android.content.Intent;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.b07project.studentPages.QuestionAnswer.EvaluateAnswer;



public class QualificationPage2 extends AppCompatActivity implements View.OnClickListener {
    TextView questionTextView;
    Button submit;
    Button ansA, ansB;

    EvaluateAnswer Ans;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_qualification_page);
        Intent intent = getIntent();
        Ans = intent.getParcelableExtra("evaluateAnswer");

        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        submit = findViewById(R.id.submit);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        submit.setOnClickListener(this);

        loadQuestion2();

    }

    @Override
    public void onClick(View v) {

        ansA.setBackgroundColor(Color.GRAY);
        ansB.setBackgroundColor(Color.GRAY);
        Button clickedChoice = (Button) (v);

        if(v.getId()==R.id.submit) {
            if (Ans.getQuestion2() == -1) {
                return;
            }
            loadQuestion3();
        }
        else{
            if (v.getId()==R.id.ans_A){
                Ans.setQuestion2(1);
            }
            else if (v.getId()==R.id.ans_B){
                Ans.setQuestion2(0);
            }
        }

    }

    void loadQuestion2(){
        questionTextView.setText(QuestionAnswer.QA.Questions[1]);
        ansA.setText(QuestionAnswer.QA.Choices[1][0]);
        ansB.setText(QuestionAnswer.QA.Choices[1][1]);
    }

    void loadQuestion3(){
        Intent intent = new Intent(this, com.example.b07project.QualificationPage3.class);
        intent.putExtra("evaluateAnswer", Ans);
        startActivity(intent);
    }

}
