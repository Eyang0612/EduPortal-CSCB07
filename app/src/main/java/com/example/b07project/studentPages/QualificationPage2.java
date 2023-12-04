package com.example.b07project.studentPages;

import android.content.Intent;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.b07project.R;
import com.example.b07project.studentPages.QuestionAnswer.EvaluateAnswer;

public class QualificationPage2 extends AppCompatActivity implements View.OnClickListener {
    private TextView questionTextView;
    private Button submit, previous, back;
    private Button ansA, ansB;

    EvaluateAnswer Ans;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_qualification_page);
        Intent intent = getIntent();
        Ans = intent.getSerializableExtra("evaluateAnswer",EvaluateAnswer.class);
        if (Ans == null) {
            Ans = new EvaluateAnswer();
        }

        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        submit = findViewById(R.id.submit);
        previous = findViewById(R.id.previous);


        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        submit.setOnClickListener(this);
        previous.setOnClickListener(this);

        back = findViewById(R.id.back);
        back.setOnClickListener(this);

        if (Ans.getQuestion2()==1){
            ansA.setBackgroundColor(Color.GRAY);
        }
        else if (Ans.getQuestion2()==0){
            ansB.setBackgroundColor(Color.GRAY);
        }


    }

    @Override
    public void onClick(View v) {

        Button clickedChoice = (Button) (v);

        if(clickedChoice.getId()==R.id.submit) {
            if (Ans.getQuestion2() == -1) {
                Toast toast = Toast.makeText(QualificationPage2.this, "Please choose an answer!", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
            loadQuestion3();
        }
        else if (clickedChoice.getId()==R.id.back) {
            Intent intent = new Intent(QualificationPage2.this, studentHomePage.class);
            startActivity(intent);

        } else{
            if (clickedChoice.getId()==R.id.ans_A){
                ansA.setBackgroundColor(Color.GRAY);
                ansB.setBackgroundColor(Color.parseColor("#6750A4"));
                Ans.setQuestion2(1);
            }
            else if (clickedChoice.getId()==R.id.ans_B){
                ansB.setBackgroundColor(Color.GRAY);
                ansA.setBackgroundColor(Color.parseColor("#6750A4"));
                Ans.setQuestion2(0);
            }
            else if (clickedChoice.getId()==R.id.previous){
                Intent intent = new Intent(QualificationPage2.this, QualificationPage1.class);
                intent.putExtra("evaluateAnswer", Ans);
                startActivity(intent);
            }
        }

    }

    void loadQuestion3(){
        Intent intent = new Intent(QualificationPage2.this, QualificationPage3.class);
        intent.putExtra("evaluateAnswer", Ans);
        startActivity(intent);
    }

}
