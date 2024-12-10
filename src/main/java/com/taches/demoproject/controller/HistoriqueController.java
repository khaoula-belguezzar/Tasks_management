package com.taches.demoproject.controller;

import com.taches.demoproject.model.Employee;
import com.taches.demoproject.model.Historique;
import com.taches.demoproject.model.Tache;
import com.taches.demoproject.services.HistoriqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/Historique")
public class HistoriqueController {

    @Autowired
    private HistoriqueService histser;

    public HistoriqueController(HistoriqueService histser) {
        this.histser = histser;
    }

 /*    
    @PostMapping("/addHistorique")
    public String addHistorique(@RequestBody Historique his){
        histser.createHistorique(his);
        return "Historique is added successfully";
    } */

    @DeleteMapping("/deleteHistorique/{his_id}")
    public ResponseEntity<Object> deleteHistorique(@PathVariable int his_id) {
        boolean isHistoriqueExist = histser.isHistoriqueExist(his_id);
        if (isHistoriqueExist) {
            histser.removeHistorique(his_id);
            return new ResponseEntity<>("historique is deleted successfully", HttpStatus.OK);
        } else {
            return null;
        }
    }


    @PutMapping("/updateHistorique/{his_id}")
    public ResponseEntity<Object> updateHistorique(@PathVariable int his_id, @RequestBody Historique his) {
        boolean isHistoriqueExist = histser.isHistoriqueExist(his_id);
        if (isHistoriqueExist) {
            his.setId_historique(his_id);
            histser.updateHistorique(his);
            return new ResponseEntity<>("Historique is updated successfully", HttpStatus.OK);
        } else {
            return null;
        }
    }


    @PostMapping("/getAllHistorique")
    public List<Historique> getAllHistorique() {
        return histser.getAllHistorique();
    }

    @PostMapping("/getHistorique/{his_id}")
    public ResponseEntity<Object> getHistorique(@PathVariable int his_id) {
        boolean isHistoriqueExist = histser.isHistoriqueExist(his_id);
        if (isHistoriqueExist) {
            Optional<Historique> his = histser.getHistorique(his_id);
            return new ResponseEntity<>(his, HttpStatus.OK);
        } else {
            return null;
        }
    }

    @PutMapping("/getNomEmployee/{his_id}")
    public String getNomEmployee(@PathVariable int his_id, @RequestBody Tache tch) {
        boolean isHistoriqueExist = histser.isHistoriqueExist(his_id);
        if (isHistoriqueExist) {
            Employee emp = tch.getEmp();
            String nom_emp = emp.getNom();
            return nom_emp;
        } else {
            return null;
        }
    }

//    @PutMapping("/getDateModification/{his_id}")
//    public String getDateModification(@PathVariable int his_id, @RequestBody Tache tch, @RequestBody String Eta) {
//        boolean isHistoriqueExist = histser.isHistoriqueExist(his_id);
//        if (isHistoriqueExist) {
//            String date_modification = histser.getDateModification(tch, Eta);
//            return "La date de modification de la tache " + tch.getNomTch() + " est " + date_modification + " .";
//        } else {
//            return null;
//        }
//    }

}
