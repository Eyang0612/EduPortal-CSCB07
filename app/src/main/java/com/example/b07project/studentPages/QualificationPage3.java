package com.example.b07project.studentPages;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.b07project.R;
import com.example.b07project.studentPages.QuestionAnswer.EvaluateAnswer;
import com.example.b07project.studentPages.QuestionAnswer.QA;


public class QualificationPage3 extends AppCompatActivity implements View.OnClickListener {
    private TextView questionTextView, course1, course2, course3, course4, course5, course6;
    private Button submit, previous, back;
    private EditText grade1, grade2, grade3, grade4, grade5, grade6;
    private EvaluateAnswer Ans;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_qualification_page2);
        Intent intent = getIntent();
        Ans = intent.getSerializableExtra("evaluateAnswer", EvaluateAnswer.class);

        if(Ans.getQuestion3()!=null){
            Double[] grade_list = Ans.getQuestion3();
            grade1.setText(Double.toString(grade_list[0]));
            grade2.setText(Double.toString(grade_list[1]));
            grade3.setText(Double.toString(grade_list[2]));
            grade4.setText(Double.toString(grade_list[3]));
            grade5.setText(Double.toString(grade_list[4]));
            grade6.setText(Double.toString(grade_list[5]));
        }
        else{Ans.setQuestion3(new Double[6]);}

        questionTextView = findViewById(R.id.question);
        course1 = findViewById(R.id.course1);
        course2 = findViewById(R.id.course2);
        course3 = findViewById(R.id.course3);
        course4 = findViewById(R.id.course4);
        course5 = findViewById(R.id.course5);
        course6 = findViewById(R.id.course6);
        previous = findViewById(R.id.previous);

        grade1 = findViewById((R.id.text_1));
        grade2 = findViewById(R.id.text_2);
        grade3 = findViewById((R.id.text_3));
        grade4 = findViewById(R.id.text_4);
        grade5 = findViewById(R.id.text_5);
        grade6 =findViewById(R.id.text_6);

        back = findViewById(R.id.back);
        back.setOnClickListener(this);



        submit = findViewById(R.id.submit);

        submit.setOnClickListener(this);
        previous.setOnClickListener(this);

        loadQuestion2();

    }

    @Override
    public void onClick(View v) {
        Button clickedChoice = (Button) (v);

        if (clickedChoice.getId() == R.id.submit) {
            String[] grade_list = new String[6];

            try {
                for (int i = 0; i < 6; i++) {
                    grade_list[i] = getGradeText(i);

                    if (TextUtils.isEmpty(grade_list[i])) {
                        Ans.setQuestion3_index(0.0, i);
                    } else {
                        double actualGrade = Double.parseDouble(grade_list[i]);
                        if (actualGrade < 4.0) {
                            Toast toast = Toast.makeText(QualificationPage3.this, "Course grade must be less than 4.0!", Toast.LENGTH_SHORT);
                            toast.show();
                            return; // Stop further processing if grade is invalid
                        } else {
                            Ans.setQuestion3_index(actualGrade, i);
                        }
                    }
                }

                // If all grades are valid, proceed to the next page
                LoadNewPage();
            } catch (NumberFormatException e) {
                String errorMessage = "Invalid Number Format";
                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        } else if (clickedChoice.getId() == R.id.back) {
            Intent intent = new Intent(QualificationPage3.this, studentHomePage.class);
            startActivity(intent);
        } else if (clickedChoice.getId() == R.id.previous) {
            Intent intent = new Intent(QualificationPage3.this, QualificationPage2.class);
            intent.putExtra("evaluateAnswer", Ans);
            startActivity(intent);
        }
    }

    private String getGradeText(int index) {
        switch (index) {
            case 0:
                return grade1.getText().toString();
            case 1:
                return grade2.getText().toString();
            case 2:
                return grade3.getText().toString();
            case 3:
                return grade4.getText().toString();
            case 4:
                return grade5.getText().toString();
            case 5:
                return grade6.getText().toString();
            default:
                return "";
        }
    }

    void loadQuestion2(){
        if(Ans.getQuestion1()==1 || Ans.getQuestion2() == 2){
            questionTextView.setText(QA.Questions[0]);
        }
        else{questionTextView.setText(QA.Questions[1]);}

        if(Ans.getQuestion1() == 1){
            course1.setText(QA.Choices[1][0]);
            course2.setText(QA.Choices[1][1]);
            course3.setText(QA.Choices[1][2]);
            course4.setText(QA.Choices[1][3]);
            course5.setText(QA.Choices[1][4]);
            course6.setText(QA.Choices[1][5]);
        }
        else {
            course1.setText(QA.Choices[0][0]);
            course2.setText(QA.Choices[0][1]);
            course3.setText(QA.Choices[0][2]);
            course4.setText(QA.Choices[0][3]);
            course5.setText(QA.Choices[0][4]);
            course6.setText(QA.Choices[0][5]);

        }
    }

    void LoadNewPage(){
        Intent intent = new Intent(QualificationPage3.this, QualificationPage4.class);
        intent.putExtra("evaluateAnswer", Ans);
        startActivity(intent);
    }




}
