package com.taches.demoproject.security.services;

import com.taches.demoproject.model.Type;
import com.taches.demoproject.security.entities.AppRole;
import com.taches.demoproject.security.repositories.AppRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppRoleService {

    @Autowired
    private AppRoleRepository appRoleRepository;

    public List<AppRole> getAllRoles() {
        return appRoleRepository.findAll();
    }

}
