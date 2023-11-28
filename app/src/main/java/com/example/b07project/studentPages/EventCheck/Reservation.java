package com.example.b07project.studentPages.EventCheck;

import com.example.b07project.User.Student;
import com.example.b07project.adminPages.EventSetUp.Event;

import java.io.Serializable;

public class Reservation implements Serializable {
    private Event event;
    private Student student;

    public Reservation() {
    }

    public Reservation(Event event, Student student) {
        this.event = event;
        this.student = student;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
