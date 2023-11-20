package com.example.b07project.adminPages.EventSetUp;

public class Event {
    private String eventId;
    private String title;
    private String location;
    private String date;
    private String time;
    private String limit;

    private String adminName;
    private String description;

    // Default constructor (needed for Firebase)
    public Event() {
    }

    // Parameterized constructor
    public Event(String eventId, String title, String location, String date, String time, String limit, String adminName, String description) {
        this.eventId = eventId;
        this.title = title;
        this.location = location;
        this.date = date;
        this.time = time;
        this.limit = limit;
        this.adminName = adminName;
        this.description = description;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

