package com.taches.demoproject.repository;

import com.taches.demoproject.model.Priority;
import org.springframework.data.jpa.repository.JpaRepository;


//@Repository
public interface PriorityRepository extends JpaRepository<Priority, Integer> {

}
