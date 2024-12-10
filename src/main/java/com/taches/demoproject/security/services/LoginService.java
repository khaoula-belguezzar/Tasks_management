package com.taches.demoproject.security.services;

import com.taches.demoproject.model.Employee;
import com.taches.demoproject.security.entities.AppRole;
import com.taches.demoproject.security.entities.AppUser;

public interface LoginService {
    Employee addUser(String username, String password, String nom, String prenom, String email, String fonction);//permet d'ajouter un nv user
    AppRole addRole(String roleName);//cree un role
    void addRoleToUser(String username, String roleName);//associer le role a un user
    void removeRoleFromUser(String username, String roleName);//supprimer un user
    //tres important pour spring security
    //car on besoin d'une methode qui reçoit l'email et qui permet de retourner cet utilisateur par dernière
    Employee loadUserByUsername(String username);//chercher un user
}
