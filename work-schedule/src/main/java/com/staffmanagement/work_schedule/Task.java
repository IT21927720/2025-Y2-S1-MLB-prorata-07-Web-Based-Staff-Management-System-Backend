package com.staffmanagement.work_schedule;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "tasks")  // MongoDB collection name
public class Task {

    @Id
    private String id;  // MongoDB uses String/ObjectId instead of Long

    private String tname;
    private String description;
    private String assignTo;
    private LocalDate deadlineDate;
    private String status;

    // ----- Constructors -----
    public Task() {}

    public Task(String tname, String description, String assignTo, LocalDate deadlineDate, String status) {
        this.tname = tname;
        this.description = description;
        this.assignTo = assignTo;
        this.deadlineDate = deadlineDate;
        this.status = status;
    }

    // ----- Getters & Setters -----
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignTo() {
        return assignTo;
    }

    public void setAssignTo(String assignTo) {
        this.assignTo = assignTo;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // ----- toString() for debugging -----
    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", tname='" + tname + '\'' +
                ", description='" + description + '\'' +
                ", assignTo='" + assignTo + '\'' +
                ", deadlineDate=" + deadlineDate +
                ", status='" + status + '\'' +
                '}';
    }
}
