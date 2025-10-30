package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Events {
    private int id;
    private int studentId;
    private String title;
    private String description;
    private String eventType;   // "parcial", "entrega", "otro"
    private LocalDate eventDate;

    public Events() {}

    public Events(int id, int studentId, String title, String description, String eventType, LocalDate eventDate) {
        this.id = id;
        this.studentId = studentId;
        this.title = title;
        this.description = description;
        this.eventType = eventType;
        this.eventDate = eventDate;
    }

    public Events(int studentId, String title, String description, String eventType, LocalDate eventDate) {
        this.studentId = studentId;
        this.title = title;
        this.description = description;
        this.eventType = eventType;
        this.eventDate = eventDate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public LocalDate getEventDate() { return eventDate; }
    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }

}
