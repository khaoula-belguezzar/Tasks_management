package com.taches.demoproject.repository;

//import org.springframework.data.jdbc.repository.query.Query;

import com.taches.demoproject.model.Historique;
import com.taches.demoproject.model.Tache;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface HistoriqueRepository extends JpaRepository<Historique, Integer> {

    public Historique findByTch(Tache Tch);

    public String findNomByTch(Tache Tch);

    //@Query("select....")
    Historique findByTchAndEtat(Tache tch, String Etat);


}
