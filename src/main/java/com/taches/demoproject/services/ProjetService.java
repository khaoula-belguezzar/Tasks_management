package com.taches.demoproject.services;

import com.taches.demoproject.model.Projet;
import com.taches.demoproject.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetService {

    @Autowired
    private ProjetRepository PrjRep;

    public ProjetService(ProjetRepository prjRep) {
        PrjRep = prjRep;
    }

    public List<Projet> getAllProjet() {
        return PrjRep.findAll();
    }

    public void removeProjet(int id_prj) {
        PrjRep.deleteById(id_prj);
    }

    public void addProjet(Projet prj) {
        PrjRep.save(prj);
    }

    public void updateProjet(Projet prj) {
        PrjRep.save(prj);
    }

    public Optional<Projet> getProjetInfo(Integer id_prj) {
        return PrjRep.findById(id_prj);
    }

    // public Projet getProjetByDate(String dateCreation) {
    //     return PrjRep.findBydateCreation(dateCreation);
    // }


    public boolean isProjetExist(int id_prj) {
        return PrjRep.existsById(id_prj);
    }
}
