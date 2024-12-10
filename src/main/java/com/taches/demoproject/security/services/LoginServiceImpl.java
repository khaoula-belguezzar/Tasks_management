package com.taches.demoproject.security.services;

import com.taches.demoproject.model.Employee;
import com.taches.demoproject.repository.EmployeeRepository;
import com.taches.demoproject.security.entities.AppRole;
import com.taches.demoproject.security.repositories.AppRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
// pour donner a spring security de gerer les transactions de tous les methodes (Toutes les methodes sont transactionnel)
@Transactional
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private EmployeeRepository employeeRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public Employee addUser(String username, String password, String nom, String prenom, String email, String fonction) {
        Employee employee=employeeRepository.findByUsername(username);
        if (employee!=null) throw new RuntimeException("Déja exist !");
        String hashedPWD = passwordEncoder.encode(password); // hasher le mdp
        //if (!password.equals(rePwd)) throw new RuntimeException("Le mot de passe pas identique !");
        employee = Employee.builder()
                .username(username)
                .password(hashedPWD)
                .nom(nom)
                .prenom(prenom)
                .email(email)
                .fonction(fonction)
                .build();
        Employee saveUser = employeeRepository.save(employee);
        return saveUser;
    }

    @Override
    public AppRole addRole(String roleName) {
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if (appRole!=null) throw new RuntimeException("Déja exist");
        appRole = AppRole.builder()
                .roleName(roleName)
                .build();
        AppRole saveRole = appRoleRepository.save(appRole);
        return saveRole;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        Employee employee = employeeRepository.findByUsername(username) ;
        if (employee == null)
            throw new RuntimeException("Utilisateur introuvable");
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if (appRole == null)
            throw new RuntimeException("Role introuvable");
        List<AppRole> roles = new ArrayList<>();
        roles.add(appRole);
        employee.setRoles(roles);//ajouter le role dans la collection des roles de appUser
        /*On a pas besoin de faire ça , parce que la methode est transactionnele*/
        //appUserRepository.save(appUser);
    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
        Employee employee = employeeRepository.findByUsername(username);
        if (employee == null)
            throw new RuntimeException("Utiilisateur introuvable");
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if (appRole == null)
            throw new RuntimeException("Role introuvable");
        employee.getRoles().remove(appRole);
    }

    @Override
    public Employee loadUserByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }
}
