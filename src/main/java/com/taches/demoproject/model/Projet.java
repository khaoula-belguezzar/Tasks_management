package com.taches.demoproject.model;


import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "Projet") // create the table with the class name
public class Projet {

    @Id
    @Column(name = "Id_projet", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id_projet;
    @Column(length = 100, name = "Nom_projet")
    private String nomPrj;
    @Column(length = 30, name = "Date_creation")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datecreation;
    @Column(length = 200, name = "Description")
    private String Description;


    public Projet() {
    }

    public Projet(long id_projet, String nomPrj, Date datecreation, String description) {
        Id_projet = id_projet;
        this.nomPrj = nomPrj;
        this.datecreation = datecreation;
        Description = description;
    }

    public long getId_projet() {
        return Id_projet;
    }

    public void setId_projet(long id_projet) {
        Id_projet = id_projet;
    }

    public String getNomPrj() {
        return nomPrj;
    }

    public void setNomPrj(String nomPrj) {
        this.nomPrj = nomPrj;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return "Projet [Id_projet=" + Id_projet + ", nomPrj=" + nomPrj + ", datecreation=" + datecreation
                + ", Description=" + Description + "]";
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }


}
