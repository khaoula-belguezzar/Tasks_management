package com.taches.demoproject.controller;

import com.taches.demoproject.model.Type;
import com.taches.demoproject.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Type")
public class TypeController {

    @Autowired
    private TypeService typser;

    public TypeController(TypeService typser) {
        this.typser = typser;
    }

    @PostMapping("/addType")
    public String addType(@RequestBody Type typ) {
        typser.createType(typ);
        return "Type is added successfully";
    }

    @DeleteMapping("/deleteType/{typ_id}")
    public ResponseEntity<Object> deleteType(@PathVariable int typ_id) {
        boolean isTypeExist = typser.isTypeExist(typ_id);
        if (isTypeExist) {
            typser.removeType(typ_id);
            return new ResponseEntity<>("Type is deleted successfully", HttpStatus.OK);
        } else {
            return null;
        }
    }

    @PutMapping("/updateType/{typ_id}")
    public ResponseEntity<Object> updateType(@PathVariable int typ_id, @RequestBody Type typ) {
        boolean isTypeExist = typser.isTypeExist(typ_id);
        if (isTypeExist) {
            typ.setId_type(typ_id);
            typser.updateType(typ);
            return new ResponseEntity<>("Type is updated successfully", HttpStatus.OK);
        } else {
            return null;
        }
    }

    @PostMapping("/getAllTypes")
    public List<Type> getAllTypes() {
        return typser.getAllTypes();
    }


}
