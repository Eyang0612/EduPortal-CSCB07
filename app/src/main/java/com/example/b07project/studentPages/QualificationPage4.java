package com.example.b07project.studentPages;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.b07project.R;
import com.example.b07project.studentPages.QuestionAnswer.EvaluateAnswer;



public class QualificationPage4 extends AppCompatActivity implements View.OnClickListener {
    TextView Result, Message;
    EvaluateAnswer Ans;
    Button Home;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_qualification_page4);
        Intent intent = getIntent();
        Ans = intent.getSerializableExtra("evaluateAnswer", EvaluateAnswer.class);

        Result = findViewById(R.id.result);
        Message = findViewById(R.id.message);
        Home = findViewById(R.id.back);
        Home.setOnClickListener(this);

        loadResult();

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(QualificationPage4.this, StudentHomePage.class);
        startActivity(intent);

    }

    void loadResult(){
        String[] Evaluate_result= Ans.Qualify();
        Result.setText(Evaluate_result[0]);
        Message.setText(Evaluate_result[1]);
    }





}
