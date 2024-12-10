package com.taches.demoproject;

import com.taches.demoproject.security.services.LoginService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.w3c.dom.ls.LSOutput;


@SpringBootApplication
public class DemoProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoProjectApplication.class, args);
    }

    //@Bean
    CommandLineRunner commandLineRunner(LoginService loginService){
        return args -> {
            //loginService.addRole("USER");
            //loginService.addRole("ADMIN");
            //loginService.addUser("khaoula","1234","Bel Guezzar","Khaoula","khaoula@gmail.com","Analyseur");
            //loginService.addUser("Yassin ben","4321");

            //loginService.addRoleToUser("khaoula","ADMIN");
            //loginService.addRoleToUser("Yassin ben","ADMIN");

        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
