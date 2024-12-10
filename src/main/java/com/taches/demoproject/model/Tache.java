package com.taches.demoproject.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Tache") // create the table with the class name
public class Tache {

    @Id
    @Column(name = "Id_tache", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_tache;
    @Column(length = 100, name = "Nomtache")
    private String nomTch;
    @Column(name = "Date_affectation")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateAffectation;
    @Column(name = "last_delai")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date last_delai;
    @Column(name = "valide")
    private int valide = 0;

    @Column(name = "delivered")
    private int delivered = 0;

    @Column(name = "cancelled")
    private int cancelled = 0;
    @ManyToOne
    @JoinColumn(name = "pj_id")
    private Projet pj;
    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee emp;
    @ManyToOne
    @JoinColumn(name = "stat_id")
    private Status stat;
    @ManyToOne
    @JoinColumn(name = "prio_id")
    private Priority prio;
    @ManyToOne
    @JoinColumn(name = "typ_id")
    private Type typ;
    @OneToMany(mappedBy = "tch", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Historique> historiques = new ArrayList<Historique>();

    public Tache() {
    }

    public Tache(Integer id_tache, String nomTch, Date dateAffectation, Date last_delai, Projet pj, Employee emp,
                 Status stat, Priority prio, Type typ) {
        Id_tache = id_tache;
        this.nomTch = nomTch;
        this.dateAffectation = dateAffectation;
        this.last_delai = last_delai;
        this.pj = pj;
        this.emp = emp;
        this.stat = stat;
        this.prio = prio;
        this.typ = typ;
    }

    public Integer getId_tache() {
        return Id_tache;
    }

    public void setId_tache(Integer id_tache) {
        Id_tache = id_tache;
    }

    public String getNomTch() {
        return nomTch;
    }

    public void setNomTch(String nomTch) {
        this.nomTch = nomTch;
    }

    public Date getDateAffectation() {
        return dateAffectation;
    }

    public void setDateAffectation(Date dateAffectation) {
        this.dateAffectation = dateAffectation;
    }

    public Date getLast_delai() {
        return last_delai;
    }

    public void setLast_delai(Date last_delai) {
        this.last_delai = last_delai;
    }

    public Projet getPj() {
        return pj;
    }

    public void setPj(Projet pj) {
        this.pj = pj;
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    public Status getStat() {
        return stat;
    }

    public void setStat(Status stat) {
        this.stat = stat;
    }

    public Priority getPrio() {
        return prio;
    }

    public void setPrio(Priority prio) {
        this.prio = prio;
    }

    public Type getTyp() {
        return typ;
    }

    public void setTyp(Type typ) {
        this.typ = typ;
    }

    public List<Historique> getHistoriques() {
        return historiques;
    }

    public void setHistoriques(List<Historique> historiques) {
        this.historiques = historiques;
    }


    public int getValide() {
        return valide;
    }

    public void setValide(int valide) {
        this.valide = valide;
    }

    public int getDelivered() {
        return delivered;
    }

    public void setDelivered(int delivered) {
        this.delivered = delivered;
    }

    public int getCancelled() {
        return cancelled;
    }

    public void setCancelled(int cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public String toString() {
        return "Tache{" +
                "Id_tache=" + Id_tache +
                ", nomTch='" + nomTch + '\'' +
                ", dateAffectation=" + dateAffectation +
                ", last_delai=" + last_delai +
                ", valide=" + valide +
                ", delivered=" + delivered +
                ", cancelled=" + cancelled +
                ", pj=" + pj +
                ", emp=" + emp +
                ", stat=" + stat +
                ", prio=" + prio +
                ", typ=" + typ +
                ", historiques=" + historiques +
                '}';
    }
}
