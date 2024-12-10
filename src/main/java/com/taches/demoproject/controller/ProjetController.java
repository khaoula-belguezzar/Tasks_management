package com.taches.demoproject.controller;

import com.taches.demoproject.model.Employee;
import com.taches.demoproject.model.Projet;
import com.taches.demoproject.repository.ProjetRepository;
import com.taches.demoproject.services.ProjetService;
import com.taches.demoproject.services.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/Projet")
public class ProjetController {

    @Autowired
    private ProjetService prjser;

    @Autowired
    private ProjetRepository prjrep;

    @Autowired
    private TacheService tchser;

    public ProjetController(ProjetService prjser) {
        this.prjser = prjser;
    }

    @PostMapping("/CreateProjet")
    public String CreateProjet(Projet prj) {
        prjser.addProjet(prj);
        return "redirect:/Projet/listProjet";
    }

    @DeleteMapping("/deleteProjet/{prj_id}")
    public ResponseEntity<Object> deleteProjet(@PathVariable int prj_id) {
        boolean isProjetExist = prjser.isProjetExist(prj_id);
        if (isProjetExist) {
            prjser.removeProjet(prj_id);
            return new ResponseEntity<>("Projet is deleted successfully", HttpStatus.OK);
        } else {
            return null;
        }
    }

    @PostMapping("/updateProjet/{prj_id}")
    public ModelAndView updateProjet(@PathVariable int prj_id, Projet prj, ModelMap model) {
        Projet projet = prjser.getProjetInfo(prj_id).orElse(null);
        if (projet == null) throw new RuntimeException("Projet introuvable");
        projet.setNomPrj(prj.getNomPrj());
        projet.setDescription(prj.getDescription());
        projet.setDatecreation(projet.getDatecreation());
        prjser.updateProjet(projet);
        return new ModelAndView("redirect:/Projet/listProjet", model);
    }


    @GetMapping("/listProjet")
    public ModelAndView getAllTaches(Model model,
                                     @RequestParam(name = "page", defaultValue = "0") int page,
                                     @RequestParam(name = "size", defaultValue = "5") int size,
                                     @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        ModelAndView mav = new ModelAndView("list-projet");
        Page<Projet> pageProjets = prjrep.findByNomPrjContains(keyword, PageRequest.of(page, size));
        model.addAttribute("projets", pageProjets.getContent());
        model.addAttribute("pages", new int[pageProjets.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("projet", new Projet());
        model.addAttribute("taches", tchser.gatAllTaches());
        return mav;
    }

//    @PostMapping("/getProjetInfo/{prj_id}")
//    public ResponseEntity<Object> getProjetInfo(@PathVariable int prj_id) {
//        boolean isProjetExist = prjser.isProjetExist(prj_id);
//        if (isProjetExist) {
//            Optional<Projet> prj = prjser.getProjetInfo(prj_id);
//            return new ResponseEntity<>(prj, HttpStatus.OK);
//        } else {
//            return null;
//        }
//    }

    // @PostMapping("/getProjetByDate/{date}")
    // public ResponseEntity<Object> getProjetByDate(@PathVariable String date){
    //     Projet prj = prjser.getProjetByDate(date);
    //     if (prj != null){
    //         return new ResponseEntity<>(prj, HttpStatus.OK);
    //     }
    //     else{
    //         return null;
    //     }
    // }

}
