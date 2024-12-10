package com.taches.demoproject.repository;

import com.taches.demoproject.model.Projet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


//@Repository
public interface ProjetRepository extends JpaRepository<Projet, Integer> {

    //select * from Projet where Date_cration = 'dateCreation'
    //public Projet findBydateCreation(String dateCreation);

    Page<Projet> findByNomPrjContains(String kw, Pageable pageable);

}
