package com.taches.demoproject.controller;

import com.taches.demoproject.model.Priority;
import com.taches.demoproject.services.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/Priority")
public class PriorityController {

    @Autowired
    private PriorityService prioser;

    public PriorityController(PriorityService prioser) {
        this.prioser = prioser;
    }


    @PostMapping("/addPriority")
    public String addPriority(@RequestBody Priority prio) {
        prioser.createPriority(prio);
        return "Priority is added successfully";
    }

    @DeleteMapping("/deletePriority/{prio_id}")
    public ResponseEntity<Object> deletePriority(@PathVariable int prio_id) {
        boolean isPriorityExist = prioser.isPriorityExist(prio_id);
        if (isPriorityExist) {
            prioser.removePriority(prio_id);
            return new ResponseEntity<>("Priority is deleted successfully", HttpStatus.OK);
        } else {
            return null;
        }
    }

    @PutMapping("/updatePriority/{prio_id}")
    public ResponseEntity<Object> updatePriority(@PathVariable int prio_id, @RequestBody Priority prio) {
        boolean isPriorityExist = prioser.isPriorityExist(prio_id);
        if (isPriorityExist) {
            prio.setId_priority(prio_id);
            prioser.updatePriority(prio);
            return new ResponseEntity<>("Priority is updated successfully", HttpStatus.OK);
        } else {
            return null;
        }
    }

    @PostMapping("/getAllPriority")
    public List<Priority> getAllPriority() {
        return prioser.getAllPriority();
    }


}
