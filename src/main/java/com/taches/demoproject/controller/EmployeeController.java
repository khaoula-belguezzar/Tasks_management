package com.taches.demoproject.controller;


import com.taches.demoproject.model.Employee;
import com.taches.demoproject.repository.EmployeeRepository;
import com.taches.demoproject.repository.NotificationRepository;
import com.taches.demoproject.security.repositories.AppRoleRepository;
import com.taches.demoproject.security.services.AppRoleService;
import com.taches.demoproject.security.services.LoginServiceImpl;
import com.taches.demoproject.services.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping("/Employee")
public class EmployeeController {

    @Autowired
    private EmployeeService empSer;

    @Autowired
    private EmployeeRepository emprep;

    @Autowired
    private AppRoleService appRoleService;

    @Autowired
    private AppRoleRepository appRoleRepository;

    @Autowired
    private LoginServiceImpl loginServiceImpl;

    @Autowired
    private NotificationRepository notrep;

    public EmployeeController(EmployeeService empSer, EmployeeRepository emprep) {
        this.empSer = empSer;
        this.emprep = emprep;
    }

    @PostMapping("/addEmployee")
    public ModelAndView addEmployee(Employee emp, ModelMap model) {
        loginServiceImpl.addUser(emp.getUsername(),emp.getPassword(),emp.getNom(),emp.getPrenom(),emp.getEmail(),emp.getFonction());
        System.out.println(emp);
        loginServiceImpl.addRoleToUser(emp.getUsername(),appRoleRepository.findByRoleId(Long.parseLong(emp.getType())).getRoleName());
        return new ModelAndView("redirect:/Employee/listemployes", model);
    }

    @GetMapping("/deleteEmployeee/{id_Em}")
    public ModelAndView deleteTache(@PathVariable Integer id_Em, ModelMap model) {
        empSer.removeEmployee(id_Em);
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:/Employee/listemployes", model);
    }

    @PostMapping("/updateEmployee/{emp_id}")
    public ModelAndView updateEmployee(@PathVariable int emp_id, Employee emp, ModelMap model) {
        Employee employee = empSer.getEmployeeById(emp_id).orElse(null);
        if (employee == null) throw new RuntimeException("Employee introuvable");
        employee.setNom(emp.getNom());
        employee.setEmail(emp.getEmail());
        employee.setPrenom(emp.getPrenom());
        employee.setType(emp.getType());
        employee.setFonction(emp.getFonction());
        employee.setPassword(emp.getPassword());
        empSer.updateEmployee(emp);
        return new ModelAndView("redirect:/Employee/listemployes", model);
    }

    @GetMapping("/listemployes")
    public ModelAndView getAllEmployees(Model model,
                                        @RequestParam(name = "page", defaultValue = "0") int page,
                                        @RequestParam(name = "size", defaultValue = "5") int size,
                                        @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        ModelAndView mav = new ModelAndView("list-employees");
        Page<Employee> pageEmployes = emprep.findBynomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("employees", pageEmployes.getContent());
        model.addAttribute("pages", new int[pageEmployes.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("employee", new Employee());
        model.addAttribute("emp", empSer.getAllEmployee());
        model.addAttribute("roles",appRoleService.getAllRoles());
        return mav;
    }

}