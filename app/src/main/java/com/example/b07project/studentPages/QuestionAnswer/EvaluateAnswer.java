package com.example.b07project.studentPages.QuestionAnswer;

import java.io.Serializable;



public class EvaluateAnswer implements Serializable {
    private Integer question1;
    private Integer question2;
    private Double[] question3;


    public EvaluateAnswer(){
        this.question1= 0;
        this.question2 = -1;
        this.question3 = new Double[6];
    }

    public EvaluateAnswer(Integer question1, Integer question2, Double [] question3){
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
    }

    public Integer getQuestion1(){
        return question1;
    }

    public Integer getQuestion2(){
        return question2;
    }
    public Double[] getQuestion3(){
        return question3;
    }

    public void setQuestion1(Integer question1){
        this.question1=question1;
    }
    public void setQuestion2(Integer question2){
        this.question2=question2;
    }
    public void setQuestion3(Double[] grades){
        this.question3 = grades;
    }
    public void setQuestion3_index(Double grade, int index){
        this.question3[index]=grade;
    }




    public String[] Qualify() {
        //Apply for Minor
        switch (question1) {
            case 1:
                return MinorQua();
            case 2:
                return MajorCSQua();
            case 3:
                return MajorMSqQua();
            default:
                return MajorOthQua();
        }
    }

    public int CountNonZero(){
        int count =0;
        for (int i=0; i<6;i++){
            if (question3[i]!=0){
                count++;
            }
        }
        return count;
    }

    public Boolean CountMinorNonZero(){
        int count =0;
        if (question3[0] !=0.0){count++;}
        if (question3[1] !=0.0){count++;}
        if (question3[3] !=0.0){count++;}
        if (count >=1){
            return true;
        }
        return false;
    }


    public String[] MinorQua(){
        String[] result = {"Pass", ""};

        if (!(question2 == 1)) {
            result[0] = "Sorry, you are not qualify...";
            result[1] += "Unsatisfied requirement: Have at least 4.0 credits" + "\n\n";
        }

        if(!(question3[2] != 0.0 && question3[5] != 0.0 && this.CountMinorNonZero())) {
            result[0]= "Sorry, you are not qualify...";
            result[1]+= "Unsatisfied requirement: Complete CSCA08, CSCA48, and one of CSCA67/MATA67, MATA22/A23, MATA30/A31/A32."+ "\n\n";
        }

        if(result[0].equals("Pass")){
            result[1] ="Congratulations! You have fulfilled all the requirement!";
        }
        return result;


    }

    public boolean AboveTwopFive(){
        double sum =0;
        for (int i=0; i<5;i++){
            sum = sum +question3[i];
            }

        if(sum/5>=2.5){
            return true;
        }
        return false;

    }

    public String[] MajorCSQua(){
        String[] result = {"Pass", ""};

        if(!(CountNonZero()==6)){
            result[0] ="Sorry, you are not qualify...";
            result[1]+= "Unsatisfied requirement: Complete all A-level courses (CSC/MATA67, CSCA48, MATA22, MATA37, CSCA08, MATA31)." + "\n\n";
        }

        if (question2 != 1){
            result[0] ="Sorry, you are not qualify...";
            result[1]+= "Unsatisfied requirement: Have at least 4.0 credits." + "\n\n";
        }

        if(!AboveTwopFive()){
            result[0] ="Sorry, you are not qualify...";
            result[1]+= "Unsatisfied requirement: A grade point average of at least 2.5 across the following five courses:  CSC/MATA67, CSCA48, MATA22, MATA31, MATA37." + "\n\n";
        }

        if(!(question3[2]>=3.0)){
            result[0] ="Sorry, you are not qualify...";
            result[1]+="Unsatisfied requirement: A grade of at least B in CSCA48." + "\n\n";
        }

        if (!((question3[0]>=1.7 && question3[1]>=1.7)||(question3[0]>=1.7 && question3[4]>=1.7)||(question3[1]>=1.7 && question3[4]>=1.7))){
            result[0] ="Sorry, you are not qualify...";
            result[1]+="Unsatisfied requirement: A grade of at least C- in two of CSC/MATA67, MATA22, MATA37."+ "\n\n";
        }

        if(result[0].equals("Pass")){
            result[0] = "You are qualified!";
            result[1] ="Congratulations! You have fulfilled all the requirement!";
        }
        return result;


    }

    public String[] MajorMSqQua(){
        String[] result = {"", ""};

        if (question2 != 1){
            result[0] ="Sorry, you are not qualify...";
            result[1]+= "Unsatisfied requirement: Have at least 4.0 credits." + "\n\n";
        }
        if(!(CountNonZero()==6)){
            result[0] ="Sorry, you are not qualify...";
            result[1]+= "Unsatisfied requirement: Complete all A-level courses (CSC/MATA67, CSCA48, MATA22, MATA37, CSCA08)."+ "\n\n";
            return result;
        }
        result[0] = "You may be qualified...";
        result[1] = "Math and Statistic students will be selected for admission to these spaces based on their grades in CSC/MATA67, CSCA48, MATA22, MATA37.";
        return result;
    }

    public String[] MajorOthQua(){
        String[] result = {"Pass", ""};


        if(!(CountNonZero()==6)){
            result[0] ="Sorry, you are not qualify...";
            result[1]+= "Unsatisfied requirement: Complete all A-level courses (CSC/MATA67, CSCA48, MATA22, MATA37, CSCA08, MATA31)." + "\n\n";
        }

        if (question2 != 1){
            result[0] ="Sorry, you are not qualify...";
            result[1]+= "Unsatisfied requirement: Have at least 4.0 credits." + "\n\n";
        }

        if(!(question3[0]>= 3.7 || question3[3]>=3.7)){
            result[0] ="Sorry, you are not qualify...";
            result[1]+= "Unsatisfied requirement: At least an A- in both CSC/MATA67 and MATA31 the first time that they complete those courses."+ "\n\n";
        }

        if(result[0].equals("Pass")){
            result[0] = "You are qualified!";
            result[1] ="Congratulations! You have fulfilled all the requirement!";
        }
        return result;
    }

}