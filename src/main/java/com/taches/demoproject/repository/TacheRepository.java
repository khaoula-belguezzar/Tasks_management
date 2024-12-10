package com.taches.demoproject.repository;


import com.taches.demoproject.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


//@Repository
public interface TacheRepository extends JpaRepository<Tache, Long> {

    //select * froù tache where Date_affectation = 'dateAffectation'
    //public Tache findBydateAffectation(String dateAffectation);

    Page<Tache> findBystat(Status stat, Pageable pageable);

    List<Tache> findByprio(Priority prio);

    Page<Tache> findByEmp(Employee employee, Pageable pageable);

    long countByEmpAndStat (Employee employee, Status status);

    List<Tache> findBytyp(Type typ);

    List<Tache> findBypj(Projet prj);

    long countByDelivered (int d);
    long countByStat (Status stat);
    long countByCancelled (int c);

    // @Query("DELETE from tache where N_tache LIKE %?1%")
    // public String deleteByNom_tache(String Nom_tache);

    Page<Tache> findBynomTchContains(String kw, Pageable pageable);

    Page<Tache> findByValideAndStat(int b, Status stat, Pageable pageable);

    Page<Tache> findByStatAndDelivered(Status stat,int d, Pageable pageable);
}
