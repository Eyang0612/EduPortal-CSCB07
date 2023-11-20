package com.example.b07project.studentPages.EventReservation;

public class Reservation {
    private String eventId;
    private String studentId;

    public Reservation() {
    }

    // 参数化构造函数
    public Reservation(String eventId, String studentId) {
        this.eventId = eventId;
        this.studentId = studentId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
