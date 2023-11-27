package com.example.b07project.adminPages.EventSetUp;

public class Event {
    private String eventId;
    private String title;
    private String location;
    private String eventDate;
    private String time;
    private String limit;

    private String adminName;
    private String description;

    private String postDate;

    // Default constructor (needed for Firebase)
    public Event() {
    }

    // Parameterized constructor
    public Event(String eventId, String title, String location, String eventDate, String time, String limit, String adminName, String description, String postDate) {
        this.eventId = eventId;
        this.title = title;
        this.location = location;
        this.eventDate = eventDate;
        this.time = time;
        this.limit = limit;
        this.adminName = adminName;
        this.description = description;
        this.postDate = postDate;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

