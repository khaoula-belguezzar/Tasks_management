package com.taches.demoproject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Status") // create the table with the class name
public class Status {

    @Id
    @Column(name = "Id_status", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 30, name = "Libelle_status")
    private String Libelle_status;

    public Status() {
    }


    public Status(long id_status, String libelle_status) {
        id = id_status;
        Libelle_status = libelle_status;
    }

    public long getId_status() {
        return id;
    }

    public void setId_status(long id_status) {
        id = id_status;
    }

    public String getLibelle_status() {
        return Libelle_status;
    }

    public void setLibelle_status(String libelle_status) {
        Libelle_status = libelle_status;
    }

    @Override
    public String toString() {
        return "Status [Id_status=" + id + ", Libelle_status=" + Libelle_status + "]";
    }


}
