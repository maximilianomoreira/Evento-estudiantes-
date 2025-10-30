package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Events {
    private int id;
    private int student_id;
    private String title;
    private String description;
    private LocalDate event_date;
    private String event_type;

    public Events(){

    }

    public Events(int id, int student_id, String title, String description, LocalDate event_date, String event_type){
        this.id = id;
        this.student_id = student_id;
        this.title = title;
        this.description = description;
        this.event_date = event_date;
        this.event_type = event_type;
    }

    public Events (int student_id, String title, String description, LocalDate event_date, String event_type){
        this.student_id = student_id;
        this.title = title;
        this.description = description;
        this.event_date = event_date;
        this.event_type = event_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent_id() {
        return student_id;
    }
    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getEvent_date() {
        return event_date;
    }
    public void setEvent_date(LocalDate event_date) {
        this.event_date = event_date;
    }
    public String getEvent_type() {
        return event_type;
    }
    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public void mostrarInformacion(){
        System.out.println("ID: " + id);
        System.out.println("Student ID: " + student_id);
        System.out.println("Name: " + title);
        System.out.println("Description: " + description);
        System.out.println("Event date: " + event_date);
        System.out.println("Event type: " + event_type);
    }

}
