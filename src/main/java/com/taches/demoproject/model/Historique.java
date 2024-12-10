package com.taches.demoproject.model;


import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "Historique") // create the table with the class name
public class Historique {

    @Column(length = 30, name = "respo")
    public String Respo;
    @Id
    @Column(name = "Id_historique", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id_historique;
    @Column(length = 30, name = "Etat")
    private String etat;
    @Column(name = "Date_modification")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date Datemodification;
    @OneToOne
    @JoinColumn(name = "tch_id", referencedColumnName = "Id_tache")
    private Tache tch;

    public Historique() {
    }


    public Historique(String etat, Date datemodification, String respo, Tache tch) {
        this.etat = etat;
        Datemodification = datemodification;
        Respo = respo;
        this.tch = tch;
    }

    public long getId_historique() {
        return Id_historique;
    }

    public void setId_historique(long id_historique) {
        Id_historique = id_historique;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDatemodification() {
        return Datemodification;
    }

    public void setDatemodification(Date datemodification) {
        Datemodification = datemodification;
    }

    public String getRespo() {
        return Respo;
    }

    public void setRespo(String respo) {
        Respo = respo;
    }

    @Override
    public String toString() {
        return "Historique [Id_historique=" + Id_historique + ", etat=" + etat + ", Datemodification="
                + Datemodification + ", Respo=" + Respo + ", tch=" + tch + "]";
    }

    public Tache getTch() {
        return tch;
    }

    public void setTch(Tache tch) {
        this.tch = tch;
    }

}
