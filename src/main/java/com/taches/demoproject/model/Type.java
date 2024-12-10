package com.taches.demoproject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Type") // create the table with the class name
public class Type {

    @Id
    @Column(name = "Id_type", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id_type;
    @Column(length = 30, name = "Libelle_type")
    private String Libelle_type;

    public Type() {
    }


    public Type(long id_type, String libelle_type) {
        Id_type = id_type;
        Libelle_type = libelle_type;
    }

    public long getId_type() {
        return Id_type;
    }

    public void setId_type(long id_type) {
        Id_type = id_type;
    }

    public String getLibelle_type() {
        return Libelle_type;
    }

    public void setLibelle_type(String libelle_type) {
        Libelle_type = libelle_type;
    }

    @Override
    public String toString() {
        return "Type [Id_type=" + Id_type + ", Libelle_type=" + Libelle_type + "]";
    }


}
