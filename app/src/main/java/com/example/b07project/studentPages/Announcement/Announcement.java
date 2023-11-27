package com.example.b07project.studentPages.Announcement;

public class Announcement {
    private String announcementId, userId, title, description, userName, postTime;

    public Announcement(String announcementId, String userId, String userName, String title, String description, String postTime){
        this.announcementId = announcementId;
        this.userId = userId;
        this.userName = userName;
        this.title = title;
        this.description = description;
        this.postTime = postTime;
    }

    public String getAnnouncementID(){
        return announcementId;
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
    public String getPostTime() { return postTime; }
    public String getDescription(){ return description; }
}
