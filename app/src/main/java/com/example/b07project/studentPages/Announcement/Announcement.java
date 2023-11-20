package com.example.b07project.studentPages.Announcement;

public class Announcement {
    private String announcementID, userId, title, description;

    public Announcement(String announcementID, String userId, String title, String description){
        this.announcementID = announcementID;
        this.userId = userId;
        this.title = title;
        this.description = description;
    }

    public String getAnnouncementID(){
        return announcementID;
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
