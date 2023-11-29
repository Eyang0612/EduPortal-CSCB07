package com.example.b07project.studentPages.Review;

public class Review {

    private double ratingValue;
    private String reciewID, userId, userName, comments;



    public Review(String reciewID, String userName, String userId, double ratingValue, String comments){
        this.ratingValue = ratingValue;
        this.comments = comments;
        this.userId = userId;
        this.userName = userName;
        this.reciewID = reciewID;

    }

    public double getratingValue(){return ratingValue;}
    public String getcomments(){return comments;}
    public String getUserId(){
        return userId;
    }
    public String getUserName(){
        return userName;
    }
    public String getReciewID(){
        return reciewID;
    }

}
