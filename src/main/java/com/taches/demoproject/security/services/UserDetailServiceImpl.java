package com.taches.demoproject.security.services;

//simport com.taches.demoproject.security.entities.AppRole;
import com.taches.demoproject.model.Employee;
import com.taches.demoproject.security.entities.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private LoginService loginService;

    /* a chaque fois que l'utilisateur va saisi leur email et password
    il fait appell à cette methode la*/
    @Override
    //code qui va se repetter dans tt les app qui utilise spring security
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = loginService.loadUserByUsername(username);
        if (employee == null) throw new UsernameNotFoundException(String.format("L'utilisateur %s est introuvable", username));

        String[] roles = employee.getRoles().stream().map(u->u.getRoleName()).toArray(String[]::new);
        UserDetails userDetails = User
                .withUsername(employee.getUsername())
                .password((employee.getPassword()))
                .roles(roles)
                .build();
        return  userDetails;
    }

}
