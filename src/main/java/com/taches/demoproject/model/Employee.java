package com.taches.demoproject.model;


import com.taches.demoproject.security.entities.AppRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.sql.In;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Employee") // create the table with the class name
@AllArgsConstructor  @NoArgsConstructor @Builder
public class Employee {

    @Id
    @Column(name = "Id_employee", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)//This is for generate the id automatically by jpa
    private Integer Id_employee;
    //@NotEmpty(message = "Mode de passe !!")
    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    //@NotEmpty(message = "Le nom de l'employee est obligatoire !")
    @Column(length = 40, name = "nom")
    private String nom;
    //@NotEmpty(message = "Le prenom de l'employee est obligatoire !")
    @Column(length = 40, name = "prenom")
    private String prenom;
    @Column(length = 100, name = "email")
    //@Email(message = "doit être une adresse e-mail bien formée")
    //@NotEmpty(message = "Obligatoire de saisil'email de l'employee !")
    private String email;
    //@NotEmpty(message = "Le type de l'employee est obligatoire !")
    @Column(length = 40, name = "type")
    private String type;
    //@NotEmpty(message = "La fonction de l'employee est obligatoire !")
    @Column(length = 40, name = "fonction")
    private String fonction;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppRole> roles = new ArrayList<AppRole>();

    public Integer getId_employee() {
        return Id_employee;
    }

    public void setId_employee(Integer id_employee) {
        Id_employee = id_employee;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "Id_employee=" + Id_employee +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", type='" + type + '\'' +
                ", fonction='" + fonction + '\'' +
                '}';
    }

    public String concat() {
        String a = nom;
        String b = prenom;
        return a + " " + b;

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AppRole> roles) {
        this.roles = roles;
    }
}
