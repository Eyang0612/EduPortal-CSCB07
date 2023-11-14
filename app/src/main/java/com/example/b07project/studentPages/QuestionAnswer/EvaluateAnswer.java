package com.example.b07project.studentPages.QuestionAnswer;



public class EvaluateAnswer {
    private Integer question1;
    private Boolean question2;
    private Double[] question3;


    public EvaluateAnswer(Integer question1, Boolean question2, Double [] question3){
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
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
        for (int i=0; i<5;i++){
            if (question3[i]!=0){
                count++;
            }
        }
        return count;
    }


    public String[] MinorQua(){
        String[] result = {"Pass", ""};

        if (!question2 == true) {
            result[0] = "Fail";
            result[1] += "Unsatisfied requirement: Have at least 4.0 credits" + "\n";
        }

        if(!(CountNonZero()>=3)) {
            result[0]= "Fail";
            result[1]+= "Unsatisfied requirement: Complete CSCA08, CSCA48, and one of CSCA67/MATA67, MATA22/A23, MATA30/A31/A32.";
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

        if(!(CountNonZero()==5)){
            result[0] ="Fail";
            result[1]+= "Unsatisfied requirement: Complete all A-level courses (CSC/MATA67, CSCA48, MATA22, MATA37, CSCA08)." + "\n";
        }

        if (question2 != true){
            result[0] ="Fail";
            result[1]+= "Unsatisfied requirement: Have at least 4.0 credits" + "\n";
        }

        if(!AboveTwopFive()){
            result[0] ="Fail";
            result[1]+= "Unsatisfied requirement: A grade point average of at least 2.5 across the following five courses:  CSC/MATA67, CSCA48, MATA22, MATA31, MATA37." + "\n";
        }

        if(!(question3[2]>=3.0)){
            result[0] ="Fail";
            result[1]+="Unsatisfied requirement: A grade point average of at least 2.5 across the following five courses:  CSC/MATA67, CSCA48, MATA22, MATA31, MATA37." + "\n";
        }

        if (!((question3[0]>=1.7 && question3[1]>=1.7)||(question3[0]>=1.7 && question3[4]>=1.7)||(question3[1]>=1.7 && question3[4]>=1.7))){
            result[0] ="Fail";
            result[1]+="Unsatisfied requirement: A grade of at least C- in two of CSC/MATA67, MATA22, MATA37.";
        }

        if(result[0].equals("Pass")){
            result[1] ="Congratulations! You have fulfilled all the requirement!";
        }
        return result;


    }

    public String[] MajorMSqQua(){
        String[] result = new String[2];
        if(!(CountNonZero()==5)){
            result[0] ="Fail";
            result[1]+= "Unsatisfied requirement: Complete all A-level courses (CSC/MATA67, CSCA48, MATA22, MATA37, CSCA08).";
            return result;
        }
        result[0] = "Pass";
        result[1] = "Students will be selected for admission to these spaces based on their grades in CSC/MAT A67, CSC A48, MAT A22, MAT A37.";
        return result;
    }

    public String[] MajorOthQua(){
        String[] result = {"Pass", ""};

        if(!(CountNonZero()==5)){
            result[0] ="Fail";
            result[1]+= "Unsatisfied requirement: Complete all A-level courses (CSC/MATA67, CSCA48, MATA22, MATA37, CSCA08)." + "\n";
        }

        if (question2 != true){
            result[0] ="Fail";
            result[1]+= "Unsatisfied requirement: Have at least 4.0 credits" + "\n";
        }

        if(!(question3[0]>= 3.7 || question3[3]>=3.7)){
            result[0] ="Fail";
            result[1]+= "Unsatisfied requirement: At least an A- in both CSC/MAT A67 and MAT A31 the first time that they complete those courses";
        }

        if(result[0].equals("Pass")){
            result[1] ="Congratulations! You have fulfilled all the requirement!";
        }
        return result;
    }
}