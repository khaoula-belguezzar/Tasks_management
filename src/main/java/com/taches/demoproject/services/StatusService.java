package com.taches.demoproject.services;

import com.taches.demoproject.model.Status;
import com.taches.demoproject.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class StatusService {

    @Autowired
    private StatusRepository StatRep;

    public StatusService(StatusRepository statRep) {
        StatRep = statRep;
    }

    public void createStatus(Status status) {
        StatRep.save(status);
    }

    public void removeStatus(Integer Id_status) {
        StatRep.deleteById(Id_status);
    }

    public void updateStatus(Status status) {
        StatRep.save(status);
    }

    public List<String> getsixStatus() {
        return StatRep.findTop6ByOrderByIdAsc()
                .stream()
                .map(Status::getLibelle_status)
                .collect(Collectors.toList());
    }

    public Optional<Status> getStatusInfo(Integer Id_status) {
        return StatRep.findById(Id_status);
    }

    public boolean isStatusExist(int Id_status) {
        return StatRep.existsById(Id_status);
    }

    public List<Status> getAllStatus() {
        return StatRep.findAll();
    }
}