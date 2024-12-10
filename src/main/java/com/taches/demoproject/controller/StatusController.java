package com.taches.demoproject.controller;

import com.taches.demoproject.model.Status;
import com.taches.demoproject.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Status")
public class StatusController {

    @Autowired
    private StatusService staser;

    public StatusController(StatusService staser) {
        this.staser = staser;
    }

    @PostMapping("/addStatus")
    public String addStatus(@RequestBody Status Stat) {
        staser.createStatus(Stat);
        return "Status is added successfully";
    }

    @DeleteMapping("/deleteStatus/{stat_id}")
    public ResponseEntity<Object> deleteStatus(@PathVariable int stat_id) {
        boolean isStatusExist = staser.isStatusExist(stat_id);
        if (isStatusExist) {
            staser.removeStatus(stat_id);
            return new ResponseEntity<>("Status is deleted successfully", HttpStatus.OK);
        } else {
            return null;
        }
    }

    @PutMapping("/updateStatus/{stat_id}")
    public ResponseEntity<Object> updateStatus(@PathVariable int stat_id, @RequestBody Status stat) {
        boolean isStatusExist = staser.isStatusExist(stat_id);
        if (isStatusExist) {
            stat.setId_status(stat_id);
            staser.updateStatus(stat);
            return new ResponseEntity<>("Status is updated successfully", HttpStatus.OK);
        } else {
            return null;
        }
    }

    @PostMapping("/getAllStatus")
    public List<Status> getAllStatus() {
        return staser.getAllStatus();
    }


}
