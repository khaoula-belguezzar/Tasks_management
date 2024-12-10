package com.taches.demoproject.services;

import com.taches.demoproject.model.Historique;
import com.taches.demoproject.model.Tache;
import com.taches.demoproject.repository.HistoriqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoriqueService {

    @Autowired
    private HistoriqueRepository Hisrep;

    public HistoriqueService(HistoriqueRepository hisrep) {
        Hisrep = hisrep;
    }

    public Historique getHistoriqueinfo(Tache Tch) {
        return Hisrep.findByTch(Tch);

    }

    public List<Historique> getAllHistorique() {
        return Hisrep.findAll();
    }

//    public String getDateModification(Tache nom_tch, String etat) {
//        String Date_M;
//        Date_M = Hisrep.findByTchAndEtat(nom_tch, etat);
//        return Date_M;
//    }

    public String getNomEmployee(Tache tch) {
        String nom;
        nom = Hisrep.findNomByTch(tch);
        return nom;

    }

    public void createHistorique(Historique His) {
        Hisrep.save(His);
    }

    public Optional<Historique> getHistorique(Integer His_id) {
        return Hisrep.findById(His_id);
    }

    public void removeHistorique(Integer His_id) {
        Hisrep.deleteById(His_id);
    }

    public void updateHistorique(Historique His) {
        Hisrep.save(His);
    }

    public boolean isHistoriqueExist(int his_id) {
        return Hisrep.existsById(his_id);
    }

}
