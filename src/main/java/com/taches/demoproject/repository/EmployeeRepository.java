package com.taches.demoproject.repository;


import com.taches.demoproject.model.Employee;
import com.taches.demoproject.security.entities.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


//@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //public Employee findByEm(Employee Em);

    Employee findByUsername(String username);

    public Employee findByemail(String email);

    public Employee findByEmailAndPassword(String email, String password);

    Page<Employee> findBynomContains(String kw, Pageable pageable);

    long count();

}
