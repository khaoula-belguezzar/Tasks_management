package com.taches.demoproject.services;

import com.taches.demoproject.model.Type;
import com.taches.demoproject.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TypeService {

    @Autowired
    private TypeRepository Typrep;

    public TypeService(TypeRepository typrep) {
        this.Typrep = typrep;
    }

    public void createType(Type typ) {
        Typrep.save(typ);
    }

    public void removeType(Integer typ_id) {
        Typrep.deleteById(typ_id);
    }

    public void updateType(Type typ) {
        Typrep.save(typ);
    }

    public List<Type> getAllTypes() {
        return Typrep.findAll();
    }

    public Optional<Type> getTypeInfo(Integer typ_id) {
        return Typrep.findById(typ_id);
    }

    public boolean isTypeExist(int typ_id) {
        return Typrep.existsById(typ_id);
    }

}
