package com.taches.demoproject.repository;

import com.taches.demoproject.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;


//@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

    List<Status> findTop6ByOrderByIdAsc();
    
}
