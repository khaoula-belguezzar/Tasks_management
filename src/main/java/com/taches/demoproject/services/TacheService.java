package com.taches.demoproject.services;

import com.taches.demoproject.model.*;
import com.taches.demoproject.repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TacheService {

    @Autowired
    private TacheRepository Tchrep;

    public TacheService(TacheRepository tchrep) {
        Tchrep = tchrep;
    }

    public void createTache(Tache Tch) {
        Tchrep.save(Tch);
    }

    public void removeTache(long Tch_id) {
        Tchrep.deleteById(Tch_id);
    }

    public void updateTache(Tache Tch) {
        Tchrep.save(Tch);
    }

    public List<Tache> gatAllTaches() {
        return Tchrep.findAll();
    }

    // find tache by date d'affectation
    // public Tache getTacheByDate(String dateAffectation) {
    //     return Tchrep.findBydateAffectation(dateAffectation);
    // }

    // find tache by priority du tache
    public List<Tache> getTacheByPriority(Priority prio) {
        return Tchrep.findByprio(prio);
    }

    // find tache by status du tache
//    public List<Tache> getTacheByStatus(Status stat) {
//        return Tchrep.findBystat(stat);
//    }

    // find tache by type du tache
    public List<Tache> getTacheByType(Type typ) {
        return Tchrep.findBytyp(typ);
    }

    // find tache by le projet qu
    public List<Tache> getTacheByProjet(Projet prj) {
        return Tchrep.findBypj(prj);
    }


    public boolean isTacheExist(int Tch_id) {
        return Tchrep.existsById(null);
    }

    public Optional<Tache> getTacheById(long id_tch) {
        return Tchrep.findById(id_tch);
    }

    public long counttachetermine (int d){
        return Tchrep.countByDelivered(d);
    }

}
