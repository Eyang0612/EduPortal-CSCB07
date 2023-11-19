package com.example.b07project.studentPages.Complaint;

public class Complaint {
    private String complaintID, userId, title, description;

    public Complaint(String complaintID, String userId, String title, String description){
        this.complaintID = complaintID;
        this.userId = userId;
        this.title = title;
        this.description = description;
    }

    public String getComplaintID(){
        return complaintID;
    }
    public String getUserId(){
        return userId;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
}
