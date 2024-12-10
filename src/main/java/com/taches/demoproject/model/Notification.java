package com.taches.demoproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String username;

    @Column
    private String task;

    @Column
    private String status;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateAffectaion;

    public Notification(String username, String nomTch, String libelleStatus, Date date) {
        this.username=username;
        this.task=nomTch;
        this.status= libelleStatus;
        this.dateAffectaion=date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateAffectaion() {
        return dateAffectaion;
    }

    public void setDateAffectaion(Date dateAffectaion) {
        this.dateAffectaion = dateAffectaion;
    }

    public Notification() {
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", task='" + task + '\'' +
                ", status='" + status + '\'' +
                ", dateAffectaion=" + dateAffectaion +
                '}';
    }
}

