package com.taches.demoproject.services;

import com.taches.demoproject.model.Priority;
import com.taches.demoproject.repository.PriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriorityService {

    @Autowired
    private PriorityRepository PrRep;

    public PriorityService(PriorityRepository prRep) {
        PrRep = prRep;
    }

    public void createPriority(Priority Prio) {
        PrRep.save(Prio);
    }

    public void removePriority(Integer Prio_id) {
        PrRep.deleteById(Prio_id);
    }

    public void updatePriority(Priority prio) {
        PrRep.save(prio);
    }

    public List<Priority> getAllPriority() {
        return PrRep.findAll();
    }

    public Optional<Priority> getPriorityInfo(Integer Prio_id) {
        return PrRep.findById(Prio_id);
    }

    public boolean isPriorityExist(int Prio_id) {
        return PrRep.existsById(Prio_id);
    }

}
