package com.example.b07project.studentPages.Complaint;

public class Complaint {
    private String complaintID, userId, userName, title, description, submissionTime;

    public Complaint(String complaintID, String userId, String userName, String title, String description, String submissionTime){
        this.complaintID = complaintID;
        this.userName = userName;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.submissionTime = submissionTime;
    }

    public String getComplaintID(){
        return complaintID;
    }
    public String getUserId(){
        return userId;
    }
    public String getUserName(){
        return userName;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public String getSubmissionTime(){
        return submissionTime;
    }
}
