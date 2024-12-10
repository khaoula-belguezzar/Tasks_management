package com.taches.demoproject.services;

import com.taches.demoproject.model.Employee;
import com.taches.demoproject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository empRep;

    public EmployeeService(EmployeeRepository empRep) {
        this.empRep = empRep;
    }

    // public Employee getEmployeeinfo (Employee Em){
    //     return empRep.findByEm(Em);
    // }

    public void removeEmployee(Integer id_Em) {
        empRep.deleteById(id_Em);

    }

    public void addEmployee(Employee Em) {
        empRep.save(Em);
    }

    public void updateEmployee(Employee Em) {
        empRep.save(Em);

    }

    public List<Employee> getAllEmployee() {
        return empRep.findAll();
    }

    public Optional<Employee> getEmployeeById(Integer id_Em) {
        return empRep.findById(id_Em);
    }

    public boolean isEmployeeExist(Integer emp_id) {
        return empRep.existsById(emp_id);
    }

    public Employee getEmployeeByEmail(String email) {
        return empRep.findByemail(email);
    }

    public Employee getEmpInfos(String email, String password) {
        return empRep.findByEmailAndPassword(email, password);
    }

}

