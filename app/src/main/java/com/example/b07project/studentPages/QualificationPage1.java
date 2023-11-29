package com.example.b07project.studentPages;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.b07project.studentPages.QuestionAnswer.EvaluateAnswer;


import com.example.b07project.R;



public class QualificationPage1 extends AppCompatActivity implements View.OnClickListener{
    private TextView questionTextView;
    private Button ansA, ansB, ansC, ansD;
    private Button submit, back;

    private EvaluateAnswer Ans;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_qualification_page3);


        Intent intent = getIntent();
        Ans = intent.getSerializableExtra("evaluateAnswer",EvaluateAnswer.class);
        if (Ans == null) {
            Ans = new EvaluateAnswer();
        }

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

        back = findViewById(R.id.back);
        back.setOnClickListener(this);


        if (Ans.getQuestion1()==1){
            ansA.setBackgroundColor(Color.GRAY);
        }
        else if (Ans.getQuestion1()==2){
            ansB.setBackgroundColor(Color.GRAY);
        }
        else if (Ans.getQuestion1()==3){
            ansC.setBackgroundColor(Color.GRAY);
        }
        else if (Ans.getQuestion1()==4){
            ansD.setBackgroundColor(Color.GRAY);
        }
    }

    @Override
    public void onClick(View v) {


        Button clickedChoice = (Button) (v);

        if(clickedChoice.getId()==R.id.submit) {
            if (Ans.getQuestion1() == 0) {
                return;
            }
            loadQuestion2();
        } else if (clickedChoice.getId()==R.id.back) {
            Intent intent = new Intent(QualificationPage1.this, studentHomePage.class);
            startActivity(intent);

        } else{
            if (clickedChoice.getId()==R.id.ans_A){
                ansA.setBackgroundColor(Color.GRAY);
                ansB.setBackgroundColor(Color.parseColor("#6750A4"));
                ansC.setBackgroundColor(Color.parseColor("#6750A4"));
                ansD.setBackgroundColor(Color.parseColor("#6750A4"));
                Ans.setQuestion1(1);
                Ans.setQuestion3(null);
            }
            else if (clickedChoice.getId()==R.id.ans_B){
                ansB.setBackgroundColor(Color.GRAY);
                ansA.setBackgroundColor(Color.parseColor("#6750A4"));
                ansC.setBackgroundColor(Color.parseColor("#6750A4"));
                ansD.setBackgroundColor(Color.parseColor("#6750A4"));
                Ans.setQuestion1(2);
                Ans.setQuestion3(null);
            }
            else if (clickedChoice.getId()==R.id.ans_C){
                ansC.setBackgroundColor(Color.GRAY);
                ansB.setBackgroundColor(Color.parseColor("#6750A4"));
                ansA.setBackgroundColor(Color.parseColor("#6750A4"));
                ansD.setBackgroundColor(Color.parseColor("#6750A4"));
                Ans.setQuestion1(3);
                Ans.setQuestion3(null);
            }
            else if (clickedChoice.getId()==R.id.ans_D){
                ansD.setBackgroundColor(Color.GRAY);
                ansB.setBackgroundColor(Color.parseColor("#6750A4"));
                ansA.setBackgroundColor(Color.parseColor("#6750A4"));
                ansC.setBackgroundColor(Color.parseColor("#6750A4"));
                Ans.setQuestion1(4);
                Ans.setQuestion3(null);
            }
        }


    }

    void loadQuestion2(){
        Intent intent = new Intent(QualificationPage1.this, QualificationPage2.class);
        intent.putExtra("evaluateAnswer", Ans);
        startActivity(intent);
    }
}
