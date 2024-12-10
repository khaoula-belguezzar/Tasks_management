package com.taches.demoproject.repository;

import com.taches.demoproject.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;


//@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {

}
