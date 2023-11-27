package com.example.b07project.studentPages.Announcement;

public class Announcement {
    private String announcementId, userId, title, description, userName;

    public Announcement(String announcementId, String userId, String userName, String title, String description){
        this.announcementId = announcementId;
        this.userId = userId;
        this.userName = userName;
        this.title = title;
        this.description = description;
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
    public String getDescription(){
        return description;
    }
}
