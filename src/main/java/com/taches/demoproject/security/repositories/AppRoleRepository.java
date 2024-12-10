package com.taches.demoproject.security.repositories;

import com.taches.demoproject.security.entities.AppRole;
import org.hibernate.query.criteria.JpaCoalesce;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRoleName(String roleName);

    AppRole findByRoleId (long roleId);
}
