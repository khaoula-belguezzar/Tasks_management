package com.taches.demoproject.controller;

import com.taches.demoproject.model.*;
import com.taches.demoproject.repository.EmployeeRepository;
import com.taches.demoproject.repository.HistoriqueRepository;
import com.taches.demoproject.repository.NotificationRepository;
import com.taches.demoproject.repository.TacheRepository;
import com.taches.demoproject.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.bind.SchemaOutputResolver;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/Tache")
public class TacheController {

    @Autowired
    private TacheService tchser;

    @Autowired
    private TacheRepository tchrep;

    @Autowired
    private ProjetService prjser;

    @Autowired
    private PriorityService prioser;

    @Autowired
    private StatusService statser;

    @Autowired
    private HistoriqueRepository hisrep;

    @Autowired
    private TypeService typser;

    @Autowired
    private EmployeeService empser;

    @Autowired
    private EmployeeRepository emprep;

    @Autowired
    private NotificationRepository notrep;


    public TacheController(TacheService tchser) {
        this.tchser = tchser;
    }

    @PostMapping("/CreateTache")
    public String CreateTache(Tache tch) {
        Date date = new Date();
        Historique His = new Historique(tch.getStat().getLibelle_status(), date, tch.getEmp().concat(), tch);
        tch.getHistoriques().add(His);
        //hisser.createHistorique(His);
        Notification notification = new Notification(tch.getEmp().getUsername(),tch.getNomTch(),tch.getStat().getLibelle_status(),date);
        //System.out.println("new notification : "+ notification.toString());
        notrep.save(notification);
        //System.out.println("new notification : "+ notification.toString());
        tchser.createTache(tch);
        return "redirect:/Tache/listtache";
    }


    @GetMapping("/deleteTache/{tch_id}")
    public ModelAndView deleteTache(@PathVariable long tch_id, ModelMap model) {
        tchser.removeTache(tch_id);
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:/Tache/listtache", model);
    }

    @PostMapping("/updateTache/{tch_id}")
    public ModelAndView updateTache(@PathVariable Integer tch_id, Tache tch, ModelMap model) {
        Date date = new Date();
        Tache tache = tchser.getTacheById(tch_id).orElse(null);
        if (tache == null) throw new RuntimeException("Tache introuvable");
        tache.setNomTch(tch.getNomTch());
        //tache.setDateAffectation(tch.getDateAffectation());
        //tache.setLast_delai(tch.getLast_delai());
        tache.setPj(tch.getPj());
        tache.setEmp(tch.getEmp());
        tache.setPrio(tch.getPrio());
        tache.setTyp(tch.getTyp());
        tache.setStat(tch.getStat());
        Historique His = new Historique(tache.getStat().getLibelle_status(), date, tache.getEmp().concat(), tache);
        tache.getHistoriques().add(His);
        tchser.updateTache(tache);
        return new ModelAndView("redirect:/Tache/listtache", model);
    }


    @GetMapping("/listtache")
    public ModelAndView getAllTaches(Model model,
                                     @RequestParam(name = "page", defaultValue = "0") int page,
                                     @RequestParam(name = "size", defaultValue = "4") int size,
                                     @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        ModelAndView mav = new ModelAndView("users");
        Page<Tache> pageTaches = tchrep.findBynomTchContains(keyword, PageRequest.of(page, size));
        //mav.addObject("taches", pageTaches.getContent());
        model.addAttribute("taches", pageTaches.getContent());
        model.addAttribute("pages", new int[pageTaches.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("tache", new Tache());
        model.addAttribute("projets", prjser.getAllProjet());
        model.addAttribute("types", typser.getAllTypes());
        model.addAttribute("priorites", prioser.getAllPriority());
        model.addAttribute("employees", empser.getAllEmployee());
        model.addAttribute("status", statser.getAllStatus());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        //Employee employee = emprep.findByUsername(username);
        long notif = notrep.countByUsername(username);
        System.out.println(username+"have "+notif+" notif");
        model.addAttribute("notif",notif);
        return mav;
    }

    @GetMapping("/TacheByStatus")
    public ModelAndView getTacheByStatus(Model model,
                                         @RequestParam(name = "page", defaultValue = "0") int page,
                                         @RequestParam(name = "size", defaultValue = "4") int size) {
        ModelAndView mav = new ModelAndView("Dashbord");
        Status status = statser.getStatusInfo(1).orElseThrow();
        Page<Tache> Ptaches = tchrep.findBystat(status, PageRequest.of(page, size));
        model.addAttribute("listetaches",Ptaches.getContent());
        model.addAttribute("taches", Ptaches.getContent());
        model.addAttribute("pages", new int[Ptaches.getTotalPages()]);
        model.addAttribute("currentPage", page);
        long count1 =  tchser.counttachetermine(1);
        model.addAttribute("count1",count1);
        Status stat = statser.getStatusInfo(1).orElse(null);
        long count2 = tchrep.countByStat(stat);
        model.addAttribute("count2",count2);
        long count3 = emprep.count();
        model.addAttribute("count3",count3);
        long count4 = tchrep.countByCancelled(1);
        model.addAttribute("count4",count4);
        return mav;
    }

    @GetMapping("/notification")
    public ModelAndView notification(Model model){
        ModelAndView mav = new ModelAndView("Template");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        //Employee employee = emprep.findByUsername(username);
        long notification = notrep.countByUsername(username);
        System.out.println("hou have "+notification+" notificatiob");
        model.addAttribute("notification",notification);
        return mav;
    }

    @GetMapping("/Search")
    public ModelAndView searchTache(Model model,
                                     @RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "4") int size){
        ModelAndView mav = new ModelAndView("validation-taches");
        Status status = statser.getStatusInfo(5).orElseThrow();
        Page<Tache> pageTaches = tchrep.findByValideAndStat(1,status,PageRequest.of(page, size));
        //mav.addObject("taches", pageTaches.getContent());
        model.addAttribute("tachevalider", pageTaches.getContent());
        model.addAttribute("pages", new int[pageTaches.getTotalPages()]);
        model.addAttribute("currentPage", page);
        return mav;
    }

    @PostMapping("/cancelledStatus/{tch_id}")
    public ModelAndView cancelledStatus(@PathVariable long tch_id, Tache tch, ModelMap model) {
        Date date = new Date();
        Tache tache = tchser.getTacheById(tch_id).orElse(null);
        if (tache == null) throw new RuntimeException("Tache introuvable");
        Status status = statser.getStatusInfo(6).orElse(null);
        tache.setStat(status);
        Historique His = new Historique(tache.getStat().getLibelle_status(), date, tache.getEmp().concat(), tache);
        tache.getHistoriques().add(His);
        tache.setCancelled(1);
        tchser.updateTache(tache);
        return new ModelAndView("redirect:/Tache/listtache", model);
    }

    //pas terminer
    @PostMapping("/updateStatus/{tch_id}")
    public ModelAndView updateStatus(@PathVariable Integer tch_id, Tache tch, ModelMap model) {
        Date date = new Date();
        Tache tache = tchser.getTacheById(tch_id).orElse(null);
        if (tache == null) throw new RuntimeException("Tache introuvable");
        tache.setStat(tch.getStat());
        Historique His = new Historique(tache.getStat().getLibelle_status(), date, tache.getEmp().concat(), tache);
        tache.getHistoriques().add(His);
        tchser.updateTache(tache);
        return new ModelAndView("redirect:/Tache/todo", model);
    }

    //pas terminer
    @PostMapping("/valideTache/{tch_id}")
    public ModelAndView valideTache(@PathVariable Integer tch_id, Tache tch, ModelMap model) {
        //Date date = new Date();
        Tache tache = tchser.getTacheById(tch_id).orElse(null);
        if (tache == null) throw new RuntimeException("Tache introuvable");
        tache.setValide(1);
        //Historique His = new Historique(tache.getStat().getLibelle_status(), date, tache.getEmp().concat(), tache);
        //tache.getHistoriques().add(His);
        tchser.updateTache(tache);
        return new ModelAndView("redirect:/Tache/todo", model);
    }

    //pas terminer
    @GetMapping("/annulettache/{tch_id}")
    public ModelAndView annulertache(@PathVariable Integer tch_id, Tache tch, ModelMap model){
        System.out.println("begin");
        Date date = new Date();
        Tache tache = tchser.getTacheById(tch_id).orElse(null);
        if (tache == null) throw new RuntimeException("Tache introuvable");
        Status status = statser.getStatusInfo(1).orElse(null);
        System.out.println("status : "+status.toString());
        tache.setStat(status);
//        tache.setEmp();
//        tchrep.save(tache);
//        Historique his = hisrep.findByTchAndEtat(tache,status.getLibelle_status());
//        System.out.println("his : "+his.toString());
//        hisrep.save(his);
//        tache.setEmp(his.getTch().getEmp());
        Historique His = new Historique(tache.getStat().getLibelle_status(), date, tache.getEmp().concat(), tache);
        tache.getHistoriques().add(His);
        tchser.updateTache(tache);
        return new ModelAndView("redirect:/Tache/Search", model);
    }

    @GetMapping("/delivered")
    public ModelAndView deliveredTache(Model model,
                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "6") int size){
        ModelAndView mav = new ModelAndView("delivered_tache");
        Status status = statser.getStatusInfo(5).orElseThrow();
        Page<Tache> tachedelivred = tchrep.findByStatAndDelivered(status,1,PageRequest.of(page, size));
        //mav.addObject("taches", pageTaches.getContent());
        model.addAttribute("tachedelivrer", tachedelivred.getContent());
        model.addAttribute("pages", new int[tachedelivred.getTotalPages()]);
        model.addAttribute("currentPage", page);
        //model.addAttribute("keyword", keyword);
        return mav;
    }


    @PostMapping("/updatedel/{tch_id}")
    public ModelAndView updatedel(@PathVariable Integer tch_id, Tache tch, ModelMap model) {
        System.out.println("bonjour");
        //Date date = new Date();
        Tache tache = tchser.getTacheById(tch_id).orElse(null);
        if (tache == null) throw new RuntimeException("Tache introuvable");
        tache.setValide(0);
        tache.setDelivered(1);
        System.out.println("bonjour");
        tchser.updateTache(tache);
        return new ModelAndView("redirect:/Tache/delivered", model);
    }


    @GetMapping("/todo")
    public ModelAndView todo(Model model,
                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "5") int size){
        ModelAndView mav = new ModelAndView("todo");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Employee emp = emprep.findByUsername(username);
        Page<Tache> pageTaches = tchrep.findByEmp(emp,PageRequest.of(page, size));
        model.addAttribute("todo", pageTaches.getContent());
        model.addAttribute("pages", new int[pageTaches.getTotalPages()]);
        model.addAttribute("currentPage", page);
        return mav;
    }

//    @GetMapping("/counttache")
//    public ModelAndView tachetermine(Model model){
//        ModelAndView mav = new ModelAndView("Dashbord");
//        long count1 =  tchser.counttachetermine(1);
//        model.addAttribute("count1",count1);
//        Status stat = statser.getStatusInfo(1).orElse(null);
//        long count2 = tchrep.countByStat(stat);
//        model.addAttribute("count2",count2);
//        return mav;
//    }

    @PostMapping("/getTacheByPriority/{id_prio}")
    public ResponseEntity<Object> getTacheByPriority(@PathVariable Integer id_prio) {
        Priority priority = prioser.getPriorityInfo(id_prio).orElseThrow();
        List<Tache> tch = tchser.getTacheByPriority(priority);
        if (tch != null) {
            return new ResponseEntity<>(tch, HttpStatus.OK);
        } else {
            return null;
        }
    }


    @PostMapping("/getTacheByType/{id_typ}")
    public ResponseEntity<Object> getTacheByType(@PathVariable Integer id_typ) {
        Type type = typser.getTypeInfo(id_typ).orElseThrow();
        List<Tache> tch = tchser.getTacheByType(type);
        if (tch != null) {
            return new ResponseEntity<>(tch, HttpStatus.OK);
        } else {
            return null;
        }
    }


    @PostMapping("/getTacheByProjet/{id_prj}")
    public ResponseEntity<Object> getTacheByProjet(@PathVariable Integer id_prj) {
        Projet projet = prjser.getProjetInfo(id_prj).orElseThrow();
        List<Tache> tch = tchser.getTacheByProjet(projet);
        if (tch != null) {
            return new ResponseEntity<>(tch, HttpStatus.OK);
        } else {
            return null;
        }
    }



}
