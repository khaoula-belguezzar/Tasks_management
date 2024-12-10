package com.taches.demoproject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Priority") // create the table with the class name
public class Priority {

    @Id
    @Column(name = "Id_priority", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id_priority;
    @Column(length = 30, name = "Libelle_priority")
    private String Libelle_priority;

    public Priority() {
    }


    public Priority(long id_priority, String libelle_priority) {
        Id_priority = id_priority;
        Libelle_priority = libelle_priority;
    }

    public long getId_priority() {
        return Id_priority;
    }

    public void setId_priority(long id_priority) {
        Id_priority = id_priority;
    }

    public String getLibelle_priority() {
        return Libelle_priority;
    }

    public void setLibelle_priority(String libelle_priority) {
        Libelle_priority = libelle_priority;
    }

    @Override
    public String toString() {
        return "Priority [Id_priority=" + Id_priority + ", Libelle_priority=" + Libelle_priority + "]";
    }


}
